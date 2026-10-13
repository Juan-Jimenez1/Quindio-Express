package co.edu.uniquindio;

import java.util.Comparator;

/**
 * Define los criterios de comparación para ordenar los paquetes
 * según su prioridad, peso, tiempo estimado y despacho.
 */
public class ComparadoresPaquete {

    /**
     * Ordena por prioridad descendente, tiempo ascendente
     * y código alfabético.
     */
    public static final Comparator<Paquete> POR_PRIORIDAD =
            Comparator.comparingInt(
                            (Paquete p) -> p.getPrioridad().getValor()
                    )
                    .reversed()
                    .thenComparingInt(Paquete::getTiempoEstimado)
                    .thenComparing(Paquete::getCodigo);

    /**
     * Ordena los paquetes por peso de mayor a menor.
     */
    public static final Comparator<Paquete> POR_PESO =
            Comparator.comparingDouble(Paquete::getPeso)
                    .reversed();

    /**
     * Ordena los paquetes por tiempo estimado de menor a mayor.
     */
    public static final Comparator<Paquete> POR_TIEMPO =
            Comparator.comparingInt(Paquete::getTiempoEstimado);

    /**
     * Define el orden de despacho según prioridad descendente,
     * tiempo ascendente y código alfabético.
     */
    public static final Comparator<Paquete> PARA_DESPACHO =
            Comparator.comparingInt(
                            (Paquete p) -> p.getPrioridad().getValor()
                    )
                    .reversed()
                    .thenComparingInt(Paquete::getTiempoEstimado)
                    .thenComparing(Paquete::getCodigo);
}
