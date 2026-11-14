package jsonschema;

import java.io.File;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

public class Programa {

	public static void main(String[] args) throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		// Cargar el esquema JSON
		File schemaFile = new File("json/schema.json");
		JsonNode schemaNode = objectMapper.readTree(schemaFile);

		JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
		JsonSchema schema = schemaFactory.getSchema(schemaNode);

		// Cargar el documento JSON

		File jsonFile = new File("json/colores.json");
		JsonNode jsonNode = objectMapper.readTree(jsonFile);

		// Validar el documento JSON contra el esquema
		Set<ValidationMessage> errors = schema.validate(jsonNode);

		// Procesar resultados
		if (errors.isEmpty()) {
			System.out.println("Validación exitosa: El documento JSON es válido.");
		} else {
			System.out.println("Errores de validación:");
			errors.forEach(error -> System.out.println("- " + error.getMessage()));
		}

	}
}
