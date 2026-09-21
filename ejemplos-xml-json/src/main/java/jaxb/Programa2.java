package jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import actas.modelo.Acta;

public class Programa2 {

	public static void main(String[] args) throws Exception {
		
		JAXBContext contexto = JAXBContext.newInstance(Acta.class);
		
		Marshaller marshaller = contexto.createMarshaller();
		
		marshaller.setProperty("jaxb.formatted.output", true);
		
		
	}
}
