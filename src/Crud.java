import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.io.File;

public class Crud {
    public static void create(){
        //TO DO
    }
    public static void delete(){
        //TO DO
    }
    public static void read(){
        //TO DO
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
            doc.getDocumentElement().normalize();

            // Obtiene todas las etiquetas Región
            NodeList nList = doc.getElementsByTagName("Región");

            // Iterar sobre las regiones
            for (int i = 0; i < nList.getLength(); i++) {
                Element elementoRegion = (Element) nList.item(i);
                String id = elementoRegion.getAttribute("id");

                if (id.contains("ri")) {
                    String nombreRegion = elementoRegion.getElementsByTagName("nombre").item(0).getTextContent();
                    System.out.println(nombreRegion);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Create:\n");
        create();
        System.out.println("Read:\n");
        read();
        System.out.println("Update:\n");
        update();
        System.out.println("Delete:\n");
        delete();
        System.out.println("Consulta Ap 6:\n");
        consultaAp6();
    }
}
