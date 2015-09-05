package test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import util.Carte;
import util.Carte.JobStatus;

public class CarteTest {

	@Test
	public void testInit() {
		JobStatus jobStatus = Carte.init();
		System.out.println("statusCode: " + jobStatus.getStatusCode());
		System.out.println("finished: " + jobStatus.isFinished());
		System.out.println("hasError: " + jobStatus.hasError());
		System.out.println("error: " + jobStatus.getError());
	}

	@Test
	public void testSyncEnterpriseToZCDH() {
		String enterpriseId = "";
		JobStatus jobStatus = Carte.syncEnterpriseToZCDH(enterpriseId);
		System.out.println("statusCode: " + jobStatus.getStatusCode());
		System.out.println("finished: " + jobStatus.isFinished());
		System.out.println("hasError: " + jobStatus.hasError());
		System.out.println("error: " + jobStatus.getError());
	}

	@Test
	public void createLink() {
		String link = "http://192.168.48.84:8080/ent/account/loginForHBJYJ/";// 最好是可配置的

		String username = "myadmin";// 我们提供
		String password = "my123456";// 我们提供
		String dataKey = "751030120"; // 企业的主键

		String keys = encodeKeys(username, password, dataKey);
		link = link + keys;// 用于跳转到我们系统的链接
		System.out.println(link);// http://192.168.48.84:8080/ent/account/loginForHBJYJ/bXlhZG1pbjpteTEyMzQ1Njo3NTEwMzAxMjA=
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
