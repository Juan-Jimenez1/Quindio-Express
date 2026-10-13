package co.edu.uniquindio;

/**
 * Representa un nodo de una lista simplemente enlazada,
 * que almacena un valor y una referencia al siguiente nodo.
 *
 * @param <T> tipo de dato almacenado en el nodo.
 */
public class Nodo<T> {

    T valor;
    Nodo<T> siguiente;

    /**
     * Crea un nodo con el valor indicado.
     *
     * @param valor dato que se almacenará en el nodo.
     */
    public Nodo(T valor) {
        this.valor = valor;
        this.siguiente = null;
    }
}

