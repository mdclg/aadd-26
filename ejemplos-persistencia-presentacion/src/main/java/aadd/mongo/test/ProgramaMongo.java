package aadd.mongo.test;

import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class ProgramaMongo {

	public static void main(String[] args) {
		
		
		String connectionString = "mongodb+srv://mcarmen:<password>@aadd2023.oan6qse.mongodb.net/?appName=aadd2023";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {

        	 MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
             MongoCollection<Document> gradesCollection = sampleTrainingDB.getCollection("grades");
             
             Random rand = new Random();
             Document student = new Document("_id", new ObjectId());
             student.append("student_id", 10000d)
                    .append("class_id", 1d)
                    .append("scores", List.of(new Document("type", "exam").append("score", rand.nextDouble() * 100),
                                              new Document("type", "quiz").append("score", rand.nextDouble() * 100),
                                              new Document("type", "homework").append("score", rand.nextDouble() * 100),
                                              new Document("type", "homework").append("score", rand.nextDouble() * 100)));
             gradesCollection.insertOne(student);
             
             // Actualizar documento
             
             Document filtro = new Document("student_id", 10000d);
             Document update = new Document("$set", new Document("comment", "Estudia más"));
             
             UpdateResult updateResult = gradesCollection.updateOne(filtro, update);
             
             System.out.println(updateResult);
             
             // Actualizar o insertar un documento `upsert`
             
             Document filtro2 = new Document("student_id", 10002d).append("class_id", 10d);
             Document update2 = new Document("$set", new Document("comment", "Suspenso"));
             UpdateOptions options = new UpdateOptions().upsert(true);
             
             updateResult = gradesCollection.updateOne(filtro2, update2, options);
             System.out.println(updateResult);
             
             // Actualizar y recuperar
             
             JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();
             
             Document update3 = new Document("$set", new Document("class_id", 20d));
             
             Document oldVersion = gradesCollection.findOneAndUpdate(filtro2, update3);
             System.out.println(oldVersion.toJson(prettyPrint));
             

             FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
             Document newVersion = gradesCollection.findOneAndUpdate(filtro, update3, optionAfter);
             
             System.out.println(newVersion.toJson(prettyPrint));
             
             
             // Borrar
             
             Document filtroBorrar = new Document("student_id", 10002d);
             DeleteResult result = gradesCollection.deleteOne(filtroBorrar);
             System.out.println(result);
             
             
             
             
        }

	}

}
