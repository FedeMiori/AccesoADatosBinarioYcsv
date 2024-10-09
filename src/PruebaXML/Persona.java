package PruebaXML;

public class Persona {
    private String nombre;
    private String apellidos;

    // Constructor
    public Persona(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
}
