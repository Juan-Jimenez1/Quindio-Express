package co.edu.uniquindio;



public class Repartidor {

    private final String identificacion;
    private final String nombre;
    private final Zona zona;
    private boolean disponible;

    public Repartidor(String identificacion, String nombre,
                      Zona zona) {

        if (identificacion == null || identificacion.isBlank()) {
            throw new IllegalArgumentException("Identificacion invalida");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre invalido");
        }
        if (zona == null) {
            throw new IllegalArgumentException("Zona invalida");
        }

        this.identificacion = identificacion;
        this.nombre = nombre;
        this.zona = zona;
        this.disponible = true;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Zona getZona() {
        return zona;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Repartidor{" +
                "identificacion='" + identificacion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", zona=" + zona +
                ", disponible=" + disponible +
                '}';
    }
}
