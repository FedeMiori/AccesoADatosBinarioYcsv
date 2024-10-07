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
        return "Region{" +
                "nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", vinos=" + vinos +
                '}';
    }
}
