import java.util.ArrayList;

public class Region {
    String nombre;
    String pais;
    ArrayList<Vino> vinos = new ArrayList<>();

    public Region(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    public Region(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Vino> getVinos() {
        return vinos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public void añadirVino(Vino vino){
        vinos.add(vino);
    }

    @Override
    public String toString() {
        String region = "Region: "+nombre+", "+pais+"\n Vinos:\n";
        for (int i = 0; i < vinos.size(); i++) {
            region += " - "+vinos.get(i).getNombre()+"\n";
        }
        return region;
    }
}
