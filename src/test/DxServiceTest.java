package test;

import org.junit.Test;

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
		String params = "<maps>"
				// + "<map><key><![CDATA[ACB200]]></key><value><![CDATA[E5AF7B1AB654C27AE040800A9A083FA2]]></value></map>"
				+ "<map><key><![CDATA[AAB004]]></key><value><![CDATA[%]]></value></map>"
				+ "<map><key><![CDATA[AAB003]]></key><value><![CDATA[%]]></value></map>"
				+ "<map><key><![CDATA[AAE022]]></key><value><![CDATA[%]]></value></map>"
				+ "<map><key><![CDATA[AAF036_1]]></key><value><![CDATA[20150101]]></value></map>"
				+ "<map><key><![CDATA[AAF036_2]]></key><value><![CDATA[20150102]]></value></map>"
			+ "</maps>";

		Client client = new Client(url, username, password, "get");
		Object[] resps = client.execute(appName, taskOId, params);
		for (Object resp : resps)
			System.out.println(resp);
	}

	@Test
	public void testGetEntpost() {
		String appName = "职场导航网络科技有限公司";
		String taskOId = "hbjyweb_webservice_cb21_xml_task";
		String params = "<maps>"
				+ "<map><key><![CDATA[ACB200]]></key><value><![CDATA[E5AF7B1AB654C27AE040800A9A083FA2]]></value></map>"
				+ "<map><key><![CDATA[ACB217]]></key><value><![CDATA[%]]></value></map>"
				+ "<map><key><![CDATA[ACA111]]></key><value><![CDATA[%]]></value></map>"
				+ "<map><key><![CDATA[AAE022]]></key><value><![CDATA[%]]></value></map>"
				+ "<map><key><![CDATA[AAF036_1]]></key><value><![CDATA[20151231]]></value></map>"
				+ "<map><key><![CDATA[AAF036_2]]></key><value><![CDATA[20151231]]></value></map>"
			+ "</maps>";

		Client client = new Client(url, username, password, "get");
		Object[] resps = client.execute(appName, taskOId, params);
		System.out.println();
		for (Object resp : resps)
			System.out.println(resp);
	}
	
	@Test
	public void testPost() {
		String appName = "职场导航网络科技有限公司";
		String taskOId = "hbjyweb_webservice_xml_cb20_task";
		String xmlDataForAdd = "<?xml version='1.0' encoding='utf-8'?><rows><row><LKEY><![CDATA[zcdh_test_01]]></LKEY><AAB004>职场导航网络科技有限公司</AAB004><AAB003><![CDATA[07957472-7]]></AAB003><AAB007><![CDATA[440003000021483]]></AAB007><AAB022><![CDATA[0761]]></AAB022><AAB019><![CDATA[10]]></AAB019><AAB020><![CDATA[170]]></AAB020><AAB049>3000</AAB049><AAB056>5</AAB056><AAB301><![CDATA[440400000000]]></AAB301><AAB302><![CDATA[珠海市]]></AAB302><AAE006><![CDATA[珠海高新区大州科技园]]></AAE006><AAE007 /><ACB205 /><AAE392 /><AAB092><![CDATA[单位简介单位简介]]></AAB092><AAE045><![CDATA[黄海华]]></AAE045><AAB015 /><AAE004><![CDATA[杜小龙]]></AAE004><AAE005><![CDATA[13380873369]]></AAE005><BAE017 /><AAE014 /><AAE159><![CDATA[jim.du@3hjob.cn]]></AAE159><GPSX><![CDATA[114.323997]]></GPSX><GPSY><![CDATA[30.57785]]></GPSY><ACB20A /><ACB116><![CDATA[1]]></ACB116><ACE750><![CDATA[1]]></ACE750><AAE011><![CDATA[ff808081426fa2e30142730f400b003a]]></AAE011><AAE017><![CDATA[0]]></AAE017><AAE036>2015-08-12</AAE036><AAE019><![CDATA[王帆]]></AAE019><AAE020><![CDATA[湖北省人力资源和社会保障厅]]></AAE020><AAE024><![CDATA[湖北省]]></AAE024><AAE022><![CDATA[420000000000]]></AAE022><AAE393 /></row></rows> ";
		String xmlDataForUpd = "<?xml version='1.0' encoding='utf-8'?><rows><row><LKEY><![CDATA[zcdh_test_01]]></LKEY><AAB004>职场导航网络科技有限公司</AAB004><AAB003><![CDATA[07957472-7]]></AAB003><AAB007><![CDATA[440003000021483]]></AAB007><AAB022><![CDATA[0761]]></AAB022><AAB019><![CDATA[10]]></AAB019><AAB020><![CDATA[170]]></AAB020><AAB049>3000</AAB049><AAB056>5</AAB056><AAB301><![CDATA[440400000000]]></AAB301><AAB302><![CDATA[珠海市]]></AAB302><AAE006><![CDATA[珠海高新区大州科技园]]></AAE006><AAE007 /><ACB205 /><AAE392 /><AAB092><![CDATA[单位简介单位简介]]></AAB092><AAE045><![CDATA[黄海华]]></AAE045><AAB015 /><AAE004><![CDATA[杜小龙]]></AAE004><AAE005><![CDATA[13380873369]]></AAE005><BAE017 /><AAE014 /><AAE159><![CDATA[jim.du@3hjob.cn]]></AAE159><GPSX><![CDATA[114.323997]]></GPSX><GPSY><![CDATA[30.57785]]></GPSY><ACB20A /><ACB116><![CDATA[1]]></ACB116><ACE750><![CDATA[2]]></ACE750><AAE011><![CDATA[ff808081426fa2e30142730f400b003a]]></AAE011><AAE017><![CDATA[0]]></AAE017><AAE036>2015-08-12</AAE036><AAE019><![CDATA[王帆]]></AAE019><AAE020><![CDATA[湖北省人力资源和社会保障厅]]></AAE020><AAE024><![CDATA[湖北省]]></AAE024><AAE022><![CDATA[420000000000]]></AAE022><AAE393 /></row></rows> ";
		String params = "";

		Client client = new Client(url, username, password, "post");
		Object[] resps = client.execute(appName, taskOId, xmlDataForUpd, params);
		for (Object resp : resps)
			System.out.println(resp);
	}

	@Test
	public void testClient() {
		String appName = "职场导航网络科技有限公司";
		String taskOId = "hbjyweb_webservice_cb20_xml_task";
		String params = "<maps><map><key><![CDATA[AAB003]]></key><value>07957472-7</value></map><map><key><![CDATA[AAB004]]></key><value>职场导航</value></map><map><key><![CDATA[AAF036_1]]></key><value>19800101</value></map><map><key><![CDATA[AAF036_2]]></key><value>20151231</value></map><map><key><![CDATA[AAE022]]></key><value>%</value></map></maps>";

		Client client = new Client(url, username, password, "get");
		Object[] resps = client.execute(appName, taskOId, params);
		for (Object resp : resps)
			System.out.println(resp);
	}

}
