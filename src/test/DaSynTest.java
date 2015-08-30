package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import dasyn.Client;

public class DaSynTest {

	@Test
	public void testClient() {
		String url = "http://192.168.49.34:8080/dasyn/post/enterprise";
		String resp = new Client(url).execute("[]");
		System.out.println(resp);
	}

	@Test
	public void testEnterprise() {
		String url = "http://192.168.49.34:8080/dasyn/post/enterprise";
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Map<String, Object> d = new HashMap<String, Object>();
		d.put("name", "test...");
		d.put("updateDate", new Date().getTime());
		data.add(d);
		String resp = new Client(url).execute(data);
		System.out.println(resp);
	}

}
