package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MediaActaHandler extends DefaultHandler {

	private int contadorCalificaciones;
	private double acumuladorNotas;
	private boolean isCalificacion;
	private boolean isNota;
	private double media;
	
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("Invoca comienzo de documento");	
		contadorCalificaciones = 0;
		acumuladorNotas = 0d;
		isCalificacion = false;
		isNota = false;
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("Invoca el final del documento");	
		media = acumuladorNotas / contadorCalificaciones;
		System.out.println("Media obtenida "+media);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		System.out.println("Entra al elemento " + localName );
		// como acceder a los atributos
		for (int i = 0; i < attributes.getLength(); i++) {
			String valor = attributes.getValue(i);
			String nombreAtributo = attributes.getQName(i);
			System.out.println("atributo " + nombreAtributo + " del elemento " + qName + " con valor " + valor);
		}

		if(qName.equals("acta")) {
			// se puede acceder a los atributos directamente por nombre
			String cursoValor = attributes.getValue("curso");
			System.out.println("cursoValor2 "+cursoValor);	
		}
		
		if(qName.equals("calificacion")) {
			isCalificacion = true;
			++contadorCalificaciones;
		}
		if(isCalificacion && qName.equals("nota")) {
			isNota = true;
		}
			

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("calificacion")) {
			isCalificacion = false;
		}
		else if(qName.equals("nota")) {
			isNota = false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
			if(isCalificacion && isNota) {
				String texto = new String(ch, start, length);
				Double d = Double.parseDouble(texto);
				acumuladorNotas +=d;
			}	

	}

}
