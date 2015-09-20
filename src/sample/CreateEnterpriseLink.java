package sample;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.junit.Test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CreateEnterpriseLink {

	@Test
	public void decodeKeys() {
		/*
		 * jimdu999, 123456
		 * keys: aGI6aGI6MTVEOUE3OUE4QTdBN0M5MEUwNTA4MDBBOEMwQTY4Nzg=
		 * enterprise: 15D9A79A8A7A7C90E050800A8C0A6878
		 */
		String[] strs = decodeKeys("aGI6aGI6MTVEOUE3OUE4QTdBN0M5MEUwNTA4MDBBOEMwQTY4Nzg=");
		System.out.println(Arrays.toString(strs));
	}

	@Test
	public void createLink() {
		String link = "http://10.128.10.147:9091/ent/account/loginForHBJYJ/";// 最好是可配置的
		String username = "hb";// 我们提供
		String password = "hb";// 我们提供
		
		/*
		 * jimdu999, 123456
		 * enterprise: 15D9A79A8A7A7C90E050800A8C0A6878
		 */
		String dataKey = "15D9A79A8A7A7C90E050800A8C0A6878"; // 企业的主键

		String keys = encodeKeys(username, password, dataKey);
		link = link + keys;// 用于跳转到我们系统的链接

		/*
		 * 15D9A79A8A7A7C90E050800A8C0A6878
		 * http://10.128.10.147:9091/ent/account/loginForHBJYJ/aGI6aGI6MTVEOUE3OUE4QTdBN0M5MEUwNTA4MDBBOEMwQTY4Nzg=
		 */
		System.out.println(link + ", " + dataKey);
	}

	@Test
	public void createLinks() {
		String link = "http://10.128.10.147:9091/ent/account/loginForHBJYJ/";// 最好是可配置的
		String username = "hb";// 我们提供
		String password = "hb";// 我们提供
		String[] dataKeys = {
			"0AE3B54628199C0EE050800A8C0A5771",
			"0ADF83958261D217E050800A8C0A6A58",
			"0AE3CB8B52851A2DE050800A8C0A7FE7",
			"0AE3EC07D242A402E050800A8C0A3D67",
			"0AE420C22C9D372DE050800A8C0A1F9A",
			"0AE537166B79D9ABE050800A8C0A127E",
			"0AE7AA74FC654F7CE050800A8C0A3F08",
			"1552724E7EE0C357E050800A8C0A58F9",
			"19C4334852CF95E0E050800A8C0A0A9C",
			"19C6B2750FBAD8D9E050800A8C0A307E",
			"19C86DEBA9E0D675E050800A8C0A6794",
			"19CD4238A13F3CB6E050800A8C0A2A27",
			"19D197EAEC1AC269E050800A8C0A6485",
			"19D197F1D0B327C0E050800A8C0A648D",
			"19D197F799FEAEB7E050800A8C0A6495",
			"19D197FD6D519D79E050800A8C0A649D",
			"19D1980366AC9A28E050800A8C0A64A5",
			"19D198090BF3792BE050800A8C0A64AD",
			"19D1980EDADBA809E050800A8C0A64B5",
			"19D19814B8108AD4E050800A8C0A64BD",
			"19CCBFD721498440E050800A8C0A227B",
			"19CD56D57B2EEC7FE050800A8C0A2AFF",
			"19CD97FF01EB39E9E050800A8C0A2F82",
			"19D1981AE709966BE050800A8C0A64C7",
			"19D198211C6CA8BAE050800A8C0A64CF",
			"19D19826BFC9098FE050800A8C0A64D7",
			"19D1982E1FF1E9D3E050800A8C0A64DF",
			"19D198339FCF69B2E050800A8C0A64E7",
			"19D198398271892CE050800A8C0A64EF",
			"19D1984110A53B47E050800A8C0A64F7",
			"19D19847316A1ACFE050800A8C0A64FF",
			"19D1984E8CB0F0D9E050800A8C0A6507",
			"19D19856D6A0F803E050800A8C0A650F",
			"19D1985C80590679E050800A8C0A6517",
			"19D19862763E5BCFE050800A8C0A651F",
			"19D1986866F0AA2AE050800A8C0A6527",
			"19D1986F0C8BCDA2E050800A8C0A6531",
			"19D198769591B6E9E050800A8C0A6539",
			"19D1987E887D8B80E050800A8C0A6541",
			"19D19884B08EED5DE050800A8C0A6549",
			"19C718F7C4FF4EE8E050800A8C0A3729",
			"19C73C9C73612E4DE050800A8C0A39A3",
			"19C85BC70214A8B2E050800A8C0A66AE",
			"19CCD72747158AFFE050800A8C0A2451"
		}; // 企业的主键

		for (String dataKey : dataKeys) {
			String keys = encodeKeys(username, password, dataKey);
			link = link + keys;// 用于跳转到我们系统的链接

			System.out.println(link + ", " + dataKey);
		}
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
