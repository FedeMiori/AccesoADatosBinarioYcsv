import java.util.ArrayList;
import java.io.*;

public class GuardarVinoBinario implements Serializable{
    String nombre;
    String region;
    String tipo;
    String uva;
    String envejecimiento;
    String bodega;

    public GuardarVinoBinario(String nombre, String region, String tipo, String uva, String envejecimiento, String bodega) {
        this.nombre = nombre;
        this.region = region;
        this.tipo = tipo;
        this.uva = uva;
        this.envejecimiento = envejecimiento;
        this.bodega = bodega;
    }

    public static boolean escribirEnFichero(ArrayList<GuardarVinoBinario> lista) throws IOException {

        File fichero = new File("binarioVinos.dat");//declara el fichero
        FileOutputStream fileout = new FileOutputStream(fichero,true);  //crea el flujo de salida
        //conecta el flujo de bytes al flujo de datos
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        System.out.println("GRABAMOS LOS DATOS DE LOS VINOS");
        for (int i=0;i<lista.size(); i++){ //recorro los arrays
            dataOS.writeObject( lista.get(i) ); //escribo la persona en el fichero
            System.out.println("GRABADO EL "+i+" DATO DE VINOS");
        }
        dataOS.close();
        return true;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<GuardarVinoBinario> listaVinos = new ArrayList<>();
        listaVinos.add(
                new GuardarVinoBinario("barolo",
                        "Piamonte",
                        "Tinto",
                        "Nebbiolo",
                        "24 meses en barrica",
                        "Marchesi di Barolo")
        );
        listaVinos.add(
                new GuardarVinoBinario("Brunello di Montalcino",
                        "Toscana",
                        "Tinto",
                        "Sangiovese",
                        "36 meses en roble",
                        "Biondi Santi")
        );
        listaVinos.add(
                new GuardarVinoBinario("Chianti Classico Riserva",
                        "Toscana",
                        "Tinto",
                        "Sangiovese (mayoritaria), Canaiolo",
                        "24 meses",
                        "Antinori")
        );
        listaVinos.add(
                new GuardarVinoBinario("Amarone della Valpolicella",
                        "Veneto",
                        "Tinto",
                        "Corvina, Rondinella, Molinara",
                        "48 meses en barrica",
                        "Masi")
        );
        listaVinos.add(
                new GuardarVinoBinario("Prosecco di Valdobbiadene",
                        "Veneto",
                        "Espumoso",
                        "Glera",
                        "Charmat",
                        "Nino Franco")
        );
        escribirEnFichero(listaVinos);
    }
}


