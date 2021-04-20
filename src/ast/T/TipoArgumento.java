package ast.T;

import ast.E.E;

public class TipoArgumento {
    private Tipo tipo;
    private E argumento;

    public TipoArgumento(Tipo tipo, E argumento) {
        this.tipo = tipo;
        this.argumento = argumento;
    }

    public Tipo getTipo() {
        return tipo;
    }

    @Override
    public String toString() {

        return tipo.toString() + argumento.toString();
    }
}
