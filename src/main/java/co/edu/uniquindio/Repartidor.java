package co.edu.uniquindio;

/**
 * Representa un repartidor con su identificación, nombre,
 * zona asignada y estado de disponibilidad.
 */
public class Repartidor {

    private final String identificacion;
    private final String nombre;
    private final Zona zona;
    private boolean disponible;

    /**
     * Crea un repartidor y valida sus datos.
     * Inicialmente, el repartidor queda disponible.
     *
     * @param identificacion identificación del repartidor.
     * @param nombre nombre del repartidor.
     * @param zona zona asignada al repartidor.
     */
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

    /**
     * Actualiza el estado de disponibilidad del repartidor.
     *
     * @param disponible indica si el repartidor está disponible.
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Retorna una representación textual de los datos del repartidor.
     */
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
