package co.edu.uniquindio;


public interface Coleccion<T> extends Iterable<T> {

    void agregar(T elemento);

    boolean eliminar(T elemento);

    T obtener(int indice);

    int tamaño();

    boolean estaVacia();
}

