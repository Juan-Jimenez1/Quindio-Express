package co.edu.uniquindio;

/**
 * Representa un paquete con su código, destino, peso, prioridad
 * y tiempo estimado. Valida los datos al crearse y permite
 * comparar paquetes por su código.
 */
public class Paquete implements Comparable<Paquete> {

    private final String codigo;
    private final Zona destino;
    private final double peso;
    private final Prioridad prioridad;
    private final int tiempoEstimado;

    /**
     * Crea un paquete y valida que sus datos sean correctos.
     *
     * @param codigo código identificador del paquete.
     * @param destino zona de destino del paquete.
     * @param peso peso del paquete.
     * @param prioridad prioridad asignada al paquete.
     * @param tiempoEstimado tiempo estimado de entrega.
     */
    public Paquete(String codigo, Zona destino, double peso,
                   Prioridad prioridad, int tiempoEstimado) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Codigo invalido");
        }
        if (destino == null) {
            throw new IllegalArgumentException("Destino invalido");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser positivo");
        }
        if (prioridad == null) {
            throw new IllegalArgumentException("Prioridad invalida");
        }
        if (tiempoEstimado <= 0) {
            throw new IllegalArgumentException("El tiempo debe ser positivo");
        }

        this.codigo = codigo;
        this.destino = destino;
        this.peso = peso;
        this.prioridad = prioridad;
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getCodigo() {
        return codigo;
    }

    public Zona getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    /**
     * Compara dos paquetes según sus códigos.
     *
     * @param otro paquete con el que se realiza la comparación.
     * @return resultado de la comparación alfabética de los códigos.
     */
    @Override
    public int compareTo(Paquete otro) {
        return this.codigo.compareTo(otro.codigo);
    }

    /**
     * Retorna una representación textual de los datos del paquete.
     */
    @Override
    public String toString() {
        return "Paquete{" +
                "codigo='" + codigo + '\'' +
                ", destino=" + destino +
                ", peso=" + peso +
                ", prioridad=" + prioridad.getValor() +
                ", tiempoEstimado=" + tiempoEstimado +
                '}';
    }
}
