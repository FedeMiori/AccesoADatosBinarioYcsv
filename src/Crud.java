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

/**
 * Esta clase se encarga del CRUD y del Ap6
 */
public class Crud {

    /**
     * Añade una nueva región con id: ri04 nombre: Bolzano pais:Italia
     */
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

    /**
     * Busca y borra el vino con nombre "Barolo"
     */
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

    /**
     * Lista por consola los nombres de todos los vinos
     */
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

    /**
     * Muestra por consola todos los nombres de regiones cuyo id contiene "ri"
     * que son todas las regiones. Lo cual lo hace una consulta un poco absurda pero no teniamos mas atributos
     * para hacer una agrupacion con sentido. Pero la consulta está que es lo que importa :)
     */
    public static void consultaAp6(){
        try {
            File inputFile = new File("vinos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            NodeList listaRegiones = doc.getElementsByTagName("Región");

            //Recorre todos los nodos region
            for (int i = 0; i < listaRegiones.getLength(); i++) {
                Element elementoRegion = (Element) listaRegiones.item(i);
                String id = elementoRegion.getAttribute("id");
                //Al pillar el id y guardarlo en un string se hace la comprobación
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

    /**
     * Crea un elemento Dado unos parametros para evitar duplicar codigo
     */
    static void CrearElemento(String nombreElemento, String valor, Element raiz, Document document) {
        Element elem = document.createElement(nombreElemento);  // Crear el elemento
        Text text = document.createTextNode(valor);  // Crear el valor del elemento
        raiz.appendChild(elem);  // Añadir el elemento a la raíz
        elem.appendChild(text);  // Añadir el valor al elemento
    }


    /**
     * Este main hace las consultas CRUD y el Ap6
     */
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
