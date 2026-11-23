package aadd.mongo.test;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.DeleteResult;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;

public class ProgramaMongoCodec {

	public static void main(String[] args) {

		String uri = "mongodb+srv://mcarmen:<password>@aadd2023.oan6qse.mongodb.net/?appName=aadd2023";
		ConnectionString connectionString = new ConnectionString(uri);

		CodecRegistry pojoCodecRegistry = CodecRegistries
				.fromProviders(PojoCodecProvider.builder().automatic(true).build());

		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				pojoCodecRegistry);

		MongoClientSettings clientSettings = MongoClientSettings.builder()

				.applyConnectionString(connectionString)

				.codecRegistry(codecRegistry)

				.build();

		try (MongoClient mongoClient = MongoClients.create(clientSettings)) {

			MongoDatabase db = mongoClient.getDatabase("sample_training");

			MongoCollection<Grade> grades = db.getCollection("grades", Grade.class);

			// crear nuevao

			Grade newGrade = new Grade();
			newGrade.setStudentId(10003d);
			newGrade.setClassId(10d);
			Score score = new Score();
			score.setType("deberes");
			score.setScore(50d);
			newGrade.setScores(List.of(score));

			grades.insertOne(newGrade);

			System.out.println("Insertado.");

			Document busqueda = new Document("student_id", 10003d);

			Grade grade = grades.find(busqueda).first();

			System.out.println("Recuperado student_id:\t" + grade.getStudentId()+" con classId "+grade.getClassId());

			// actualizar añadiendo nueva puntuación

			List<Score> arrayScores = new ArrayList<>(grade.getScores());

			Score scoreNuevo = new Score();
			scoreNuevo.setType("examen");
			scoreNuevo.setScore(40d);

			arrayScores.add(scoreNuevo);

			grade.setScores(arrayScores);

			Document filtroPorId = new Document("_id", grade.getId());

			FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions()
					.returnDocument(ReturnDocument.AFTER);

			Grade updatedGrade = grades.findOneAndReplace(filtroPorId, grade, returnDocAfterReplace);

			System.out.println("Actualizado student_id:\t" + updatedGrade.getStudentId()+" con classId "+updatedGrade.getClassId());

			// borrar

			DeleteResult borrado = grades.deleteOne(filtroPorId);
			System.out.println(borrado);

		}

	}

}
