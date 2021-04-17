package ast.I;

import ast.E.E;
import ast.T.Tipo;
import ast.T.TipoArray;

public class InstDecl extends I{
    private TipoArray tipoVariable;
    private E identificador;
    private E expresion;

    public InstDecl(TipoArray tipo, E iden, E expr, int fila, int columna) {
        super(fila, columna);
        this.tipoVariable = tipo;
        this.identificador = iden;
        this.expresion = expr;
    }

    @Override
    public Instrucciones tipoInstruccion() {
        return Instrucciones.DECL;
    }

    @Override
    public String toString() {
        return "InstDecl{" +
                "tipoVariable=" + tipoVariable +
                ", identificador=" + identificador +
                ", expresion=" + expresion +
                '}';
    }

    public TipoArray getTipo() {
        return tipoVariable;
    }
    public E getIdentificador() {
        return identificador;
    }
    public E getExpresion() {return expresion;}
}
