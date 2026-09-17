package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LeerActaHandler extends DefaultHandler {

	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("Invoca comienzo de documento");		
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("Invoca el final del documento");	
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		System.out.println("Entra al elemento " + qName );
		// como acceder a los atributos
		for (int i = 0; i < attributes.getLength(); i++) {
			String valor = attributes.getValue(i);
			String nombreAtributo = attributes.getQName(i);
			System.out.println("atributo " + nombreAtributo + " del elemento " + qName + " con valor " + valor);
		}			

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {		
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
			String texto = new String(ch, start, length);
			if(!texto.trim().isEmpty())
				System.out.println("Contenido textual "+texto);
			

	}

}
