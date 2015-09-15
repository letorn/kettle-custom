package test;

import org.junit.Test;

import util.File;
import dasyn.Client;

public class DaSynTest {

	private static String url = "http://192.168.49.34:8082/dasyn/service";
	private static String username = "li";
	private static String password = "123";

	@Test
	public void testGetEnterprise() {
		String task = "get-enterprise";
		String jsonData = File.read(filename("/test/dasyn/get-enterprise.json"));

		Client client = new Client(url, username, password);
		Object[] responses = client.execute(task, jsonData);

		for (Object response : responses)
			System.out.println(response);
	}

	@Test
	public void testGetEntpost() {
		String task = "get-entpost";
		String jsonData = File.read(filename("/test/dasyn/get-entpost.json"));

		Client client = new Client(url, username, password);
		Object[] responses = client.execute(task, jsonData);

		for (Object response : responses)
			System.out.println(response);
	}

	@Test
	public void testGetJobhunter() {
		String task = "get-jobhunter";
		String jsonData = File.read(filename("/test/dasyn/get-jobhunter.json"));

		Client client = new Client(url, username, password);
		Object[] responses = client.execute(task, jsonData);

		for (Object response : responses)
			System.out.println(response);
	}

	@Test
	public void testGetJobresume() {
		String task = "get-jobresume";
		String jsonData = File.read(filename("/test/dasyn/get-jobresume.json"));

		Client client = new Client(url, username, password);
		Object[] responses = client.execute(task, jsonData);

		for (Object response : responses)
			System.out.println(response);
	}

	@Test
	public void testPostEnterprise() {
		String task = "post-enterprise";
		String jsonData = File.read(filename("/test/dasyn/post-enterprise.json"));

		Client client = new Client(url, username, password);
		Object[] responses = client.execute(task, jsonData);

		for (Object response : responses)
			System.out.println(response);
	}

	private static String filename(String filename) {
		return System.class.getResource(filename).getFile();
	}

}
