package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mongo.Client;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class MongoTest {

	public static void main(String[] args) throws Exception {
		String host = "192.168.1.144";
		String database = "dasyn";
		String collection = "queue";
		MongoClient mongoClient = new MongoClient(host);
		DB mongoDatabase = mongoClient.getDB(database);
		DBCollection mongoCollection = mongoDatabase.getCollection(collection);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("_id", "myid");
		data.put("account", "letorn");
		data.put("username", "letorn");
		DBObject dbObject = new BasicDBObject(data);
		WriteResult writeResult = mongoCollection.insert(dbObject);
		
		System.out.println(dbObject.get("_id"));
		
	}

	@Test
	public void testClient() {
		String host = "192.168.1.144";
		String database = "dasyn";
		String collection = "queue";
		String id = "55d575e146eabd890fe2add77";

		DBObject a = new Client(host).database(database).collection(collection).get(id);
		System.out.println(a);

		Map<String, Object> query = new HashMap<String, Object>();
		query.put("tableName", "entpostt");
		DBObject b = new Client(host).database(database).collection(collection).get(query);
		List<DBObject> c = new Client(host).database(database).collection(collection).find(query);
		System.out.println(b);
		System.out.println(c);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", "hb1");
		data.put("account", "letorn");
		data.put("username", "letorn");
		// new
		// Client(host).database(database).collection(collection).save(data);
		new Client(host).database(database).collection(collection).save("aa1111111111111111111111", data);
	}

}
