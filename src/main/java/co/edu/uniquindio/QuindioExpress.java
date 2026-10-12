package co.edu.uniquindio;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class QuindioExpress {

    private final Map<String, Paquete> paquetesPorCodigo;
    private final Map<String, Repartidor> repartidoresPorId;

    // Conserva el orden original de todos los paquetes registrados.
    private final List<Paquete> ordenRegistro;

    // Solo contiene paquetes pendientes de atención por llegada.
    private final Queue<Paquete> paquetesPorLlegada;

    // Solo contiene paquetes pendientes de despacho prioritario.
    private final PriorityQueue<Paquete> paquetesPorPrioridad;

    private final Set<Zona> destinos;
    private final Set<Zona> destinosOrdenados;
    private final Map<Zona, List<Paquete>> paquetesPorDestino;

    // Paquetes asignados que todavía no han sido entregados.
    private final Map<String, Repartidor> asignaciones;

    // Historial en el orden real de confirmación de las entregas.
    private final ListaSimple<Paquete> historialEntregas;

    public QuindioExpress() {
        paquetesPorCodigo = new HashMap<>();
        repartidoresPorId = new HashMap<>();

        ordenRegistro = new ArrayList<>();
        paquetesPorLlegada = new ArrayDeque<>();
        paquetesPorPrioridad = new PriorityQueue<>(
                ComparadoresPaquete.PARA_DESPACHO
        );

        destinos = new HashSet<>();
        destinosOrdenados = new TreeSet<>();
        paquetesPorDestino = new HashMap<>();

        asignaciones = new HashMap<>();
        historialEntregas = new ListaSimple<>();

        for (Zona zona : Zona.values()) {
            paquetesPorDestino.put(zona, new ArrayList<>());
        }
    }

    //Registro

    public void registrarPaquete(Paquete paquete) {
        if (paquete == null) {
            throw new IllegalArgumentException(
                    "El paquete no puede ser nulo."
            );
        }

        if (paquetesPorCodigo.containsKey(paquete.getCodigo())) {
            throw new IllegalArgumentException(
                    "Ya existe un paquete con el código "
                            + paquete.getCodigo() + "."
            );
        }

        paquetesPorCodigo.put(paquete.getCodigo(), paquete);
        ordenRegistro.add(paquete);

        paquetesPorLlegada.offer(paquete);
        paquetesPorPrioridad.offer(paquete);

        destinos.add(paquete.getDestino());
        destinosOrdenados.add(paquete.getDestino());

        paquetesPorDestino
                .get(paquete.getDestino())
                .add(paquete);
    }

    public void registrarRepartidor(Repartidor repartidor) {
        if (repartidor == null) {
            throw new IllegalArgumentException(
                    "El repartidor no puede ser nulo."
            );
        }

        String id = repartidor.getIdentificacion();

        if (repartidoresPorId.containsKey(id)) {
            throw new IllegalArgumentException(
                    "Ya existe un repartidor con la identificación "
                            + id + "."
            );
        }

        repartidoresPorId.put(id, repartidor);
    }

    //Consultas

    public Paquete buscarPaquete(String codigo) {
        if (codigo == null) {
            return null;
        }

        return paquetesPorCodigo.get(codigo);
    }

    public Repartidor buscarRepartidor(String identificacion) {
        if (identificacion == null) {
            return null;
        }

        return repartidoresPorId.get(identificacion);
    }

    public int cantidadPaquetes() {
        return paquetesPorCodigo.size();
    }

    public int cantidadRepartidores() {
        return repartidoresPorId.size();
    }

    public Set<Zona> obtenerDestinos() {
        return new TreeSet<>(destinosOrdenados);
    }

    public Set<Zona> obtenerDestinosRegistrados() {
        return new HashSet<>(destinos);
    }

    public List<Paquete> obtenerPaquetesPorDestino(Zona destino) {
        List<Paquete> paquetes = paquetesPorDestino.get(destino);

        if (paquetes == null) {
            return new ArrayList<>();
        }

        return new ArrayList<>(paquetes);
    }

    // Devuelve una copia: modificarla no altera el registro original.
    public List<Paquete> obtenerOrdenRegistro() {
        return new ArrayList<>(ordenRegistro);
    }

    //Cola FIFO

    public Paquete consultarSiguientePorLlegada() {
        return paquetesPorLlegada.peek();
    }

    public Paquete atenderSiguientePorLlegada() {
        Paquete paquete = paquetesPorLlegada.peek();

        if (paquete == null) {
            return null;
        }

        Repartidor repartidor =
                buscarRepartidorDisponible(paquete.getDestino());

        if (repartidor == null) {
            System.out.println(
                    "No hay repartidores disponibles para "
                            + paquete.getDestino() + "."
            );

            return null;
        }

        asignarPaquete(paquete, repartidor);
        return paquete;
    }

    //Prioridad

    public Paquete consultarSiguientePorPrioridad() {
        return paquetesPorPrioridad.peek();
    }

    //Ordenamientos

    public List<Paquete> obtenerPaquetesOrdenadosPorCodigo() {
        List<Paquete> resultado =
                new ArrayList<>(ordenRegistro);

        resultado.sort(Comparator.naturalOrder());

        return resultado;
    }

    public List<Paquete> ordenarPaquetes(
            Comparator<Paquete> comparador
    ) {
        if (comparador == null) {
            throw new IllegalArgumentException(
                    "El comparador no puede ser nulo."
            );
        }

        List<Paquete> resultado =
                new ArrayList<>(ordenRegistro);

        resultado.sort(comparador);

        return resultado;
    }

    //Busqueda Binaria

    public Paquete busquedaBinariaPorCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }

        List<Paquete> ordenados =
                obtenerPaquetesOrdenadosPorCodigo();

        int izquierda = 0;
        int derecha = ordenados.size() - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            Paquete paquete = ordenados.get(medio);

            int comparacion =
                    paquete.getCodigo().compareTo(codigo);

            if (comparacion == 0) {
                return paquete;
            }

            if (comparacion < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return null;
    }

    //Recursividad

    public double calcularPesoTotalPorDestino(Zona destino) {
        List<Paquete> paquetes = paquetesPorDestino.get(destino);

        if (paquetes == null) {
            return 0;
        }

        return calcularPesoRecursivo(paquetes, 0);
    }

    private double calcularPesoRecursivo(
            List<Paquete> paquetes,
            int indice
    ) {
        if (indice >= paquetes.size()) {
            return 0;
        }

        return paquetes.get(indice).getPeso()
                + calcularPesoRecursivo(paquetes, indice + 1);
    }

    public int contarPaquetesConPrioridadMinima(
            Prioridad prioridadMinima
    ) {
        if (prioridadMinima == null) {
            throw new IllegalArgumentException(
                    "La prioridad mínima no puede ser nula."
            );
        }

        return contarPrioridadRecursivo(
                ordenRegistro, 0, prioridadMinima
        );
    }

    private int contarPrioridadRecursivo(
            List<Paquete> paquetes,
            int indice,
            Prioridad prioridadMinima
    ) {
        if (indice >= paquetes.size()) {
            return 0;
        }

        int cantidadActual = paquetes.get(indice)
                .getPrioridad().getValor()
                >= prioridadMinima.getValor() ? 1 : 0;

        return cantidadActual
                + contarPrioridadRecursivo(
                paquetes,
                indice + 1,
                prioridadMinima
        );
    }

    //Divide y venceras

    public Paquete obtenerPaqueteDeMayorPeso() {
        if (ordenRegistro.isEmpty()) {
            return null;
        }

        return mayorPesoDivideVenceras(
                ordenRegistro,
                0,
                ordenRegistro.size() - 1
        );
    }

    private Paquete mayorPesoDivideVenceras(
            List<Paquete> paquetes,
            int inicio,
            int fin
    ) {
        // Caso base.
        if (inicio == fin) {
            return paquetes.get(inicio);
        }

        // División.
        int medio = inicio + (fin - inicio) / 2;

        Paquete izquierda = mayorPesoDivideVenceras(
                paquetes, inicio, medio
        );

        Paquete derecha = mayorPesoDivideVenceras(
                paquetes, medio + 1, fin
        );

        // Combinación.
        return izquierda.getPeso() >= derecha.getPeso()
                ? izquierda
                : derecha;
    }

    // Repartidores

    public Repartidor buscarRepartidorDisponible(Zona destino) {
        if (destino == null) {
            return null;
        }

        for (Repartidor repartidor : repartidoresPorId.values()) {
            if (repartidor.isDisponible()
                    && repartidor.getZona() == destino) {
                return repartidor;
            }
        }

        return null;
    }

    //Asignacion

    private void asignarPaquete(
            Paquete paquete,
            Repartidor repartidor
    ) {
        if (paquete == null || repartidor == null) {
            throw new IllegalArgumentException(
                    "El paquete y el repartidor son obligatorios."
            );
        }

        if (!repartidor.isDisponible()) {
            throw new IllegalStateException(
                    "El repartidor no está disponible."
            );
        }

        if (repartidor.getZona() != paquete.getDestino()) {
            throw new IllegalArgumentException(
                    "La zona del repartidor no coincide con el destino."
            );
        }

        if (asignaciones.containsKey(paquete.getCodigo())) {
            throw new IllegalStateException(
                    "El paquete ya fue asignado."
            );
        }

        // Se retira de las colas de pendientes.
        paquetesPorLlegada.remove(paquete);
        paquetesPorPrioridad.remove(paquete);

        asignaciones.put(paquete.getCodigo(), repartidor);
        repartidor.setDisponible(false);

        System.out.println(
                "Paquete asignado: " + paquete.getCodigo()
        );
        System.out.println(
                "Repartidor: " + repartidor.getNombre()
        );
    }

    public Paquete despacharSiguientePorPrioridad() {
        Paquete paquete = paquetesPorPrioridad.peek();

        if (paquete == null) {
            System.out.println("No hay paquetes pendientes.");
            return null;
        }

        Repartidor repartidor =
                buscarRepartidorDisponible(paquete.getDestino());

        if (repartidor == null) {
            System.out.println(
                    "No hay repartidores disponibles para la zona "
                            + paquete.getDestino()
            );

            return null;
        }

        asignarPaquete(paquete, repartidor);
        return paquete;
    }

    //Confirmacion de entrega

    public boolean confirmarEntrega(String codigo) {
        if (codigo == null) {
            return false;
        }

        Repartidor repartidor = asignaciones.get(codigo);

        if (repartidor == null) {
            System.out.println(
                    "El paquete no está asignado o ya fue entregado."
            );
            return false;
        }

        Paquete paquete = paquetesPorCodigo.get(codigo);

        // Se registra solo al confirmar la entrega.
        historialEntregas.agregar(paquete);

        // Se elimina la asignación pendiente.
        asignaciones.remove(codigo);

        // El repartidor queda disponible de nuevo.
        repartidor.setDisponible(true);

        System.out.println(
                "Entrega confirmada para el paquete " + codigo
        );

        return true;
    }

    public void liberarRepartidor(String identificacion) {
        Repartidor repartidor =
                repartidoresPorId.get(identificacion);

        if (repartidor == null) {
            System.out.println("No existe ese repartidor.");
            return;
        }

        // No se libera si aún tiene una entrega asignada.
        for (Repartidor asignado : asignaciones.values()) {
            if (asignado == repartidor) {
                System.out.println(
                        "El repartidor tiene un paquete pendiente "
                                + "de entrega."
                );
                return;
            }
        }

        repartidor.setDisponible(true);
    }

    //Historial

    public ListaSimple<Paquete> obtenerHistorialEntregas() {
        return historialEntregas;
    }

    public void mostrarHistorialEntregas() {
        if (historialEntregas.estaVacia()) {
            System.out.println(
                    "El historial de entregas está vacío."
            );
            return;
        }

        for (Paquete paquete : historialEntregas) {
            System.out.println(paquete);
        }
    }

    //Impresion
    public void mostrarPaquetes() {
        for (Paquete paquete : ordenRegistro) {
            System.out.println(paquete);
        }
    }
}
