package ast.T;

import ast.E.*;

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

    public E getArgumento() {
        return argumento;
    }
    @Override
    public String toString() {

        return tipo.toString() + argumento.toString();
    }
}
