package aadd.mongo.test;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class ProgramaSelectores {

	public static void main(String[] args) {

		String connectionString = "mongodb+srv://mcarmen:<password>@aadd2023.oan6qse.mongodb.net/?appName=aadd2023";

		try (MongoClient mongoClient = MongoClients.create(connectionString)) {

			MongoDatabase sampleMflix = mongoClient.getDatabase("sample_mflix");
			MongoCollection<Document> movies = sampleMflix.getCollection("movies");

			// Película mujercitas
			// db.movies.find({ "title": "Little Women" })

			Document query = new Document("title", "Little Women");
			FindIterable<Document> result = movies.find(query);
			result.forEach(doc -> System.out.println("Buscar peli por título Little Women :" + doc.toJson()));

			// Películas de "Action" o dirigidas por "John Ford"
			/*
			 * db.movies.find({ $or: [ { genres: "Action" }, { directors: "John Ford" } ] })
			 */

			Document query2 = new Document("$or",
					Arrays.asList(new Document("genres", "Action"), new Document("directors", "John Ford")));
			FindIterable<Document> result2 = movies.find(query2)
					.projection(new Document("title", 1).append("directors", 1).append("genres", 1)).limit(6);
			result2.forEach(doc -> System.out.println("Genero accion o director John Ford " + doc.toJson()));

			// Películas que tienen exactamente el género "Drama" (pero puede tener más
			// géneros)
			// db.movies.find({ genres: "Drama" })

			Document query3 = new Document("genres", "Drama");
			FindIterable<Document> result3 = movies.find(query3).projection(new Document("plot", 0)).limit(3);
			result3.forEach(doc -> System.out.println("Genero drama " + doc.toJson()));

			// Películas que contienen los géneros ["Action", "Drama"] (todos)
			// db.movies.find({ genres: { $all: ["Action", "Drama"] } })

			Document query4 = new Document("genres", new Document("$all", Arrays.asList("Action", "Drama")));
			FindIterable<Document> result4 = movies.find(query4).projection(new Document("plot", 0)).limit(3);
			result4.forEach(doc -> System.out.println("genero accion y drama " + doc.toJson()));

			// Con Filters:

			FindIterable<Document> result4bis = movies.find(Filters.all("genres", "Action", "Drama"))
					.projection(new Document("plot", 0)).limit(3);
			result4bis.forEach(doc -> System.out.println("genero accion y drama " + doc.toJson()));

			// Películas con duración mayor a 90 minutos
			// db.movies.find({ "runtime": { $gt: 90 } })

			Document query5 = new Document("runtime", new Document("$gt", 90));
			FindIterable<Document> result5 = movies.find(query5).projection(new Document("plot", 0)).limit(3);
			result5.forEach(doc -> System.out.println("runtime mayor que 90 " + doc.toJson()));

			Bson query5bis = Filters.gt("runtime", 90);
			FindIterable<Document> result5bis = movies.find(query5bis).projection(new Document("plot", 0)).limit(3);
			result5bis.forEach(doc -> System.out.println("runtime mayor que 90 " + doc.toJson()));

			// Películas con calificación IMDb mayor a 6
			// db.movies.find({ "imdb.rating": { $gt: 6.0 } })

			Document query6 = new Document("imdb.rating", new Document("$gt", 6.0));
			FindIterable<Document> result6 = movies.find(query6).projection(new Document("title", 1).append("imdb", 1))
					.limit(3);
			result6.forEach(doc -> System.out.println("rating mayor que 6 " + doc.toJson()));

			// Películas que tienen premios (campo "awards")
			// db.movies.find({ awards: { $exists: true } })

			Document query7 = new Document("awards", new Document("$exists", true));
			FindIterable<Document> result7 = movies.find(query7)
					.projection(new Document("title", 1).append("awards", 1)).limit(3);
			result7.forEach(doc -> System.out.println("existe awards " + doc.toJson()));

			FindIterable<Document> result7bis = movies.find(Filters.exists("awards"))
					.projection(new Document("title", 1).append("awards", 1)).limit(3);
			result7bis.forEach(doc -> System.out.println("existe awards " + doc.toJson()));

			MongoDatabase sampleGEO = mongoClient.getDatabase("sample_geospatial");
			MongoCollection<Document> shipwrecks = sampleGEO.getCollection("shipwrecks");

			// documentos en un radio de 10000 de las coordenadas -146.70, 60.90
			Bson query8 = Filters.nearSphere("coordinates", -146.70, 60.90, 10000.0, null);

			FindIterable<Document> results8 = shipwrecks.find(query8).limit(3);

			results8.forEach(doc -> System.out.println("Cerca de "+doc.toJson()));
			
		}
	}

}
