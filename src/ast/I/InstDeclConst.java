package ast.I;

import ast.E.E;
import ast.T.Tipo;
import ast.T.TipoArray;

public class InstDeclConst extends I {
    private TipoArray tipoVariable;
    private E identificador;

    public InstDeclConst(TipoArray tipo, E iden, int fila, int columna) {
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
