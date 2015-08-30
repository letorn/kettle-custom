package dxservice;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.transport.http.HTTPConstants;

import sun.misc.BASE64Encoder;

/*
 * DataExchange服务查询数据
 */
public class Client {

	private Service service = new Service();
	private int callTimeout = 3000;
	private Call call;

	public Client(String dxservice, String username, String password) {
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(dxservice);
			call.setTimeout(callTimeout);
			call.setUseSOAPAction(true);
			call.setProperty(HTTPConstants.REQUEST_HEADERS, encodeAuth(username, password));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Client(String dxservice, String username, String password, String operation) {
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(dxservice);
			call.setTimeout(callTimeout);
			call.setUseSOAPAction(true);
			call.setProperty(HTTPConstants.REQUEST_HEADERS, encodeAuth(username, password));
			call.setOperationName(new QName("ns", operation));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setOperationName(String opName) {
		call.setOperationName(opName);
	}

	public void setOperationName(String namespaceURI, String localPart) {
		call.setOperationName(new QName(namespaceURI, localPart));
	}

	public String[] execute(Object... params) {
		try {
			return (String[]) call.invoke(params);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	public void setTimeout(int timeout) {
		call.setTimeout(timeout);
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

}
