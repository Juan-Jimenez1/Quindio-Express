package co.edu.uniquindio;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        QuindioExpress sistema = new QuindioExpress();

        registrarRepartidores(sistema);
        registrarPaquetes(sistema);

        System.out.println("====================================");
        System.out.println("       SISTEMA QUINDIOEXPRESS");
        System.out.println("====================================");

        System.out.println("\n--- RESUMEN ---");
        System.out.println("Paquetes: " + sistema.cantidadPaquetes());
        System.out.println("Repartidores: "
                + sistema.cantidadRepartidores());

        System.out.println("\n--- ORDEN ORIGINAL DE REGISTRO ---");
        for (Paquete paquete : sistema.obtenerOrdenRegistro()) {
            System.out.println(paquete.getCodigo());
        }

        System.out.println("\n--- BÚSQUEDA CON HASHMAP ---");
        System.out.println(sistema.buscarPaquete("PQ731"));
        System.out.println("Código inexistente: "
                + sistema.buscarPaquete("PQ000"));

        System.out.println("\n--- BÚSQUEDA BINARIA ---");
        System.out.println(
                sistema.busquedaBinariaPorCodigo("PQ731")
        );
        System.out.println("Código inexistente: "
                + sistema.busquedaBinariaPorCodigo("PQ000"));

        System.out.println("\n--- ORDEN POR PRIORIDAD ---");
        for (Paquete paquete : sistema.ordenarPaquetes(
                ComparadoresPaquete.POR_PRIORIDAD)) {
            System.out.println(
                    paquete.getCodigo() + " - "
                            + paquete.getPrioridad().getValor()
            );
        }

        System.out.println("\n--- ORDEN POR PESO ---");
        for (Paquete paquete : sistema.ordenarPaquetes(
                ComparadoresPaquete.POR_PESO)) {
            System.out.println(
                    paquete.getCodigo() + " - "
                            + paquete.getPeso() + " kg"
            );
        }

        System.out.println("\n--- ORDEN POR TIEMPO ---");
        for (Paquete paquete : sistema.ordenarPaquetes(
                ComparadoresPaquete.POR_TIEMPO)) {
            System.out.println(
                    paquete.getCodigo() + " - "
                            + paquete.getTiempoEstimado() + " min"
            );
        }

        System.out.println("\n--- DESTINOS ÚNICOS ---");
        System.out.println(sistema.obtenerDestinosRegistrados());

        System.out.println("\n--- DESTINOS ORDENADOS ---");
        System.out.println(sistema.obtenerDestinos());

        System.out.println("\n--- PAQUETES POR DESTINO ---");
        for (Zona zona : sistema.obtenerDestinos()) {
            System.out.println("\n" + zona + ":");

            for (Paquete paquete :
                    sistema.obtenerPaquetesPorDestino(zona)) {
                System.out.println(paquete.getCodigo());
            }
        }

        System.out.println("\n--- PESO TOTAL POR DESTINO ---");
        for (Zona zona : sistema.obtenerDestinos()) {
            System.out.println(
                    zona + ": "
                            + sistema.calcularPesoTotalPorDestino(zona)
                            + " kg"
            );
        }

        System.out.println("\n--- PRIORIDAD MÍNIMA 4 ---");
        System.out.println(
                "Cantidad: "
                        + sistema.contarPaquetesConPrioridadMinima(
                        Prioridad.CUATRO
                )
        );

        System.out.println("\n--- PAQUETE MÁS PESADO ---");
        System.out.println(
                sistema.obtenerPaqueteDeMayorPeso()
        );

        System.out.println("\n--- ATENCIÓN FIFO ---");
        System.out.println(
                "Siguiente: "
                        + sistema.consultarSiguientePorLlegada()
        );

        Paquete atendidoFIFO =
                sistema.atenderSiguientePorLlegada();

        System.out.println(
                "Asignado por FIFO: " + atendidoFIFO
        );

        System.out.println("\n--- DESPACHO PRIORITARIO ---");
        System.out.println(
                "Siguiente: "
                        + sistema.consultarSiguientePorPrioridad()
        );

        Paquete despachado =
                sistema.despacharSiguientePorPrioridad();

        System.out.println("\n--- HISTORIAL ANTES DE CONFIRMAR ---");
        sistema.mostrarHistorialEntregas();

        System.out.println("\n--- CONFIRMAR ENTREGA ---");
        if (despachado != null) {
            sistema.confirmarEntrega(despachado.getCodigo());
        }

        if (atendidoFIFO != null) {
            sistema.confirmarEntrega(atendidoFIFO.getCodigo());
        }

        System.out.println("\n--- HISTORIAL DESPUÉS DE CONFIRMAR ---");
        sistema.mostrarHistorialEntregas();

        System.out.println("\n--- SEGUNDA CONFIRMACIÓN ---");
        if (despachado != null) {
            sistema.confirmarEntrega(despachado.getCodigo());
        }

        System.out.println("\n--- LIBERAR REPARTIDOR ---");
        sistema.liberarRepartidor("R001");
        System.out.println(sistema.buscarRepartidor("R001"));

        System.out.println("\n--- PRUEBA: PAQUETE DUPLICADO ---");
        try {
            sistema.registrarPaquete(
                    new Paquete(
                            "PQ731",
                            Zona.ARMENIA,
                            2.0,
                            Prioridad.TRES,
                            20
                    )
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Validación correcta: " + e.getMessage());
        }

        System.out.println("\n--- PRUEBA: REPARTIDOR DUPLICADO ---");
        try {
            sistema.registrarRepartidor(
                    new Repartidor(
                            "R001",
                            "Otro repartidor",
                            Zona.ARMENIA
                    )
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Validación correcta: " + e.getMessage());
        }

        System.out.println("\n--- PRUEBA: PESO INVÁLIDO ---");
        try {
            sistema.registrarPaquete(
                    new Paquete(
                            "PQ999",
                            Zona.ARMENIA,
                            -3.0,
                            Prioridad.TRES,
                            20
                    )
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Validación correcta: " + e.getMessage());
        }

        System.out.println("\n--- PRUEBA: TIEMPO INVÁLIDO ---");
        try {
            sistema.registrarPaquete(
                    new Paquete(
                            "PQ998",
                            Zona.ARMENIA,
                            3.0,
                            Prioridad.TRES,
                            0
                    )
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Validación correcta: " + e.getMessage());
        }

        System.out.println("\n--- PRUEBA: LISTA ENLAZADA ---");
        probarListaSimple();

        System.out.println("\n--- PRUEBA: DESPACHO SIN REPARTIDOR ---");
        probarSinRepartidorDisponible();

        System.out.println("\n====================================");
        System.out.println("          FIN DEL PROGRAMA");
        System.out.println("====================================");
    }

    private static void registrarRepartidores(
            QuindioExpress sistema
    ) {
        sistema.registrarRepartidor(
                new Repartidor("R001", "Carlos Perez", Zona.ARMENIA)
        );
        sistema.registrarRepartidor(
                new Repartidor("R002", "Laura Gomez", Zona.CALARCA)
        );
        sistema.registrarRepartidor(
                new Repartidor("R003", "Andres Ruiz", Zona.MONTENEGRO)
        );
        sistema.registrarRepartidor(
                new Repartidor("R004", "Sofia Marin", Zona.FILANDIA)
        );
        sistema.registrarRepartidor(
                new Repartidor("R005", "Daniel Rojas", Zona.SALENTO)
        );
        sistema.registrarRepartidor(
                new Repartidor("R006", "Valentina Gil", Zona.ARMENIA)
        );
    }

    private static void registrarPaquetes(
            QuindioExpress sistema
    ) {
        sistema.registrarPaquete(new Paquete(
                "PQ731", Zona.ARMENIA, 4.5, Prioridad.CINCO, 45));
        sistema.registrarPaquete(new Paquete(
                "PQ105", Zona.SALENTO, 3.0, Prioridad.CINCO, 25));
        sistema.registrarPaquete(new Paquete(
                "PQ942", Zona.CALARCA, 8.2, Prioridad.CUATRO, 20));
        sistema.registrarPaquete(new Paquete(
                "PQ420", Zona.MONTENEGRO, 5.1, Prioridad.TRES, 30));
        sistema.registrarPaquete(new Paquete(
                "PQ214", Zona.FILANDIA, 2.7, Prioridad.DOS, 50));
        sistema.registrarPaquete(new Paquete(
                "PQ842", Zona.ARMENIA, 12.0, Prioridad.CUATRO, 35));
        sistema.registrarPaquete(new Paquete(
                "PQ318", Zona.SALENTO, 3.8, Prioridad.TRES, 40));
        sistema.registrarPaquete(new Paquete(
                "PQ567", Zona.CALARCA, 6.4, Prioridad.CINCO, 15));
        sistema.registrarPaquete(new Paquete(
                "PQ623", Zona.MONTENEGRO, 7.4, Prioridad.UNO, 60));
        sistema.registrarPaquete(new Paquete(
                "PQ109", Zona.FILANDIA, 1.8, Prioridad.CUATRO, 22));
        sistema.registrarPaquete(new Paquete(
                "PQ255", Zona.ARMENIA, 9.1, Prioridad.TRES, 28));
        sistema.registrarPaquete(new Paquete(
                "PQ681", Zona.SALENTO, 4.6, Prioridad.DOS, 32));
        sistema.registrarPaquete(new Paquete(
                "PQ390", Zona.CALARCA, 5.7, Prioridad.CUATRO, 18));
        sistema.registrarPaquete(new Paquete(
                "PQ777", Zona.MONTENEGRO, 11.3, Prioridad.CINCO, 42));
        sistema.registrarPaquete(new Paquete(
                "PQ132", Zona.FILANDIA, 2.4, Prioridad.UNO, 55));
        sistema.registrarPaquete(new Paquete(
                "PQ506", Zona.ARMENIA, 6.9, Prioridad.CINCO, 19));
        sistema.registrarPaquete(new Paquete(
                "PQ888", Zona.SALENTO, 7.2, Prioridad.CUATRO, 24));
        sistema.registrarPaquete(new Paquete(
                "PQ241", Zona.CALARCA, 3.3, Prioridad.TRES, 38));
        sistema.registrarPaquete(new Paquete(
                "PQ659", Zona.MONTENEGRO, 8.7, Prioridad.DOS, 47));
        sistema.registrarPaquete(new Paquete(
                "PQ403", Zona.FILANDIA, 4.1, Prioridad.CINCO, 21));
        sistema.registrarPaquete(new Paquete(
                "PQ920", Zona.ARMENIA, 10.5, Prioridad.DOS, 34));
        sistema.registrarPaquete(new Paquete(
                "PQ176", Zona.SALENTO, 2.9, Prioridad.UNO, 58));
        sistema.registrarPaquete(new Paquete(
                "PQ345", Zona.CALARCA, 13.2, Prioridad.CINCO, 16));
        sistema.registrarPaquete(new Paquete(
                "PQ612", Zona.MONTENEGRO, 5.5, Prioridad.CUATRO, 27));
        sistema.registrarPaquete(new Paquete(
                "PQ298", Zona.FILANDIA, 6.1, Prioridad.TRES, 31));
        sistema.registrarPaquete(new Paquete(
                "PQ754", Zona.ARMENIA, 3.6, Prioridad.UNO, 49));
        sistema.registrarPaquete(new Paquete(
                "PQ461", Zona.SALENTO, 18.7, Prioridad.CINCO, 26));
        sistema.registrarPaquete(new Paquete(
                "PQ839", Zona.CALARCA, 4.9, Prioridad.DOS, 44));
        sistema.registrarPaquete(new Paquete(
                "PQ523", Zona.MONTENEGRO, 7.8, Prioridad.TRES, 36));
        sistema.registrarPaquete(new Paquete(
                "PQ967", Zona.FILANDIA, 9.6, Prioridad.CUATRO, 23));
    }

    private static void probarListaSimple() {
        ListaSimple<String> lista = new ListaSimple<>();

        System.out.println("¿Vacía al inicio?: " + lista.estaVacia());

        lista.agregar("Primero");
        lista.agregar("Segundo");
        lista.agregar("Tercero");

        System.out.println("Tamaño: " + lista.tamaño());
        System.out.println("Índice 1: " + lista.obtener(1));

        // Eliminar la cabeza.
        lista.eliminar("Primero");

        // Eliminar la cola.
        lista.eliminar("Tercero");

        System.out.println("Después de eliminar extremos:");

        for (String elemento : lista) {
            System.out.println(elemento);
        }

        System.out.println("Eliminar inexistente: "
                + lista.eliminar("No existe"));

        lista.eliminar("Segundo");

        System.out.println("¿Vacía al final?: " + lista.estaVacia());

        try {
            lista.obtener(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Índice inválido controlado correctamente.");
        }
    }

    private static void probarSinRepartidorDisponible() {
        QuindioExpress sistema = new QuindioExpress();

        sistema.registrarPaquete(
                new Paquete(
                        "PQ001",
                        Zona.ARMENIA,
                        3.0,
                        Prioridad.CINCO,
                        20
                )
        );

        // No se registra ningún repartidor.
        Paquete resultado =
                sistema.despacharSiguientePorPrioridad();

        System.out.println(
                "Resultado esperado: null. Resultado real: " + resultado
        );
    }
}