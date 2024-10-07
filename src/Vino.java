public class Vino {
    String nombre;
    String tipo;
    String uva;
    String envejecimiento;
    String bodega;

    public Vino(String nombre, String tipo, String uva, String envejecimiento, String bodega) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.uva = uva;
        this.envejecimiento = envejecimiento;
        this.bodega = bodega;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEnvejecimiento() {
        return envejecimiento;
    }

    public String getUva() {
        return uva;
    }

    public String getBodega() {
        return bodega;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " Tipo: " + tipo + " Uva: " + uva + " Envejecimiento: "+envejecimiento+" Bodega: "+bodega;
    }
}
