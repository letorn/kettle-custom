package dasyn;

import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Client {

	private HttpClient httpClient = new HttpClient();
	private PostMethod postMethod;

	public Client(String url) {
		postMethod = new PostMethod(url);
	}

	public Client(String url, String username, String password) {
		postMethod = new PostMethod(url);
		postMethod.addRequestHeader("Username", username);
		postMethod.addRequestHeader("Password", password);
	}

	public String execute(String str) {
		String responseBody = null;
		try {
			postMethod.setRequestEntity(new StringRequestEntity(str, "application/json;charset=UTF-8", "UTF-8"));
			httpClient.executeMethod(postMethod);
			responseBody = postMethod.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseBody;
	}

	public String execute(Map map) {
		String responseBody = null;
		try {
			postMethod.setRequestEntity(new StringRequestEntity(JSONObject.toJSONString(map), "application/json;charset=UTF-8", "UTF-8"));
			httpClient.executeMethod(postMethod);
			responseBody = postMethod.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseBody;
	}

	public String execute(List list) {
		String responseBody = null;
		try {
			postMethod.setRequestEntity(new StringRequestEntity(JSONArray.toJSONString(list), "application/json;charset=UTF-8", "UTF-8"));
			httpClient.executeMethod(postMethod);
			responseBody = postMethod.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseBody;
	}

}
