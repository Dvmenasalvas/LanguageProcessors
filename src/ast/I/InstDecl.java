package ast.I;

import ast.E.E;
import ast.T.Tipo;

public class InstDecl extends I{
    private Tipo tipoVariable;
    private E identificador;

    public InstDecl(Tipo tipo, E iden, int fila, int columna) {
        super(fila, columna);
        this.tipoVariable = tipo;
        this.identificador = iden;
    }

    @Override
    public Instrucciones tipoInstruccion() {
        return Instrucciones.DECL;
    }

    public Tipo getTipo() {
        return tipoVariable;
    }
    public E getIdentificador() {
        return identificador;
    }
}
