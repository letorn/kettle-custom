package test;

import org.junit.Test;

import dxservice.Post;

public class PostTest {

	@Test
	public void demo() {
		String url = "http://120.25.163.40:9090/dxservice/svcs/DataService";
		String username = "admin";
		String password = "1";

		String appName = "职场导航网络科技有限公司";
		String taskOId = "hbjyweb_webservice_xml_cb20_task";
		String xmlDataForAdd = "<?xml version='1.0' encoding='utf-8'?><rows><row><LKEY><![CDATA[zcdh_test_01]]></LKEY><AAB004>职场导航网络科技有限公司</AAB004><AAB003><![CDATA[07957472-7]]></AAB003><AAB007><![CDATA[440003000021483]]></AAB007><AAB022><![CDATA[0761]]></AAB022><AAB019><![CDATA[10]]></AAB019><AAB020><![CDATA[170]]></AAB020><AAB049>3000</AAB049><AAB056>5</AAB056><AAB301><![CDATA[440400000000]]></AAB301><AAB302><![CDATA[珠海市]]></AAB302><AAE006><![CDATA[珠海高新区大州科技园]]></AAE006><AAE007 /><ACB205 /><AAE392 /><AAB092><![CDATA[单位简介单位简介]]></AAB092><AAE045><![CDATA[黄海华]]></AAE045><AAB015 /><AAE004><![CDATA[杜小龙]]></AAE004><AAE005><![CDATA[13380873369]]></AAE005><BAE017 /><AAE014 /><AAE159><![CDATA[jim.du@3hjob.cn]]></AAE159><GPSX><![CDATA[114.323997]]></GPSX><GPSY><![CDATA[30.57785]]></GPSY><ACB20A /><ACB116><![CDATA[1]]></ACB116><ACE750><![CDATA[1]]></ACE750><AAE011><![CDATA[ff808081426fa2e30142730f400b003a]]></AAE011><AAE017><![CDATA[0]]></AAE017><AAE036>2015-08-12</AAE036><AAE019><![CDATA[王帆]]></AAE019><AAE020><![CDATA[湖北省人力资源和社会保障厅]]></AAE020><AAE024><![CDATA[湖北省]]></AAE024><AAE022><![CDATA[420000000000]]></AAE022><AAE393 /></row></rows> ";
		String xmlDataForUpd = "<?xml version='1.0' encoding='utf-8'?><rows><row><LKEY><![CDATA[zcdh_test_01]]></LKEY><AAB004>职场导航网络科技有限公司</AAB004><AAB003><![CDATA[07957472-7]]></AAB003><AAB007><![CDATA[440003000021483]]></AAB007><AAB022><![CDATA[0761]]></AAB022><AAB019><![CDATA[10]]></AAB019><AAB020><![CDATA[170]]></AAB020><AAB049>3000</AAB049><AAB056>5</AAB056><AAB301><![CDATA[440400000000]]></AAB301><AAB302><![CDATA[珠海市]]></AAB302><AAE006><![CDATA[珠海高新区大州科技园]]></AAE006><AAE007 /><ACB205 /><AAE392 /><AAB092><![CDATA[单位简介单位简介]]></AAB092><AAE045><![CDATA[黄海华]]></AAE045><AAB015 /><AAE004><![CDATA[杜小龙]]></AAE004><AAE005><![CDATA[13380873369]]></AAE005><BAE017 /><AAE014 /><AAE159><![CDATA[jim.du@3hjob.cn]]></AAE159><GPSX><![CDATA[114.323997]]></GPSX><GPSY><![CDATA[30.57785]]></GPSY><ACB20A /><ACB116><![CDATA[1]]></ACB116><ACE750><![CDATA[2]]></ACE750><AAE011><![CDATA[ff808081426fa2e30142730f400b003a]]></AAE011><AAE017><![CDATA[0]]></AAE017><AAE036>2015-08-12</AAE036><AAE019><![CDATA[王帆]]></AAE019><AAE020><![CDATA[湖北省人力资源和社会保障厅]]></AAE020><AAE024><![CDATA[湖北省]]></AAE024><AAE022><![CDATA[420000000000]]></AAE022><AAE393 /></row></rows> ";
		String params = "";

		Post dxPost = new Post(url, username, password);
		String[] strs = dxPost.invoke(appName, taskOId, xmlDataForUpd, params);
		for (String str : strs)
			System.out.println(str);
	}

}
