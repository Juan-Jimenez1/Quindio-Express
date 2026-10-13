# QuindioExpress 📦

Sistema de gestión y distribución de paquetes desarrollado en Java, como proyecto académico para la asignatura de Estructuras de Datos de la Universidad del Quindío.

## Descripción

QuindioExpress permite registrar y consultar paquetes y repartidores, organizar los paquetes según diferentes criterios, gestionar los despachos y llevar un historial de las entregas confirmadas.

El sistema utiliza distintas estructuras de datos para administrar la información y aplica algoritmos de búsqueda, ordenamiento, recursividad y divide y vencerás.

## Funcionalidades

- Registro de paquetes y repartidores con validación de datos.
- Consulta de paquetes por código y repartidores por identificación.
- Organización de paquetes por destino.
- Ordenamiento de paquetes por código, prioridad, peso y tiempo estimado.
- Búsqueda binaria de paquetes por código.
- Atención de paquetes por orden de llegada mediante FIFO.
- Despacho de paquetes según prioridad, tiempo estimado y código.
- Asignación de repartidores según su disponibilidad y zona.
- Cálculo recursivo del peso total de los paquetes por destino.
- Conteo recursivo de paquetes según una prioridad mínima.
- Búsqueda del paquete de mayor peso mediante divide y vencerás.
- Registro y consulta del historial de entregas confirmadas.
- Implementación de una lista simplemente enlazada genérica.

## Estructuras de datos utilizadas

| Estructura | Uso |
|---|---|
| `HashMap` | Consultar paquetes por código y repartidores por identificación. |
| `ArrayList` | Conservar el orden de registro y almacenar paquetes por destino. |
| `ArrayDeque` | Gestionar los paquetes pendientes por orden de llegada. |
| `PriorityQueue` | Organizar los paquetes pendientes según los criterios de despacho. |
| `HashSet` | Almacenar destinos sin duplicados. |
| `TreeSet` | Almacenar destinos sin duplicados y en orden natural. |
| `ListaSimple<T>` | Registrar las entregas confirmadas mediante una lista enlazada propia. |
| `Iterator<T>` | Recorrer los elementos de la lista simplemente enlazada. |

## Algoritmos implementados

### Búsqueda binaria

Permite localizar un paquete por su código después de ordenar una copia de los paquetes registrados.

### Recursividad

Se utiliza para calcular el peso total de los paquetes de un destino y contar aquellos que cumplen una prioridad mínima.

### Divide y vencerás

Permite encontrar el paquete de mayor peso dividiendo la lista en dos partes, obteniendo el máximo de cada una y comparando los resultados.

### Ordenamiento

Se utilizan comparadores personalizados para ordenar los paquetes por prioridad, peso y tiempo estimado, además del orden natural por código.

## Tecnologías

- **Lenguaje:** Java.
- **JDK:** Java 21.
- **IDE recomendado:** IntelliJ IDEA.
- **Gestión del proyecto:** Maven, si se utiliza la configuración actual del proyecto.

## Estructura del proyecto

```text
QuindioExpress/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── co/
                └── edu/
                    └── uniquindio/
                        ├── Main.java
                        ├── QuindioExpress.java
                        ├── Paquete.java
                        ├── Repartidor.java
                        ├── Zona.java
                        ├── Prioridad.java
                        ├── ComparadoresPaquete.java
                        ├── Coleccion.java
                        ├── ListaSimple.java
                        └── Nodo.java
```
## Pruebas realizadas

El programa incluye pruebas para verificar:

- Registro de paquetes y repartidores.
- Consultas por código e identificación.
- Ordenamientos y búsqueda binaria.
- Cálculos recursivos y búsqueda del paquete de mayor peso.
- Atención FIFO y despacho por prioridad.
- Asignación y liberación de repartidores.
- Confirmación de entregas e historial.
- Validación de datos duplicados o inválidos.
- Operaciones básicas de la lista simplemente enlazada.

## Autoría

Proyecto académico desarrollado para la asignatura de Estructuras de Datos de la Universidad del Quindío.

**QuindioExpress — Gestión y distribución de paquetes.**
