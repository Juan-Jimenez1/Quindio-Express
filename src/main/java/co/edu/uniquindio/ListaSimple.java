package co.edu.uniquindio;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class  ListaSimple<T> implements Coleccion<T> {

    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int cantidad;

    public ListaSimple() {
        cabeza = null;
        cola = null;
        cantidad = 0;
    }

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

    private boolean sonIguales(T primero, T segundo) {
        if (primero == null) {
            return segundo == null;
        }

        return primero.equals(segundo);
    }

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

    @Override
    public int tamaño() {
        return cantidad;
    }

    @Override
    public boolean estaVacia() {
        return cantidad == 0;
    }

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

