package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import dasyn.Client;

public class DaSynTest {

	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S Z");

	@Test
	public void testClient() {
		String url = "http://192.168.49.34:8080/dasyn/service";
		Object[] responses = new Client(url).execute("postEnterprise", "[]");
		for (Object response : responses)
			System.out.println(response);
	}

	@Test
	public void testPostEnterprise() {
		String url = "http://192.168.49.34:8080/dasyn/service";
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Object[] responses = new Client(url).execute("postEnterprise", data);
		for (Object response : responses)
			System.out.println(response);
	}

	@Test
	public void testGetJobhunter() throws Exception {
		String url = "http://192.168.49.34:8081/dasyn/service";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("jobhunterId", 1);
		// params.put("beginTime", "2015-01-01 00:00:00.0 +0800");
		// params.put("endTime", "2015-3-31 00:00:00.0 +0800");
		Object[] responses = new Client(url).execute("getJobhunter", params);
		for (Object response : responses)
			System.out.println(response);
	}

}
