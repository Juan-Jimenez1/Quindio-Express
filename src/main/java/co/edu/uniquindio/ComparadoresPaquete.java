package co.edu.uniquindio;


import java.util.Comparator;

public class ComparadoresPaquete {

    public static final Comparator<Paquete> POR_PRIORIDAD =
            Comparator.comparingInt(
                    (Paquete p) -> p.getPrioridad().getValor()
            ).reversed();

    public static final Comparator<Paquete> POR_PESO =
            Comparator.comparingDouble(Paquete::getPeso)
                    .reversed();

    public static final Comparator<Paquete> POR_TIEMPO =
            Comparator.comparingInt(Paquete::getTiempoEstimado);

    public static final Comparator<Paquete> PARA_DESPACHO =
            Comparator.comparingInt(
                            (Paquete p) -> p.getPrioridad().getValor()
                    )
                    .reversed()
                    .thenComparingInt(Paquete::getTiempoEstimado)
                    .thenComparing(Paquete::getCodigo);
}

