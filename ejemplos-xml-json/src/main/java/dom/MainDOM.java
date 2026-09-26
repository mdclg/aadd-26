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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MainDOM {

	public static void main(String[] args) throws Exception {

		// 1. Obtener una factoría
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();

		// 2. Pedir a la factoría la construcción del analizador
		DocumentBuilder analizador = factoria.newDocumentBuilder();

		// 3. Analizar el documento
		Document documento = analizador.parse("xml/acta.xml");

		// Obtener elemento raíz
		Element raiz = documento.getDocumentElement(); 
		
		// Leer atributos
		String convocatoria = raiz.getAttribute("convocatoria");
		String curso = raiz.getAttribute("curso");
		String asignatura = raiz.getAttribute("asignatura");

		System.out.println(
				"Acta de la asignatura " + asignatura + " del curso " + curso + " de la convocatoria " + convocatoria);

		NamedNodeMap atributoList = raiz.getAttributes();
		for (int i = 0; i < atributoList.getLength(); i++) {
			Node atributo = atributoList.item(i);
			System.out.println("atributo " + atributo.getNodeName() + " : " + atributo.getNodeValue());
		}
		
		// Obtener contenido textual

		System.out.println("CONTENIDO DEL RAIZ: " + raiz.getTextContent());

		// Obtener los hijos de un nodo

		NodeList hijosDeRaiz = raiz.getChildNodes();
		for (int i = 0; i < hijosDeRaiz.getLength(); i++) {
			Node nodoItem = hijosDeRaiz.item(i);
			if (nodoItem.getNodeType() == Node.ELEMENT_NODE) {
				Element elemento = (Element) nodoItem;
				System.out.println("Encontrado elemento " + elemento.getTagName());
			} else if (nodoItem.getNodeType() == Node.TEXT_NODE) {
				System.out.println("Encontrado texto");
			}
		}

		NodeList notasList = documento.getElementsByTagName("nota");
		for (int i = 0; i < notasList.getLength(); i++) {
			Node nota = notasList.item(i);
			System.out.println("Nota encontrada " + nota.getTextContent());
		}

		// Obtener notas pero solo de las calificaciones
		NodeList calificacionList = documento.getElementsByTagName("calificacion");
		for (int i = 0; i < calificacionList.getLength(); i++) {
			Node calificacion = calificacionList.item(i);
			NodeList hijos = calificacion.getChildNodes();
			for (int j = 0; j < hijos.getLength(); j++) {
				Node hijo = hijos.item(j);
				if (hijo.getNodeType() == Node.ELEMENT_NODE) {
					Element h = (Element) hijo;
					if (h.getTagName().equals("nota")) {
						System.out.println("Nota de la calificacion " + h.getTextContent());
					}
				}
			}
		}
		// Obtener las notas comprobando sus padres
		for (int i = 0; i < notasList.getLength(); i++) {
			Node nota = notasList.item(i);
			Node padre = nota.getParentNode();
			if (padre.getNodeName().equals("calificacion")) {
				System.out.println("Nota encontrada por padre " + nota.getTextContent());
			}
		}

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
