package jsonb;

import java.io.FileReader;
import java.io.PrintStream;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.json.bind.spi.JsonbProvider;

public class Programa {

	public static void main(String[] args) throws Exception {

		// Construcción del contexto
		Jsonb contexto = JsonbProvider.provider().create().build();

		Persona persona = new Persona("Pepe", null, 40, 1200); // sin email
		
		String cadenaJSON = contexto.toJson(persona);

		System.out.println("Persona: " + cadenaJSON);
		
		JsonbConfig config = new JsonbConfig()
                .withNullValues(true)
                .withFormatting(true)
                .withPropertyNamingStrategy(
					PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES)
                .withPropertyOrderStrategy(
					PropertyOrderStrategy.LEXICOGRAPHICAL);   
    	
		Jsonb contexto2 = JsonbProvider.provider().create().withConfig(config).build();

		String cadenaJSON2 = contexto2.toJson(persona);

		System.out.println("Persona v2: " + cadenaJSON2);

		contexto2.toJson(persona, new PrintStream("json/persona.json"));
		
		// Lectura de un documento
	    
	    Persona persona2 = contexto.fromJson(new FileReader("json/persona.json"), Persona.class);

		System.out.println("Documento persona: " + contexto.toJson(persona2));
		
		System.out.println("fin.");
		
	}
}