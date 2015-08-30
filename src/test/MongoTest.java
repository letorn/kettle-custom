package test;

import mongo.Client;

import org.junit.Test;

public class MongoTest {

	@Test
	public void testGet() {
		String host = "192.168.48.84";
		String database = "dasyn";
		String collection = "record";
		String id = "hb-w2t-enterprise";

		String record = new Client(host, database, collection).get(id);
		System.out.println(record);
	}

	@Test
	public void testFind() {
		String host = "192.168.48.84";
		String database = "dasyn";
		String collection = "task";

		String records = new Client(host, database, collection).find();
		System.out.println(records);
	}

}
