package dasyn;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import sun.misc.BASE64Encoder;

public class Client {

	private HttpClient httpClient = new HttpClient();
	private PostMethod postMethod;
	private int timeout = 90000;

	public Client(String url) {
		postMethod = new PostMethod(url);
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
	}

	public Client(String url, String username, String password) {
		postMethod = new PostMethod(url);
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
		postMethod.addRequestHeader("Authorization", encodeAuth(username, password));
	}

	public Object[] execute(String task, String str) {
		try {
			postMethod.setQueryString(new NameValuePair[] { new NameValuePair("task", task) });
			postMethod.setRequestEntity(new StringRequestEntity(str, "application/json;charset=UTF-8", "UTF-8"));
			httpClient.executeMethod(postMethod);
			return new Object[] { postMethod.getStatusCode(), postMethod.getStatusCode() == HttpServletResponse.SC_OK ? postMethod.getResponseBodyAsString() : postMethod.getStatusText() };
		} catch (Exception e) {
			e.printStackTrace();
			return new Object[] { -1, "Unkonwn" };
		}
	}

	public Object[] execute(String task, Map map) {
		try {
			postMethod.setQueryString(new NameValuePair[] { new NameValuePair("task", task) });
			postMethod.setRequestEntity(new StringRequestEntity(JSONObject.toJSONString(map), "application/json;charset=UTF-8", "UTF-8"));
			httpClient.executeMethod(postMethod);
			return new Object[] { postMethod.getStatusCode(), postMethod.getStatusCode() == HttpServletResponse.SC_OK ? postMethod.getResponseBodyAsString() : postMethod.getStatusText() };
		} catch (Exception e) {
			e.printStackTrace();
			return new Object[] { -1, "Unkonwn" };
		}
	}

	public Object[] execute(String task, List list) {
		try {
			postMethod.setQueryString(new NameValuePair[] { new NameValuePair("task", task) });
			postMethod.setRequestEntity(new StringRequestEntity(JSONArray.toJSONString(list), "application/json;charset=UTF-8", "UTF-8"));
			httpClient.executeMethod(postMethod);
			return new Object[] { postMethod.getStatusCode(), postMethod.getStatusCode() == HttpServletResponse.SC_OK ? postMethod.getResponseBodyAsString() : postMethod.getStatusText() };
		} catch (Exception e) {
			e.printStackTrace();
			return new Object[] { -1, "Unkonwn" };
		}
	}

	public void setTimeout(int timeout) {
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
	}

	private String encodeAuth(String username, String password) {
		try {
			String auth = ((username == null) ? "" : username) + ":" + ((password == null) ? "" : password);
			return "Basic " + new BASE64Encoder().encode(auth.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}
