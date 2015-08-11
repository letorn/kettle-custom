package dxservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;


public class TestGet {

	public static void main(String[] args) {
		String url = "http://127.0.0.1:8881/dxservice/svcs/DataService";
		String username = "admin";
		String password = "1";

		String appName = "湖北省交换区数据交换服务器";
		String taskOId = "hbjyweb_webservice_cb20_xml_task";
		Map params = new HashMap();
		params.put("AAB003", "07957472-7");
		params.put("AAB004", "职场导航");
		params.put("AAF036_1", "19800101");
		params.put("AAF036_2", "20151231");
		params.put("AAE022", "%");

		Get get = new Get(url, username, password);
		String[] strs = get.invoke(appName, taskOId, params);
		for (String str : strs)
			System.out.println(str);
	}
}
