package dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Main {

	public static void main(String[] args) throws Exception {

		// 1. Obtener una factoría
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		//factoria.setNamespaceAware(true);

		// 2. Pedir a la factoría la construcción del analizador
		DocumentBuilder analizador = factoria.newDocumentBuilder();

		// 3. Analizar el documento
		Document documento = analizador.parse("xml/acta.xml");

		// Ejemplo 2: creación de una diligencia

		Element diligencia = documento.createElement("diligencia");
		diligencia.setAttribute("fecha", "2023-09-02");

		Element nif = documento.createElement("nif");
		nif.setTextContent("22334312C");

		Element nota = documento.createElement("nota");
		nota.setTextContent("10");

		diligencia.appendChild(nif);
		diligencia.appendChild(nota);

		// Situarla en el árbol como último elemento del documento
		documento.getDocumentElement().appendChild(diligencia);

		// Ejemplo 1, creación de una calificación

		Element calificacion = documento.createElement("calificacion");
		nif = documento.createElement("nif");
		nif.setTextContent("22334312C");

		nota = documento.createElement("nota");
		nota.setTextContent("9");

		calificacion.appendChild(nif);
		calificacion.appendChild(nota);
		
		

		// La situamos en el documento antes de las diligencias.
		NodeList diligencias = documento.getElementsByTagName("diligencia");
		
		// Si no hay diligencias, se puede colocar al final del documento
		if (diligencias.getLength() == 0) {
			documento.getDocumentElement().appendChild(calificacion);
		} else {
			// Obtener como referencia la primera diligencia
			Element diligenciaReferencia = (Element) diligencias.item(0);
			System.out.println(diligenciaReferencia.getTagName());
			System.out.println(diligenciaReferencia.getLocalName());
			System.out.println(diligenciaReferencia.getNamespaceURI());
			Element padre = (Element) diligenciaReferencia.getParentNode();
			padre.insertBefore(calificacion, diligenciaReferencia);
		}
		
		// Guardar los cambios
		
		// 1. Construye la factoría de transformación y obtiene un 
		// transformador
		TransformerFactory tFactoria = TransformerFactory.newInstance(); 
		Transformer transformacion = tFactoria.newTransformer();
		
		// 2. Establece la entrada, como un árbol DOM 
		
		Source input = new DOMSource(documento);
		
		// 3. Establece la salida, un fichero en disco 
		Result output = new StreamResult("xml/acta2.xml");
		// 4. Aplica la transformación 
		
		transformacion.transform(input, output);
			
		System.out.println("fin.");
	}
}
