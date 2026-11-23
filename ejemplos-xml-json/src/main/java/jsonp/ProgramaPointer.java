package jsonp;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonPointer;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

public class ProgramaPointer {

	public static void main(String[] args) throws Exception {

		JsonReader reader = Json.createReader(new FileReader("json/colores.json"));
		JsonObject jsonObj = reader.readObject();

		JsonPointer pointer = Json.createPointer("/colors/1/color");
		JsonString jsonString = (JsonString) pointer.getValue(jsonObj);
		System.out.println(jsonString.getString());

		pointer = Json.createPointer("/colors/1/type");
		boolean found = pointer.containsValue(jsonObj);
		System.out.println(found);

		pointer = Json.createPointer("/colors/1/color");
		JsonObject modificado = pointer.replace(jsonObj, Json.createValue("blue"));

		pointer = Json.createPointer("/colors/1/code/rgba/0");
		modificado = pointer.add(modificado, Json.createValue("intruso"));

		pointer = Json.createPointer("/colors/1/code/rgba/0");
		modificado = pointer.remove(modificado);

		pointer = Json.createPointer("/colors/1/brillo");
		modificado = pointer.add(modificado, JsonValue.FALSE);

		// Almacenamiento en disco

		HashMap<String, Boolean> config = new HashMap<String, Boolean>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);

		JsonGeneratorFactory factoriaGeneradores = Json.createGeneratorFactory(config);

		JsonGenerator generador = factoriaGeneradores.createGenerator(new FileWriter("json/coloresModificado.json"));

		generador.write(modificado);
		generador.close();

		System.out.println("fin.");

	}

}
