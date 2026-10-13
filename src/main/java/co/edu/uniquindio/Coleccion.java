
package co.edu.uniquindio;

/**
 * Define las operaciones básicas de una colección genérica de elementos.
 * Permite agregar, eliminar y consultar elementos, conocer su tamaño
 * y verificar si está vacía. Además, permite recorrer sus elementos
 * mediante un iterador.
 *
 * @param <T> tipo de los elementos almacenados en la colección.
 */
public interface Coleccion<T> extends Iterable<T> {

    /**
     * Agrega un elemento a la colección.
     *
     * @param elemento elemento que se desea agregar.
     */
    void agregar(T elemento);

    /**
     * Elimina un elemento de la colección.
     *
     * @param elemento elemento que se desea eliminar.
     * @return {@code true} si el elemento fue eliminado;
     *         {@code false} si no se encontró.
     */
    boolean eliminar(T elemento);

    /**
     * Obtiene el elemento ubicado en una posición determinada.
     *
     * @param indice posición del elemento que se desea consultar.
     * @return elemento ubicado en la posición indicada.
     */
    T obtener(int indice);

    /**
     * Consulta la cantidad de elementos almacenados en la colección.
     *
     * @return número de elementos de la colección.
     */
    int tamanio();

    /**
     * Verifica si la colección no contiene elementos.
     *
     * @return {@code true} si está vacía; {@code false} en caso contrario.
     */
    boolean estaVacia();
}
