package jsonp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

public class ProgramaCrearEjemplo {

	private static JsonObject crearAddress(String street, String city, String postalCode, String state) {
		return Json.createObjectBuilder().add("streetAddress", street).add("city", city).add("state", state)
				.add("postalCode", postalCode).build();
	}

	private static JsonObject crearTelefono(String type, String number) {
		return Json.createObjectBuilder().add("type", type).add("number", number).build();
	}

	private static JsonArray crearArrayString(List<String> hijos) {
		return Json.createArrayBuilder(hijos).build();
	}

	private static JsonArray crearTelefonos() {
		return Json.createArrayBuilder().add(crearTelefono("home", "212 555-1234"))
				.add(crearTelefono("office", "646 555-4567")).build();
	}

	/*
	 * { "firstName": "John", "lastName": "Smith", "isAlive": true, "age": 27,
	 * "address": { "streetAddress": "21 2nd Street", "city": "New York", "state":
	 * "NY", "postalCode": "10021-3100" }, "phoneNumbers": [ { "type": "home",
	 * "number": "212 555-1234" }, { "type": "office", "number": "646 555-4567" } ],
	 * "children": ["Catherine", "Thomas","Trevor"], "spouse": null }
	 */

	public static void main(String[] args) throws IOException {
		JsonObject ejemplo = Json.createObjectBuilder().add("firstName", "John").add("lastName", "Smith")
				.add("isAlive", true).add("age", 27)
				.add("address", crearAddress("21 2nd Street", "New York", "10021-3100", "NY"))
				.add("phoneNumbers", crearTelefonos())
				.add("children", crearArrayString(Arrays.asList("Catherine", "Thomas", "Trevor")))
				.add("spouse", JsonValue.NULL).build();

		// Almacenamiento en disco

		HashMap<String, Boolean> config = new HashMap<String, Boolean>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);

		JsonGeneratorFactory factoriaGeneradores = Json.createGeneratorFactory(config);

		JsonGenerator generador = factoriaGeneradores.createGenerator(new FileWriter("json/ejemplo.json"));

		generador.write(ejemplo);
		generador.close();

		System.out.println("fin.");

	}

}
