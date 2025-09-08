package com.radar.mongodb;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class InsertADocument {

    public static void main( String[] args ) {

        String uri = "mongodb+srv://" + args[0] + ":" + args[1] + "@cluster0.c8l7t3g.mongodb.net/";

	try (MongoClient mongoClient = MongoClients.create(uri)) {
	    ObjectId objectId = new ObjectId();
            BsonObjectId	bsonObjectId = new BsonObjectId( objectId );
	    BsonDateTime bsonDateTime = new  BsonDateTime(40000L);

            Document doc1 = new Document("name", "Marco Aurelio").append("email", "marco@gmail.com")
		    .append("movie_id",bsonObjectId)
		    .append("text","Denme la libertad o denme la muerte");
		    .append("date", bsonDateTime );

            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("comments");

	    InsertOneResult result = collection.insertOne(doc1);

	    System.out.println("Inserted a document with the following id: "
               + result.getInsertedId().asObjectId().getValue());

            /*
            Document doc = collection.find(eq("title", "Back to the Future")).first();
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
	    */
        }
    }
}
