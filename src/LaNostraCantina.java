import java.io.*;
import java.util.ArrayList;

public class LaNostraCantina{
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

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        LaNostraCantina laNostraCantina = new LaNostraCantina();
        laNostraCantina.cargarRegionesCSV();
        //laNostraCantina.cargarVinosVinario();
        //aqui casca
        laNostraCantina.mostrarRegionesConsola();
    }
}
