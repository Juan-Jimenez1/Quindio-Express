package co.edu.uniquindio;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        QuindioExpress sistema = new QuindioExpress();

        registrarRepartidores(sistema);
        registrarPaquetes(sistema);

        //  RESUMEN INFORMAION DEL SISTEMA

        System.out.println("\n===== RESUMEN DEL SISTEMA =====");
        System.out.println("Cantidad de paquetes: "
                + sistema.cantidadPaquetes());
        System.out.println("Cantidad de repartidores: "
                + sistema.cantidadRepartidores());

        System.out.println("\n===== ORDEN DE REGISTRO (primeros 5) =====");
        imprimirPrimeros(sistema.obtenerOrdenRegistro(), 5);

        //  DESTINOS

        System.out.println("\n===== DESTINOS SIN DUPLICADOS (HashSet) =====");
        System.out.println(sistema.obtenerDestinosRegistrados());

        System.out.println("\n===== DESTINOS ORDENADOS (TreeSet) =====");
        System.out.println(sistema.obtenerDestinos());

        System.out.println("\n===== PAQUETES POR DESTINO =====");
        for (Zona zona : sistema.obtenerDestinos()) {
            System.out.print(zona + " -> ");
            for (Paquete p : sistema.obtenerPaquetesPorDestino(zona)) {
                System.out.print(p.getCodigo() + " ");
            }
            System.out.println();
        }

        //  CONSULTAS

        System.out.println("\n===== BUSCAR PAQUETE (HashMap) =====");
        System.out.println(sistema.buscarPaquete("PQ731"));
        System.out.println("Inexistente: " + sistema.buscarPaquete("PQ000"));

        System.out.println("\n===== BUSCAR REPARTIDOR =====");
        System.out.println(sistema.buscarRepartidor("R001"));

        // ORDENAMIENTOS

        System.out.println("\n===== ORDEN POR CODIGO (primeros 5) =====");
        imprimirPrimeros(sistema.obtenerPaquetesOrdenadosPorCodigo(), 5);

        System.out.println("\n===== BUSQUEDA BINARIA =====");
        System.out.println(sistema.busquedaBinariaPorCodigo("PQ731"));
        System.out.println("Inexistente: "
                + sistema.busquedaBinariaPorCodigo("PQ000"));

        System.out.println("\n===== ORDEN POR PRIORIDAD (primeros 5) =====");
        imprimirPrimeros(
                sistema.ordenarPaquetes(ComparadoresPaquete.POR_PRIORIDAD), 5);

        System.out.println("\n===== ORDEN POR PESO (primeros 5) =====");
        imprimirPrimeros(
                sistema.ordenarPaquetes(ComparadoresPaquete.POR_PESO), 5);

        System.out.println("\n===== ORDEN POR TIEMPO (primeros 5) =====");
        imprimirPrimeros(
                sistema.ordenarPaquetes(ComparadoresPaquete.POR_TIEMPO), 5);

        //  RECURSIVIDAD

        System.out.println("\n===== PESO TOTAL POR DESTINO =====");
        for (Zona zona : sistema.obtenerDestinos()) {
            System.out.println(
                    zona + ": " + String.format("%.2f", sistema.calcularPesoTotalPorDestino(zona)) + " kg"
            );
        }

        System.out.println("\n===== CONTEO POR PRIORIDAD MINIMA =====");
        System.out.println("Prioridad minima CUATRO: "
                + sistema.contarPaquetesConPrioridadMinima(Prioridad.CUATRO));
        System.out.println("Prioridad minima TRES: "
                + sistema.contarPaquetesConPrioridadMinima(Prioridad.TRES));

        //  DIVIDE Y VENCERAS

        System.out.println("\n===== PAQUETE DE MAYOR PESO =====");
        System.out.println(sistema.obtenerPaqueteDeMayorPeso());

        //  COLA FIFO

        System.out.println("\n===== SIGUIENTE POR LLEGADA =====");
        System.out.println(sistema.consultarSiguientePorLlegada());

        System.out.println("\n===== ATENDER POR LLEGADA (FIFO) =====");
        Paquete atendidoFifo = sistema.atenderSiguientePorLlegada();
        System.out.println("Atendido: " + atendidoFifo);

        // DESPACHO PRIORITARIO

        System.out.println("\n===== DESPACHO POR PRIORIDAD =====");
        System.out.println("Siguiente: "
                + sistema.consultarSiguientePorPrioridad());

        Paquete despachado1 = sistema.despacharSiguientePorPrioridad();
        Paquete despachado2 = sistema.despacharSiguientePorPrioridad();

        System.out.println("Despachado 1: " + despachado1);
        System.out.println("Despachado 2: " + despachado2);

        //  HISTORIAL

        System.out.println("\n===== HISTORIAL ANTES DE CONFIRMAR =====");
        sistema.mostrarHistorialEntregas();

        System.out.println("\n===== CONFIRMAR ENTREGAS (orden distinto al despacho) =====");
        // Se confirma primero el segundo despachado, luego el primero
        // y al final el atendido por FIFO.
        if (despachado2 != null) {
            sistema.confirmarEntrega(despachado2.getCodigo());
        }
        if (despachado1 != null) {
            sistema.confirmarEntrega(despachado1.getCodigo());
        }
        if (atendidoFifo != null) {
            sistema.confirmarEntrega(atendidoFifo.getCodigo());
        }

        System.out.println("\n===== HISTORIAL DESPUES DE CONFIRMAR =====");
        sistema.mostrarHistorialEntregas();

        System.out.println("\n===== CONFIRMAR ENTREGA REPETIDA =====");
        if (despachado1 != null) {
            sistema.confirmarEntrega(despachado1.getCodigo());
        }

        System.out.println("\n===== LIBERAR REPARTIDOR =====");
        sistema.liberarRepartidor("R001");
        System.out.println(sistema.buscarRepartidor("R001"));

        // DESPACHO SIN REPARTIDOR

        System.out.println("\n===== DESPACHO SIN REPARTIDOR DISPONIBLE =====");
        probarSinRepartidorDisponible();

        // VALIDACIONES

        System.out.println("\n===== VALIDACIONES =====");
        probarValidaciones(sistema);

        // LISTA PROPIA

        System.out.println("\n===== PRUEBAS DE LISTA PROPIA =====");
        probarListaSimple();

        System.out.println("\n===== FIN DE LAS PRUEBAS =====");
    }

    // ==================== AUXILIARES ====================

    private static void imprimirPrimeros(List<Paquete> paquetes, int n) {
        int limite = Math.min(n, paquetes.size());
        for (int i = 0; i < limite; i++) {
            System.out.println(paquetes.get(i));
        }
    }

    private static void registrarRepartidores(QuindioExpress sistema) {
        sistema.registrarRepartidor(
                new Repartidor("R001", "Laura Gomez", Zona.ARMENIA));
        sistema.registrarRepartidor(
                new Repartidor("R002", "Carlos Ruiz", Zona.CALARCA));
        sistema.registrarRepartidor(
                new Repartidor("R003", "Maria Lopez", Zona.FILANDIA));
        sistema.registrarRepartidor(
                new Repartidor("R004", "Andres Perez", Zona.MONTENEGRO));
        sistema.registrarRepartidor(
                new Repartidor("R005", "Valentina Gil", Zona.SALENTO));
        sistema.registrarRepartidor(
                new Repartidor("R006", "Daniel Torres", Zona.ARMENIA));
    }

    private static void registrarPaquetes(QuindioExpress sistema) {
        sistema.registrarPaquete(
                new Paquete("PQ731", Zona.ARMENIA, 4.5, Prioridad.CINCO, 45));
        sistema.registrarPaquete(
                new Paquete("PQ105", Zona.SALENTO, 3.0, Prioridad.CINCO, 25));
        sistema.registrarPaquete(
                new Paquete("PQ942", Zona.CALARCA, 8.2, Prioridad.CUATRO, 20));
        sistema.registrarPaquete(
                new Paquete("PQ318", Zona.FILANDIA, 5.1, Prioridad.TRES, 30));
        sistema.registrarPaquete(
                new Paquete("PQ567", Zona.MONTENEGRO, 9.4, Prioridad.CINCO, 15));
        sistema.registrarPaquete(
                new Paquete("PQ214", Zona.ARMENIA, 6.3, Prioridad.DOS, 35));
        sistema.registrarPaquete(
                new Paquete("PQ842", Zona.CALARCA, 2.7, Prioridad.CUATRO, 18));
        sistema.registrarPaquete(
                new Paquete("PQ421", Zona.SALENTO, 7.6, Prioridad.TRES, 40));
        sistema.registrarPaquete(
                new Paquete("PQ654", Zona.FILANDIA, 3.8, Prioridad.UNO, 50));
        sistema.registrarPaquete(
                new Paquete("PQ829", Zona.MONTENEGRO, 11.2, Prioridad.CINCO, 22));

        sistema.registrarPaquete(
                new Paquete("PQ146", Zona.ARMENIA, 5.7, Prioridad.TRES, 28));
        sistema.registrarPaquete(
                new Paquete("PQ275", Zona.CALARCA, 4.9, Prioridad.DOS, 32));
        sistema.registrarPaquete(
                new Paquete("PQ386", Zona.FILANDIA, 6.8, Prioridad.CUATRO, 19));
        sistema.registrarPaquete(
                new Paquete("PQ497", Zona.SALENTO, 3.4, Prioridad.UNO, 44));
        sistema.registrarPaquete(
                new Paquete("PQ518", Zona.MONTENEGRO, 8.7, Prioridad.TRES, 26));
        sistema.registrarPaquete(
                new Paquete("PQ629", Zona.ARMENIA, 12.5, Prioridad.CINCO, 16));
        sistema.registrarPaquete(
                new Paquete("PQ740", Zona.CALARCA, 4.1, Prioridad.TRES, 37));
        sistema.registrarPaquete(
                new Paquete("PQ851", Zona.FILANDIA, 7.3, Prioridad.DOS, 24));
        sistema.registrarPaquete(
                new Paquete("PQ963", Zona.SALENTO, 5.6, Prioridad.CUATRO, 21));
        sistema.registrarPaquete(
                new Paquete("PQ174", Zona.MONTENEGRO, 6.1, Prioridad.UNO, 39));

        sistema.registrarPaquete(
                new Paquete("PQ285", Zona.ARMENIA, 9.8, Prioridad.CUATRO, 17));
        sistema.registrarPaquete(
                new Paquete("PQ396", Zona.CALARCA, 3.6, Prioridad.CINCO, 23));
        sistema.registrarPaquete(
                new Paquete("PQ407", Zona.FILANDIA, 10.4, Prioridad.CINCO, 14));
        sistema.registrarPaquete(
                new Paquete("PQ619", Zona.SALENTO, 4.7, Prioridad.DOS, 33));
        sistema.registrarPaquete(
                new Paquete("PQ720", Zona.MONTENEGRO, 7.9, Prioridad.CUATRO, 27));
        sistema.registrarPaquete(
                new Paquete("PQ831", Zona.ARMENIA, 2.9, Prioridad.UNO, 41));
        sistema.registrarPaquete(
                new Paquete("PQ953", Zona.CALARCA, 13.6, Prioridad.CINCO, 12));
        sistema.registrarPaquete(
                new Paquete("PQ164", Zona.FILANDIA, 5.3, Prioridad.TRES, 29));
        sistema.registrarPaquete(
                new Paquete("PQ376", Zona.SALENTO, 8.9, Prioridad.CUATRO, 20));
        sistema.registrarPaquete(
                new Paquete("PQ487", Zona.MONTENEGRO, 6.5, Prioridad.DOS, 36));
    }

    private static void probarSinRepartidorDisponible() {
        QuindioExpress vacio = new QuindioExpress();

        vacio.registrarPaquete(
                new Paquete("PQ001", Zona.ARMENIA, 3.0, Prioridad.CINCO, 20));

        // No se registra ningún repartidor.
        Paquete resultado = vacio.despacharSiguientePorPrioridad();
        System.out.println("Resultado esperado: null. Resultado real: "
                + resultado);
    }

    private static void probarValidaciones(QuindioExpress sistema) {
        try {
            sistema.registrarPaquete(
                    new Paquete("PQ731", Zona.ARMENIA, 2.0, Prioridad.TRES, 20));
        } catch (IllegalArgumentException e) {
            System.out.println("Código duplicado: " + e.getMessage());
        }

        try {
            sistema.registrarRepartidor(
                    new Repartidor("R001", "Otro", Zona.ARMENIA));
        } catch (IllegalArgumentException e) {
            System.out.println("Repartidor duplicado: " + e.getMessage());
        }

        try {
            new Paquete("PQ999", Zona.ARMENIA, -3.0, Prioridad.TRES, 20);
        } catch (IllegalArgumentException e) {
            System.out.println("Peso inválido: " + e.getMessage());
        }

        try {
            new Paquete("PQ998", Zona.ARMENIA, 3.0, Prioridad.TRES, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Tiempo inválido: " + e.getMessage());
        }

        try {
            new Paquete("PQ997", null, 3.0, Prioridad.TRES, 20);
        } catch (IllegalArgumentException e) {
            System.out.println("Destino inválido: " + e.getMessage());
        }
    }

    private static void probarListaSimple() {
        ListaSimple<String> lista = new ListaSimple<>();

        // Lista vacía
        System.out.println("Vacía al inicio: " + lista.estaVacia());
        System.out.println("Eliminar en lista vacía: " + lista.eliminar("X"));
        try {
            lista.obtener(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Obtener en vacía: índice inválido controlado.");
        }

        // Un único elemento
        lista.agregar("A");
        System.out.println("Un elemento, tamaño: " + lista.tamanio());
        System.out.println("Eliminar único elemento (A): "
                + lista.eliminar("A"));
        System.out.println("Vacía de nuevo: " + lista.estaVacia());

        // Varios elementos
        lista.agregar("A");
        lista.agregar("B");
        lista.agregar("C");
        lista.agregar("D");
        System.out.println("Tamaño con 4 elementos: " + lista.tamanio());
        System.out.println("Elemento en posición 2: " + lista.obtener(2));

        System.out.println("Eliminar primero (A): " + lista.eliminar("A"));
        System.out.println("Eliminar intermedio (C): " + lista.eliminar("C"));
        System.out.println("Eliminar último (D): " + lista.eliminar("D"));
        System.out.println("Eliminar inexistente (Z): " + lista.eliminar("Z"));

        System.out.println("Contenido final (debe ser solo B):");
        for (String elemento : lista) {
            System.out.println("  " + elemento);
        }
        System.out.println("Tamaño final: " + lista.tamanio());

        // Agregar después de eliminar la cola
        lista.agregar("E");
        System.out.println("Agregar tras eliminar el último:");
        for (String elemento : lista) {
            System.out.println("  " + elemento);
        }

        try {
            lista.obtener(5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Índice fuera de rango controlado.");
        }
    }
}