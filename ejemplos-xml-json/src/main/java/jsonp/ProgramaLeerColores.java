package jsonp;

import java.io.FileReader;
import java.io.InputStreamReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class ProgramaLeerColores {

	public static void main(String[] args) throws Exception {
		
		InputStreamReader fuente = new FileReader("json/colores.json");        
        JsonReader jsonReader = Json.createReader(fuente);
        
        JsonObject obj = jsonReader.readObject();
        
        // Obtenemos el array de colores
        
        JsonArray colores = obj.getJsonArray("colors");
        
        	
        for (JsonObject color : colores.getValuesAs(JsonObject.class)) {     	
       	        	
          	if (color.containsKey("type"))
                System.out.println("Type: " + color.getString("type"));

        	JsonArray rgba = color.getJsonObject("code").getJsonArray("rgba");
            
        	System.out.println(rgba);
        }

        System.out.println("fin.");
	}
}
