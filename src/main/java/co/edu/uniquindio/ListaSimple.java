package co.edu.uniquindio;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementa una lista simplemente enlazada genérica que permite
 * agregar, eliminar y consultar elementos, además de recorrerlos
 * mediante un iterador.
 */
public class ListaSimple<T> implements Coleccion<T> {

    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int cantidad;

    /**
     * Inicializa una lista vacía.
     */
    public ListaSimple() {
        cabeza = null;
        cola = null;
        cantidad = 0;
    }

    /**
     * Agrega un elemento al final de la lista.
     */
    @Override
    public void agregar(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);

        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            cola.siguiente = nuevo;
        }

        cola = nuevo;
        cantidad++;
    }

    /**
     * Elimina la primera aparición del elemento indicado.
     */
    @Override
    public boolean eliminar(T elemento) {
        if (estaVacia()) {
            return false;
        }

        if (sonIguales(cabeza.valor, elemento)) {
            cabeza = cabeza.siguiente;
            cantidad--;

            if (cantidad == 0) {
                cola = null;
            }

            return true;
        }

        Nodo<T> anterior = cabeza;
        Nodo<T> actual = cabeza.siguiente;

        while (actual != null) {
            if (sonIguales(actual.valor, elemento)) {
                anterior.siguiente = actual.siguiente;

                if (actual == cola) {
                    cola = anterior;
                }

                cantidad--;
                return true;
            }

            anterior = actual;
            actual = actual.siguiente;
        }

        return false;
    }

    /**
     * Compara dos elementos para determinar si son iguales.
     */
    private boolean sonIguales(T primero, T segundo) {
        if (primero == null) {
            return segundo == null;
        }

        return primero.equals(segundo);
    }

    /**
     * Obtiene el elemento ubicado en el índice indicado.
     *
     * @throws IndexOutOfBoundsException si el índice está fuera de rango.
     */
    @Override
    public T obtener(int indice) {
        if (indice < 0 || indice >= cantidad) {
            throw new IndexOutOfBoundsException(
                    "Indice fuera de rango: " + indice
            );
        }

        Nodo<T> actual = cabeza;

        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }

        return actual.valor;
    }

    /**
     * Retorna la cantidad de elementos de la lista.
     */
    @Override
    public int tamanio() {
        return cantidad;
    }

    /**
     * Verifica si la lista está vacía.
     */
    @Override
    public boolean estaVacia() {
        return cantidad == 0;
    }

    /**
     * Retorna un iterador para recorrer los elementos de la lista.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Nodo<T> actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException(
                            "No hay mas elementos"
                    );
                }

                T elemento = actual.valor;
                actual = actual.siguiente;

                return elemento;
            }
        };
    }
}

