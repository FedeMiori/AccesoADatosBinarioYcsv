import java.util.ArrayList;
import java.io.*;

public class ObjVinoBinario implements Serializable{
    String nombre;
    String region;
    String tipo;
    String uva;
    String envejecimiento;
    String bodega;

    public ObjVinoBinario(String nombre, String region, String tipo, String uva, String envejecimiento, String bodega) {
        this.nombre = nombre;
        this.region = region;
        this.tipo = tipo;
        this.uva = uva;
        this.envejecimiento = envejecimiento;
        this.bodega = bodega;
    }

    public String getNombre() {
        return nombre;
    }
    public String getRegion() {
        return region;
    }
    public String getTipo() {
        return tipo;
    }
    public String getUva() {
        return uva;
    }
    public String getEnvejecimiento() {
        return envejecimiento;
    }

    public String getBodega() {
        return bodega;
    }

    public static boolean escribirEnFicheroBinario(ArrayList<ObjVinoBinario> lista) throws IOException {

        File fichero = new File("binarioVinos.dat");//declara el fichero
        FileOutputStream fileout = new FileOutputStream(fichero,true);  //crea el flujo de salida
        //conecta el flujo de bytes al flujo de datos
        ObjectOutputStream dataOS = new ObjectOutputStream(fileout);

        System.out.println("GRABAMOS LOS DATOS DE LOS VINOS");
        for (int i=0;i<lista.size(); i++){ //recorro los arrays
            dataOS.writeObject( lista.get(i) ); //escribo la vino en el fichero
            System.out.println("GRABADO EL "+i+" DATO DE VINOS");
        }
        dataOS.close();
        return true;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<ObjVinoBinario> listaVinos = new ArrayList<>();
        listaVinos.add(
                new ObjVinoBinario("barolo",
                        "Piamonte",
                        "Tinto",
                        "Nebbiolo",
                        "24 meses en barrica",
                        "Marchesi di Barolo")
        );
        listaVinos.add(
                new ObjVinoBinario("Brunello di Montalcino",
                        "Toscana",
                        "Tinto",
                        "Sangiovese",
                        "36 meses en roble",
                        "Biondi Santi")
        );
        listaVinos.add(
                new ObjVinoBinario("Chianti Classico Riserva",
                        "Toscana",
                        "Tinto",
                        "Sangiovese (mayoritaria), Canaiolo",
                        "24 meses",
                        "Antinori")
        );
        listaVinos.add(
                new ObjVinoBinario("Amarone della Valpolicella",
                        "Veneto",
                        "Tinto",
                        "Corvina, Rondinella, Molinara",
                        "48 meses en barrica",
                        "Masi")
        );
        listaVinos.add(
                new ObjVinoBinario("Prosecco di Valdobbiadene",
                        "Veneto",
                        "Espumoso",
                        "Glera",
                        "Charmat",
                        "Nino Franco")
        );
        escribirEnFicheroBinario(listaVinos);
    }
}


