package test;

import org.junit.Test;

import dxservice.Get;

public class GetTest {

	/**
	 * 测试查询用人单位信息
	 */
	@Test
	public void demo() {
		String url = "http://120.25.163.40:9090/dxservice/svcs/DataService";
		String username = "admin";
		String password = "1";

		String appName = "职场导航网络科技有限公司";
		String taskOId = "hbjyweb_webservice_cb20_xml_task";
		String params = "<maps><map><key><![CDATA[AAB003]]></key><value>07957472-7</value></map><map><key><![CDATA[AAB004]]></key><value>职场导航</value></map><map><key><![CDATA[AAF036_1]]></key><value>19800101</value></map><map><key><![CDATA[AAF036_2]]></key><value>20151231</value></map><map><key><![CDATA[AAE022]]></key><value>%</value></map></maps>";

		Get dxGet = new Get(url, username, password);
		String[] strs = dxGet.invoke(appName, taskOId, params);
		for (String str : strs)
			System.out.println(str);
	}

}
