package test;

import org.junit.Test;

import util.File;
import dxservice.Client;

public class DxServiceTest {

	// private String url = "http://120.25.163.40:9090/dxservice/svcs/DataService";
	// private String url = "http://59.175.218.200:9090/dxservice/svcs/DataService";// 武汉外网
	private String url = "http://10.128.10.141:9090/dxservice/svcs/DataService";// 武汉内网
	private String username = "admin";
	private String password = "1";

	@Test
	public void testGetEnterprise() {
		String appName = "职场导航网络科技有限公司";
		String taskOId = "hbjyweb_webservice_cb20_xml_task";
		String params = File.read(filename("/test/dxservice/get-enterprise.xml")).replaceAll(">\\s+<", "><");

		Client client = new Client(url, username, password, "get");
		Object[] resps = client.execute(appName, taskOId, params);
		for (Object resp : resps)
			System.out.println(resp);
	}

	@Test
	public void testGetEntpost() {
		String appName = "职场导航网络科技有限公司";
		String taskOId = "hbjyweb_webservice_cb21_xml_task";
		String params = File.read(filename("/test/dxservice/get-entpost.xml")).replaceAll(">\\s+<", "><");

		Client client = new Client(url, username, password, "get");
		Object[] resps = client.execute(appName, taskOId, params);
		System.out.println();
		for (Object resp : resps)
			System.out.println(resp);
	}

	@Test
	public void testPostEnterprise() {
		String appName = "职场导航网络科技有限公司";
		String taskOId = "hbjyweb_webservice_xml_cb20_task";
		String xmlData = File.read(filename("/test/dxservice/post-enterprise.xml")).replaceAll(">\\s+<", "><");

		Client client = new Client(url, username, password, "post");
		Object[] resps = client.execute(appName, taskOId, xmlData);
		for (Object resp : resps)
			System.out.println(resp);
	}

	@Test
	public void testPostJobhunter() {
		String appName = "职场导航网络科技有限公司";
		String taskOId = "hbjyweb_webservice_xml_cc20_task";
		String xmlData = File.read(filename("/test/dxservice/post-jobhunter.xml")).replaceAll(">\\s+<", "><");
		Client client = new Client(url, username, password, "post");
		Object[] resps = client.execute(appName, taskOId, xmlData);
		for (Object resp : resps)
			System.out.println(resp);
	}

	private static String filename(String filename) {
		return System.class.getResource(filename).getFile();
	}

}
