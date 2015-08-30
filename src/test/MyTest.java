package test;

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import sun.misc.BASE64Encoder;

public class MyTest {

	public static void main(String[] args) throws Exception {
		String url = "http://localhost:8088/kettle/hb";
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.addRequestHeader("Authorization", encodeAuth("admin", "123456"));
		postMethod.setFollowRedirects(false);
		httpClient.executeMethod(postMethod);
		String resp = postMethod.getResponseBodyAsString();
		System.out.println(resp);
		System.out.println(postMethod.getStatusCode());
	}

	private static String encodeAuth(String username, String password) {
		try {
			String auth = ((username == null) ? "" : username) + ":" + ((password == null) ? "" : password);
			return new BASE64Encoder().encode(auth.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}
