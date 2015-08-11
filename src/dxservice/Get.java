package dxservice;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.transport.http.HTTPConstants;

import sun.misc.BASE64Encoder;

public class Get {

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	private DateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S.Z");
	private Service service = new Service();
	private Call call;

	public Get(String url, String username, String password) {
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			call.setTimeout(2000);
			call.setUseSOAPAction(true);
			call.setProperty(HTTPConstants.REQUEST_HEADERS, encodeAuth(username, password));
			call.setOperationName(new QName("ns", "get"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String[] invoke(String appName, String taskOId, Map<Object, Object> params) {
		try {
			return (String[]) call.invoke(new Object[] { appName, taskOId, serializable(params) });
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	private static Object encodeAuth(String username, String password) {
		Hashtable<String, String> hashTb = new Hashtable<String, String>();
		try {
			String auth = username + ":" + ((password == null) ? "" : password);
			hashTb.put("RPC_CONNECTION_AUTHORIZATION", new BASE64Encoder().encode(auth.getBytes("utf-8")));
			hashTb.put("username", username);
			hashTb.put("password", password);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return hashTb;
	}

	private String serializable(Map<Object, Object> params) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<maps>");
		for (Object key : params.keySet())
			stringBuffer.append(String.format("<map><key><![CDATA[%s]]></key><value>%s</value></map>", key, objectToString(params.get(key))));
		stringBuffer.append("</maps>");
		return stringBuffer.toString();
	}

	private String objectToString(Object obj) {
		if (obj == null)
			return "";
		else if (obj instanceof String || obj instanceof Character)
			return "<![CDATA[" + obj.toString() + "]]>";
		else if (obj instanceof Date)
			return dateFormat.format((Date) obj);
		else if (obj instanceof Time)
			return timeFormat.format((Date) obj);
		else if (obj instanceof Timestamp)
			return datetimeFormat.format((Date) obj);
		else
			return obj.toString();
	}

}
