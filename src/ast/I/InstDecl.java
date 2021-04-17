package ast.I;

import ast.E.E;
import ast.T.Tipo;

public class InstDecl extends I{
    private Tipo tipoVariable;
    private E identificador;
    private E expresion;

    public InstDecl(Tipo tipo, E iden, E expr, int fila, int columna) {
        super(fila, columna);
        this.tipoVariable = tipo;
        this.identificador = iden;
        this.expresion = expr;
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
    public E getExpresion() {return expresion;}
}
