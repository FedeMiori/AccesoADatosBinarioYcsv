import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import java.io.File;

public class Crud {
    public static void create(){
        String nombreRegionAñadir = "Bolzano";
        try{
            File inputFile = new File("vinos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document documento = dBuilder.parse(inputFile);

            Element elementoRegion = documento.createElement("Región");
            elementoRegion.setAttribute("id", "ri"+4);
            documento.getDocumentElement().appendChild(elementoRegion);

            CrearElemento("nombre", nombreRegionAñadir, elementoRegion, documento);
            CrearElemento("pais", "Italia", elementoRegion, documento);

            Element elementoVinos = documento.createElement("Vinos");
            elementoRegion.appendChild(elementoVinos);
            documento.getDocumentElement().appendChild(elementoRegion);

            //Configuramos el transformador para escribir el archivo XML
            Source source = new DOMSource(documento);
            Result result = new StreamResult(new File("vinos.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

            System.out.println("Region: "+nombreRegionAñadir+" creado correctamente");
        } catch (Exception e) {
            System.out.println("Hubo un error al agregar la region");
        }
    }

    public static void delete(){
        try {
            File inputFile = new File("vinos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            NodeList listaVinos = doc.getElementsByTagName("Vino");
            String aEliminar = "Barolo";


            boolean encontrado = false;
            for (int i = 0; i < listaVinos.getLength(); i++) {
                Element elementoVino = (Element) listaVinos.item(i);
                String nombreVino = elementoVino.getAttribute("vino");
                if(nombreVino.equals(aEliminar)){
                    encontrado = true;
                    elementoVino.getParentNode().removeChild(elementoVino);
                    System.out.println("Vino: "+aEliminar+"Borrado correctamente");
                }
            }

            if(!encontrado)
                System.out.println("El vino "+aEliminar+" no existe.\nya fue borrado previamente");

        }catch (Exception e){
            System.out.println("OPERACCIÓN FALLIDA");
            e.printStackTrace();
        }
    }

    public static void read(){
        try{
            File inputFile = new File("vinos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            NodeList listaVinos = doc.getElementsByTagName("Vino");

            System.out.println("Nuestros Vinos:");
            for (int i = 0; i < listaVinos.getLength(); i++) {
                Node nodoVino = listaVinos.item(i);

                if (nodoVino.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoVino = (Element) nodoVino;
                    String mombreVino = elementoVino.getElementsByTagName("vino").item(0).getTextContent();
                    System.out.println(" - "+mombreVino);
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: Hubo un problema en la lectura");
            //e.printStackTrace();
        }
    }

    public static void update(){
        //TO DO
    }

    public static void consultaAp6(){
        try {
            File inputFile = new File("vinos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            NodeList listaRegiones = doc.getElementsByTagName("Región");

            for (int i = 0; i < listaRegiones.getLength(); i++) {
                Element elementoRegion = (Element) listaRegiones.item(i);
                String id = elementoRegion.getAttribute("id");

                if (id.contains("ri")) {
                    String nombreRegion = elementoRegion.getElementsByTagName("nombre").item(0).getTextContent();
                    System.out.println(nombreRegion);
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("ERROR: Hubo un error en la consulta");
        }
    }

    // Metodo para crear y añadir elementos al XML
    static void CrearElemento(String nombreElemento, String valor, Element raiz, Document document) {
        Element elem = document.createElement(nombreElemento);  // Crear el elemento
        Text text = document.createTextNode(valor);  // Crear el valor del elemento
        raiz.appendChild(elem);  // Añadir el elemento a la raíz
        elem.appendChild(text);  // Añadir el valor al elemento
    }

    public static void main(String[] args) {
        System.out.println("Create:\n");
        create();
        System.out.println("\nRead:\n");
        read();
        System.out.println("\nUpdate:\n");
        update();
        System.out.println("\nDelete:\n");
        delete();
        System.out.println("\nConsulta Ap 6:\n");
        consultaAp6();
    }
}
