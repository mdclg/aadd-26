package xpath;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Programa {

	public static void main(String[] args) throws Exception {

		// 1. Obtener una factoría
		DocumentBuilderFactory factoriaDOM = DocumentBuilderFactory.newInstance();

		// 2. Pedir a la factoría la construcción del analizador
		DocumentBuilder analizador = factoriaDOM.newDocumentBuilder();

		// 3. Analizar el documento
		Document documento = analizador.parse("xml/acta.xml");

		/** XPath **/

		// 1. Obtener la factoría
		XPathFactory factoria = XPathFactory.newInstance();

		// 2. Construir el evaluador XPath
		XPath xpath = factoria.newXPath();

		// 3. Realizar una consulta
		
		ejecutarConsulta(documento, xpath, "//nif");
		
		/**
		 * Consultas XPath 
		 * - Todas las notas 
		 * - Las notas de las calificaciones (excluidas las diligencias) 
		 * - Todas las fechas de las diligencias 
		 * - Diligencias que tengan establecido el atributo "extraordinaria". 
		 * - Calificaciones con nota >= 9. 
		 * - Calificaciones o diligencias para el NIF "13322156M" 
		 * - Calificaciones cuyo NIF comience por "2". 
		 * - La segunda calificación
		 * - Obtener el nombre de los alumnos
		 * - Calificaciones que no tengan nombre de alumno
		 * - El nif de Pepe
		 * - Calificaciones con nota entre 5 y 10
		 * - Todos los atributos de acta
		 * - La nota más alta
		 **/

		
		
	}
	
	public static void ejecutarConsulta(Document documento, XPath xpath, String expresion) throws Exception{
		
		System.out.println("\nXPath: "+expresion);
		
		XPathExpression consulta = xpath.compile(expresion);
		
		NodeList resultado = (NodeList) consulta.evaluate(documento, XPathConstants.NODESET);

		// Recorro los resultados
		for (int i = 0; i < resultado.getLength(); i++) {

			Node nodo = resultado.item(i);

			System.out.println("Nodo: " + nodo.getNodeName());
			System.out.println("Contenido: " + nodo.getTextContent());
		}

		System.out.println("fin.");
	}
}
