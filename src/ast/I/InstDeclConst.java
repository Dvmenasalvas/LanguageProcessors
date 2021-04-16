package ast.I;

import ast.E.E;
import ast.T.Tipo;

public class InstDeclConst extends I {
    private Tipo tipoVariable;
    private E identificador;

    public InstDeclConst(Tipo tipo, E iden, int fila, int columna) {
        super(fila, columna);
        this.tipoVariable = tipo;
        this.identificador = iden;
    }

    @Override
    public Instrucciones tipoInstruccion() {
        return Instrucciones.DECLCONST;
    }

    public Tipo getTipo() {
        return tipoVariable;
    }

    public E getIdentificador() {
        return identificador;
    }
}
