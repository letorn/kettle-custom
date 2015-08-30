package mongo;

import java.net.UnknownHostException;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
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

	public Client(String host, String database) {
		try {
			mongoClient = new MongoClient(host);
			mongoDatabase = mongoClient.getDB(database);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public Client(String host, int port, String database) {
		try {
			mongoClient = new MongoClient(host, port);
			mongoDatabase = mongoClient.getDB(database);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public Client(String host, String database, String collection) {
		try {
			mongoClient = new MongoClient(host);
			mongoDatabase = mongoClient.getDB(database);
			mongoCollection = mongoDatabase.getCollection(collection);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public Client(String host, int port, String database, String collection) {
		try {
			mongoClient = new MongoClient(host, port);
			mongoDatabase = mongoClient.getDB(database);
			mongoCollection = mongoDatabase.getCollection(collection);
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

	public String get(Object id) {
		DBObject dbObject = mongoCollection.findOne(id);
		return dbObject != null ? dbObject.toString() : null;
	}

	public String get(Map map) {
		DBObject dbObject = mongoCollection.findOne(new BasicDBObject(map));
		return dbObject != null ? dbObject.toString() : null;
	}

	public String find() {
		return encode(mongoCollection.find());
	}

	public String find(Map map) {
		return encode(mongoCollection.find(new BasicDBObject(map)));
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

	private String encode(DBCursor dbCursor) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		if (dbCursor.hasNext())
			buffer.append(dbCursor.next().toString());
		while (dbCursor.hasNext())
			buffer.append("," + dbCursor.next().toString());
		buffer.append("]");
		return buffer.toString();
	}

}
