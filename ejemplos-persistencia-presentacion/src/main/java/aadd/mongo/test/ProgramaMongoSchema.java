package aadd.mongo.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.ValidationOptions;
import com.mongodb.client.result.InsertOneResult;

public class ProgramaMongoSchema {

	public static void main(String[] args) {
		
		String connectionString = "mongodb+srv://mcarmen:<password>@aadd2023.oan6qse.mongodb.net/?appName=aadd2023";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {

        	 MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
        	 
        	 StringBuilder content = new StringBuilder();

             try (BufferedReader br = new BufferedReader(new FileReader("json/grade.json"))) {
                 String line;
                 while ((line = br.readLine()) != null) {
                     content.append(line);
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
             
        	 Document validationSchema = new Document("$jsonSchema", Document.parse(content.toString()));
        	 
        	 ValidationOptions validationOptions = new ValidationOptions().validator(validationSchema);

             // Configuramos las opciones de creación de la colección
             CreateCollectionOptions collectionOptions = new CreateCollectionOptions().validationOptions(validationOptions);

        	 
        	sampleTrainingDB.createCollection("grades2Prueba", collectionOptions);
        	 
             MongoCollection<Document> gradesCollection = sampleTrainingDB.getCollection("grades2Prueba");
             
             Random rand = new Random();
             Document student = new Document("_id", new ObjectId());
             student.append("student_id", 10000d)
                    .append("class_id", 1d)
                    .append("scores", List.of(new Document("type", "exam").append("score", rand.nextDouble() * 100),
                                              new Document("type", "quiz").append("score", rand.nextDouble() * 100),
                                              new Document("type", "homework").append("score", rand.nextDouble() * 100),
                                              new Document("type", "homework").append("score", rand.nextDouble() * 100)));
             InsertOneResult result = gradesCollection.insertOne(student);
             
             System.out.println("Insercción correcta "+result);
             
             Document student2 = new Document();
             student2.append("student_id", 10000d)
                    .append("class_id", "MI CLASE")
                    .append("scores", List.of(new Document("type", "exam").append("score", rand.nextDouble() * 100),
                                              new Document("type", "quiz").append("score", rand.nextDouble() * 100),
                                              new Document("type", "homework").append("score", rand.nextDouble() * 100)));
            
             try {
             
             InsertOneResult result2 = gradesCollection.insertOne(student2);
             System.out.println("Insercción correcta "+result2);
             }
             catch(MongoWriteException ex) {
            	 System.err.println("Insercción incorrecta "+ex.getMessage());
             }
            		 
             

             
             
        }
	}
}
