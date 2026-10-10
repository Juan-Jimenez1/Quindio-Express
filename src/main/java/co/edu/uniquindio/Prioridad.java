package co.edu.uniquindio;


public enum Prioridad {
    CINCO(5),
    CUATRO(4),
    TRES(3),
    DOS(2),
    UNO(1);

    private final int valor;

    Prioridad(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}

