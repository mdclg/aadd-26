package jsonp;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

public class ProgramaLeerGenerico {

	private static void leerValue(JsonValue valor) {
		ValueType tipo = valor.getValueType();
		if (tipo == ValueType.ARRAY) {
			leerArray((JsonArray) valor);
		} else if (tipo == ValueType.NUMBER || tipo == ValueType.STRING || tipo == ValueType.FALSE
				|| tipo == ValueType.TRUE) {
			System.out.println(valor);
		} else if (tipo == ValueType.OBJECT) {
			leerObject((JsonObject) valor);
		}
		else if(tipo == ValueType.NULL) {
			System.out.println("null");
		}
	}

	private static void leerArray(JsonArray ja) {
		System.out.println("[");
		for (int i = 0; i < ja.size(); i++) {
			JsonValue valor = ja.get(i);
			leerValue(valor);
		}
		System.out.println("]");
	}

	private static void leerObject(JsonObject obj) {

		Set<String> keys = obj.keySet();
		System.out.println("{");
		for (String k : keys) {
			JsonValue valor = obj.get(k);
			System.out.print(k+": ");
			leerValue(valor);
		}
		System.out.println("}");

	}

	public static void main(String[] args) throws Exception {

		InputStreamReader fuente = new FileReader("json/ejemplo-wikipedia.json");
		JsonReader jsonReader = Json.createReader(fuente);
		
		JsonStructure valor = jsonReader.read();
		leerValue(valor);
		

	}

}
