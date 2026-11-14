package jsonp;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonPointer;
import javax.json.JsonReader;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

public class ProgramaPointer2 {
	/*
	 * { 	"firstName": "John", 
	 * 		"lastName": "Smith", 
	 * 		"isAlive": true, 
 * 			"age": 27,
	 * 		"address": { 
	 * 				"streetAddress": "21 2nd Street", 
 * 					"city": "New York", 
 * 					"state": "NY", 
 * 					"postalCode": "10021-3100" 
 * 			}, 
 * 			"phoneNumbers": [ 
 * 								{ 	"type": "home",
	 * 								"number": "212 555-1234" 
	 * 							}, 
	 * 							{ 	"type": "office", 
	 * 								"number": "646 555-4567" 
	 * 							} 
	 * 						],
	 * 		"children": ["Catherine", "Thomas","Trevor"], 
	 * 		"spouse": null 
	 * }
	 */
	public static void main(String[] args) throws Exception {

		JsonReader reader = Json.createReader(new FileReader("json/ejemplo-wikipedia.json"));
		JsonObject jsonObj = reader.readObject();

		// Obtener el apellido
		JsonPointer jsonPointer = Json.createPointer("");

		// Obtener el código postal

		// Obtener el número de teléfono de casa

		// Obtener el nombre del segundo hijo

		// Verificar si spouse es null

		// Reemplazar el nombre "John" por "Jonathan"

		// Cambiar la ciudad de "New York" a "Boston"

		// Agregar un nuevo teléfono al final del array

		// Eliminar al hijo "Thomas"

		// Cambiar el número de teléfono de oficina a "646 000-0000"

		// Almacenamiento en disco

		HashMap<String, Boolean> config = new HashMap<String, Boolean>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);

		JsonGeneratorFactory factoriaGeneradores = Json.createGeneratorFactory(config);

		JsonGenerator generador = factoriaGeneradores.createGenerator(new FileWriter("json/ejemplo-modificado.json"));

		generador.write(jsonObj);
		generador.close();

		System.out.println("fin.");

	}
}
