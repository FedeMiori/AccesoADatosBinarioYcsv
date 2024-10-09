import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class LaNostraCantina implements Serializable{
    private ArrayList<Region> regioniItaliane = new ArrayList<>();

    public LaNostraCantina(){

    }

    public void mostrarRegionesConsola(){
        for (int i = 0; i < regioniItaliane.size(); i++) {
            System.out.println(regioniItaliane.get(i));
        }
    }

    public void cargarRegionesCSV(){
        try (BufferedReader br = new BufferedReader(new FileReader("Regiones.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Divide la línea en un array de strings por las comas
                String[] valores = linea.split(",");
                regioniItaliane.add( new Region( valores[0],valores[1] ) );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarVinosVinario() throws IOException, ClassNotFoundException {
        File fichero = new File("binarioVinos.dat");
        ObjVinoBinario vinoBinario;
        ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero));
        try {
            while (true) { // lectura del fichero
                vinoBinario = (ObjVinoBinario) dataIS.readObject(); // leer una Vino
                String nombreRegion = vinoBinario.getRegion();
                Region region= buscarRegion(nombreRegion);
                Vino vino = new Vino(vinoBinario.getNombre(),vinoBinario.getTipo(),vinoBinario.getUva(),vinoBinario.getEnvejecimiento(),vinoBinario.getBodega());
                region.añadirVino(vino);
            }
        } catch (EOFException eo) {
            System.out.println("FIN DE LECTURA.");
        } catch (StreamCorruptedException x) {
        }

        dataIS.close(); // cerrar stream de entrada
    }

    public Region buscarRegion(String nombreRegion){
        boolean encontrado=false;
        int i=0;
        Region resultado = null;
        while(!encontrado && i < regioniItaliane.size()){
            if(nombreRegion.equals(regioniItaliane.get(i).getNombre())){
                encontrado=true;
                resultado = regioniItaliane.get(i);
            }
            i++;
        }
        return resultado;
    }

    public void crearXML(){
        try{
            // Paso 1: Inicializar el creador de documentos XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            // Paso 2: Crear el documento XML con la raíz "Personas"
            Document document = implementation.createDocument(null, "VinosItalianos", null);
            document.setXmlVersion("1.0");

            for (int i = 0; i < regioniItaliane.size(); i++) {
                Element elementoRegion = document.createElement("Región");
                elementoRegion.setAttribute("id", "ri"+String.valueOf(i));
                document.getDocumentElement().appendChild(elementoRegion);

                Region region = regioniItaliane.get(i);
                CrearElemento("nombre", region.getNombre(), elementoRegion, document);
                CrearElemento("pais", region.getPais(), elementoRegion, document);

                Element elementoVinos = document.createElement("Vinos");
                elementoRegion.appendChild(elementoVinos);
                document.getDocumentElement().appendChild(elementoRegion);

                ArrayList<Vino> vinos = region.getVinos();
                for (int j = 0; j < vinos.size(); j++) {
                    Vino vino = vinos.get(j);
                    Element elementoVinoConcreto = document.createElement("Vino");
                    elementoVinos.appendChild(elementoVinoConcreto);
                    CrearElemento("nombre", vino.getNombre(), elementoVinoConcreto, document);
                    CrearElemento("tipo", vino.getTipo(), elementoVinoConcreto, document);
                    CrearElemento("uva", vino.getUva(), elementoVinoConcreto, document);
                    CrearElemento("envejecimiento", vino.getEnvejecimiento(), elementoVinoConcreto, document);
                    CrearElemento("bodega", vino.getBodega(), elementoVinoConcreto, document);
                }
            }

            // Paso 4: Configurar el transformador para escribir el archivo XML
            Source source = new DOMSource(document);
            Result result = new StreamResult(new File("vinos.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

            System.out.println("Archivo XML generado correctamente.");
        }catch (Exception e) {
            //System.out.println("\nERROR: Hubo un problema al generar el xml");
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

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        LaNostraCantina cantina = new LaNostraCantina();
        cantina.cargarRegionesCSV();
        cantina.cargarVinosVinario();
        cantina.mostrarRegionesConsola();

        cantina.crearXML();
    }
}
