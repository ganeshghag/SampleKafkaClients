package com.ghag.rnd.mongo;

import java.util.Date;
import java.util.regex.Pattern;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class SampleMongoClient {

	public static void main(String[] args) {
		
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
		System.out.println("DB names="+mongo.getDatabaseNames());
		
		DB db = mongo.getDB("test");
		System.out.println("collections="+db.getCollectionNames());
		
		saveRecord(db, "users", "ganesh ghag-"+new Date().getTime());
		
		
		getRows(db,"users","ganesh ghag.*/");

	}

	
	public static void saveRecord(DB db, String collectionName, String username){
		DBCollection table = db.getCollection(collectionName);
		BasicDBObject document = new BasicDBObject();
		document.put("name", username);
		document.put("age", 43);
		document.put("createdDate", new Date());
		table.insert(document);
	}
	
	public static void getRows(DB db, String collectionName, String username){
		DBCollection table = db.getCollection(collectionName);

		Pattern p = Pattern.compile("ganesh ghag.*", Pattern.CASE_INSENSITIVE);
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", p);

		DBCursor cursor = table.find(searchQuery);
	
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		System.out.println("row count="+cursor.size());
		
		
	}
	
}
