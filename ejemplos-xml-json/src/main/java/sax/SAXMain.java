package sax;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXMain {
	
	public static void main(String[] args) throws Exception {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();

		LeerActaHandler handler = new LeerActaHandler();

		parser.parse(new File("xml/acta.xml"), handler);

	}	

}
