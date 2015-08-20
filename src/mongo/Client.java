package mongo;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Client {

	private MongoClient mongoClient;
	private DB mongoDatabase;
	private DBCollection mongoCollection;

	public Client(String host) {
		try {
			mongoClient = new MongoClient(host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public Client(String host, int port) {
		try {
			mongoClient = new MongoClient(host, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public Client database(String database) {
		mongoDatabase = mongoClient.getDB(database);
		return this;
	}

	public Client collection(String collection) {
		mongoCollection = mongoDatabase.getCollection(collection);
		return this;
	}

	public DBObject get(Object id) {
		return mongoCollection.findOne(id);
	}

	public DBObject get(Map map) {
		return mongoCollection.findOne(new BasicDBObject(map));
	}

	public List<DBObject> find(Map map) {
		return mongoCollection.find(new BasicDBObject(map)).toArray();
	}

	public void insert(Map map) {
		mongoCollection.insert(new BasicDBObject(map));
	}

	public void insert(Object id, Map map) {
		DBObject dbObject = new BasicDBObject(map);
		dbObject.put("_id", id);
		mongoCollection.insert(dbObject);
	}

	public void update(Map map) {
		mongoCollection.update(new BasicDBObject("_id", map.get("_id")), new BasicDBObject(map));
	}

	public void update(Object id, Map map) {
		DBObject dbObject = new BasicDBObject(map);
		dbObject.put("_id", id);
		mongoCollection.update(new BasicDBObject("_id", id), dbObject);
	}

	public void save(Map map) {
		mongoCollection.save(new BasicDBObject(map));
	}

	public void save(Object id, Map map) {
		DBObject dbObject = new BasicDBObject(map);
		dbObject.put("_id", id);
		mongoCollection.save(dbObject);
	}

	public void remove(Object id) {
		mongoCollection.remove(new BasicDBObject("_id", id));
	}

	public void remove(Map map) {
		mongoCollection.remove(new BasicDBObject(map));
	}

}
