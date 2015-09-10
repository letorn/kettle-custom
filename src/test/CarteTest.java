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
	public void testSyncEnterpriseAndEntpostFromHBToWHDH() {
		String enterpriseId = "0B8DE458DF0110B0E050800A8C0A34B3";
		JobStatus jobStatus = Carte.syncEnterpriseAndEntpostFromHBToWHDH(enterpriseId);
		System.out.println("statusCode: " + jobStatus.getStatusCode());
		System.out.println("finished: " + jobStatus.isFinished());
		System.out.println("hasError: " + jobStatus.hasError());
		System.out.println("error: " + jobStatus.getError());
	}

	@Test
	public void createLinkSample() {
		String link = "http://10.128.10.147:9091/ent/account/loginForHBJYJ/";// 最好是可配置的

		String username = "hb";// 我们提供
		String password = "hb";// 我们提供
		String dataKey = "0B8DE3F07DFDFCF7E050800A8C0A3411"; // 企业的主键

		String keys = encodeKeys(username, password, dataKey);
		link = link + keys;// 用于跳转到我们系统的链接
		System.out.println(link);// http://10.128.10.147:8080/ent/account/loginForHBJYJ/d2hkaDp3aGRoOjBCOERFNDU4REYwMTEwQjBFMDUwODAwQThDMEEzNEIz
	}

	@Test
	public void createLinks() {
		String[] dataKeys = new String[] {
				"0B8DE44F4862B901E050800A8C0A34A3",
				"0B8DE45410E8E696E050800A8C0A34AB",
				"0B8DE458DF0110B0E050800A8C0A34B3",
				"0B8DE45DB5AF8434E050800A8C0A34BB",
				"0B8DE3D77A3E7AA3E050800A8C0A33EF",
				"0B8DE3DE74937C66E050800A8C0A33F7",
				"0B8DE3E4FA833F5DE050800A8C0A3401",
				"0B8DE3EB7114B624E050800A8C0A3409",
				"0B8DE3F07DFDFCF7E050800A8C0A3411",
				"0B8DE43FE948DC6FE050800A8C0A3481",
				"0B8DE44506DEBEEFE050800A8C0A3489",
				"0B8DE44A3F907648E050800A8C0A3491",
				"0B8DE462AACC995EE050800A8C0A34C5",
				"0B8DE4679435A7FFE050800A8C0A34CD",
				"0B8DE46CCA27BA24E050800A8C0A34D5",
				"0B8DE472FAFD8B4CE050800A8C0A34DD",
				"0B8DE47800B58DADE050800A8C0A34EA",
				"0B8DE47CE721D157E050800A8C0A34F7",
				"0B8DE481B3911D2BE050800A8C0A34FF",
				"0B8DE3F57366F62AE050800A8C0A341B",
				"0B8DE3FA7B01F9BBE050800A8C0A3423",
				"0B8DE3FF84862105E050800A8C0A342B",
				"0B8DE405F408B06FE050800A8C0A3433",
				"0B8DE4114F2FA90AE050800A8C0A3445",
				"0B8DE41668CE8768E050800A8C0A344D",
				"0B8DE41B846E62EAE050800A8C0A3455",
				"0B8DE4209342AF41E050800A8C0A345D",
				"0B8DE425BFBD7BF0E050800A8C0A3466",
				"0B8DE42AB4ED8C92E050800A8C0A346F",
				"0B8DE42F92CCE826E050800A8C0A3477"
		};
		for (String dataKey : dataKeys)
			System.out.println(createLink(dataKey));
	}

	private static String createLink(String dataKey) {
		String link = "http://10.128.10.147:9091/ent/account/loginForHBJYJ/";
		String username = "hb";
		String password = "hb";
		return link + encodeKeys(username, password, dataKey);
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
