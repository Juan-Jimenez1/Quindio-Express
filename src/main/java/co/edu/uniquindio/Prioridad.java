package co.edu.uniquindio;

/**
 * Define los niveles de prioridad de los paquetes
 * y el valor numérico asociado a cada uno.
 */
public enum Prioridad {
    CINCO(5),
    CUATRO(4),
    TRES(3),
    DOS(2),
    UNO(1);

    private final int valor;

    /**
     * Asigna un valor numérico a la prioridad.
     *
     * @param valor valor asociado al nivel de prioridad.
     */
    Prioridad(int valor) {
        this.valor = valor;
    }

    /**
     * Retorna el valor numérico de la prioridad.
     *
     * @return valor de la prioridad.
     */
    public int getValor() {
        return valor;
    }
}

