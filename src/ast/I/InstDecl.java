package ast.I;

import ast.E.E;
import ast.T.TipoArray;

import java.util.List;

public class InstDecl extends I{
    private TipoArray tipoVariable;
    private E identificador;
    private List<E> expresion;
    private Boolean constante;

    public InstDecl(TipoArray tipo, E iden, List<E> expr, Boolean constante, int fila, int columna ) {
        super(fila, columna);
        this.tipoVariable = tipo;
        this.identificador = iden;
        this.expresion = expr;
        this.constante = constante;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.DECL;
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
}
