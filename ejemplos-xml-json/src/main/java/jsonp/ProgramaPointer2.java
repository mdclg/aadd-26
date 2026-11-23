package jsonp;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonPointer;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

public class ProgramaPointer2 {

	/*
	 * {
  "firstName": "John",
  "lastName": "Smith",
  "isAlive": true,
  "age": 27,
  "address": {
    "streetAddress": "21 2nd Street",
    "city": "New York",
    "state": "NY",
    "postalCode": "10021-3100"
  },
  "phoneNumbers": [
    {
      "type": "home",
      "number": "212 555-1234"
    },
    {
      "type": "office",
      "number": "646 555-4567"
    }
  ],
  "children": ["Catherine", "Thomas","Trevor"],
  "spouse": null
}
	 */
	public static void main(String[] args) throws Exception {

		JsonReader reader = Json.createReader(new FileReader("json/ejemplo-wikipedia.json"));
		JsonObject jsonObj = reader.readObject();

		// Obtener el apellido 
		JsonPointer pointer = Json.createPointer("/lastName");
		JsonString jsonString = (JsonString) pointer.getValue(jsonObj);
		System.out.println("apellido "+jsonString.getString());
		
		// Obtener el código postal
		pointer = Json.createPointer("/address/postalCode");
		jsonString = (JsonString) pointer.getValue(jsonObj);
		System.out.println("código postal "+jsonString.getString());

		// Obtener el número de teléfono de casa
		
		pointer = Json.createPointer("/phoneNumbers/0/number");
		jsonString = (JsonString) pointer.getValue(jsonObj);
		System.out.println("tlf de casa "+jsonString.getString());
		
		// alternativa:
		pointer = Json.createPointer("/phoneNumbers");
		JsonArray phones = (JsonArray) pointer.getValue(jsonObj);
		for(int i = 0;i<phones.size();i++) {
			JsonObject phone = phones.getJsonObject(i);
			if(phone.getString("type").equals("home")) {
				System.out.println("tlf de casa "+phone.getString("number"));
			}
		}
		
		// Obtener el nombre del segundo hijo
		pointer = Json.createPointer("/children/1");
		jsonString = (JsonString) pointer.getValue(jsonObj);
		System.out.println("2º hijo "+jsonString.getString());
		
		// Verificar si spouse es null
		pointer = Json.createPointer("/spouse");
		if(pointer.getValue(jsonObj) == JsonValue.NULL) {
			System.out.println("Esposa es nulo");
		}
		
		// Reemplazar el nombre "John" por "Jonathan"
		pointer = Json.createPointer("/firstName");
		JsonObject modificado = pointer.replace(jsonObj, Json.createValue("Jonathan"));
		
		// Cambiar la ciudad de "New York" a "Boston"
		pointer = Json.createPointer("/address/city");
		modificado = pointer.replace(modificado, Json.createValue("Boston"));
		
		// Agregar un nuevo teléfono al final del array
		pointer = Json.createPointer("/phoneNumbers/-");
		JsonObject nuevoTlf = Json.createObjectBuilder().add("type", "home").add("number", "555-555-555").build();
		modificado = pointer.add(modificado, nuevoTlf);
		
		// Eliminar al hijo "Thomas"
		pointer = Json.createPointer("/children/1");
		modificado = pointer.remove(modificado);
		
		// Cambiar el número de teléfono de oficina a "646 000-0000"
		pointer = Json.createPointer("/phoneNumbers/1/number");
		modificado = pointer.replace(modificado, Json.createValue("646 000-0000"));
		
		// Almacenamiento en disco

		HashMap<String, Boolean> config = new HashMap<String, Boolean>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);

		JsonGeneratorFactory factoriaGeneradores = Json.createGeneratorFactory(config);

		JsonGenerator generador = factoriaGeneradores.createGenerator(new FileWriter("json/ejemplo-modificado-pointer.json"));

		generador.write(modificado);
		generador.close();

		System.out.println("fin.");

	}
}
