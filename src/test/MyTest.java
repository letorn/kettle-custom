package test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class MyTest {

	@Test
	public void createLink() {
		String link = "http://10.128.10.147/ent/account/loginForHBJYJ/";// 最好是可配置的

		String username = "hb";// 我们提供
		String password = "hb";// 我们提供
		String dataKey = "E5AF7B1AB654C27AE040800A9A083FA2"; // 企业的主键

		String keys = encodeKeys(username, password, dataKey);
		link = link + keys;// 用于跳转到我们系统的链接
		System.out.println(link);// http://10.128.10.147/ent/account/loginForHBJYJ/aGI6aGI6RTVBRjdCMUFCNjU0QzI3QUUwNDA4MDBBOUEwODNGQTI=
	}

	private static String encodeKeys(String username, String password, String dataKey) {
		try {
			String keys = ((username == null) ? "" : username) + ":" + ((password == null) ? "" : password) + ":" + ((dataKey == null) ? "" : dataKey);
			return new BASE64Encoder().encode(keys.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	private static String[] decodeKeys(String keys) {
		if (keys == null)
			return null;
		try {
			byte[] bytes = new BASE64Decoder().decodeBuffer(keys);
			String[] strs = new String(bytes).split(":", 3);
			String[] mystrs = new String[] { "", "", "" };
			for (int i = 0; i < strs.length; i++)
				if (strs[i] != null)
					mystrs[i] = strs[i];
			return mystrs;
		} catch (Exception e) {
			return null;
		}
	}

}
