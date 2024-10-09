package PruebaXML;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class PruebaXML {

    public static void main(String[] args) {
        // Crear un objeto Persona
        Persona persona = new Persona("Juan", "Pérez García");

        // Crear el XML a partir del objeto Persona
        try {
            // Paso 1: Inicializar el creador de documentos XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            // Paso 2: Crear el documento XML con la raíz "Personas"
            Document document = implementation.createDocument(null, "Personas", null);
            document.setXmlVersion("1.0");

            // Paso 3: Crear un nodo "persona" y añadirlo al documento
            Element raiz = document.createElement("persona");
            document.getDocumentElement().appendChild(raiz);

            // Añadir nombre y apellidos al nodo "persona"
            CrearElemento("nombre", persona.getNombre(), raiz, document);
            CrearElemento("apellidos", persona.getApellidos(), raiz, document);

            // Paso 4: Configurar el transformador para escribir el archivo XML
            Source source = new DOMSource(document);
            Result result = new StreamResult(new File("Persona.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

            System.out.println("Archivo XML generado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metodo para crear y añadir elementos al XML
    static void CrearElemento(String nombreElemento, String valor, Element raiz, Document document) {
        Element elem = document.createElement(nombreElemento);  // Crear el elemento
        Text text = document.createTextNode(valor);  // Crear el valor del elemento
        raiz.appendChild(elem);  // Añadir el elemento a la raíz
        elem.appendChild(text);  // Añadir el valor al elemento
    }
}