package jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Programa1 {

	public static void main(String[] args) throws Exception {
		
		// Construir el contexto JAXB para las clases anotadas
		
		JAXBContext contexto = 
			JAXBContext.newInstance(Persona.class);
				
		Persona persona = new Persona();
		persona.setNombre("Juan");
		persona.setApellidos("González");
				
		// Empaquetado en un documento XML (marshalling)
				
		Marshaller marshaller = contexto.createMarshaller();
						
		marshaller.setProperty("jaxb.formatted.output", true);
				
		marshaller.marshal(persona, new File("xml/persona.xml"));
		
		// Cargamos el documento
		
		Unmarshaller unmarshaller = contexto.createUnmarshaller();
		
		Persona persona2 = (Persona) unmarshaller.unmarshal(new File("xml/persona.xml"));
		
		// Modifica el nombre
		persona2.setNombre("Juan Antonio");
		
		// Almacena los cambios en otro documento
		marshaller.marshal(persona2, new File("xml/persona2.xml"));
		
		
		System.out.println("fin.");
	}
}