package ast.I;

import ast.E.E;
import ast.T.Tipo;
import ast.T.TipoArray;

import java.util.List;

public class InstDecl extends I{
    private TipoArray tipoVariable;
    private E identificador;
    private List<E> expresion;
    private boolean constante;



    public InstDecl(TipoArray tipo, E iden, List<E> expr, Boolean constante, int fila, int columna ) {
        super(fila, columna);
        this.tipoVariable = tipo;
        this.identificador = iden;
        this.expresion = expr;
        this.constante = constante;
    }

    public E getIdentificador() {
        return identificador;
    }
    public List<E> getExpresion() {
        return expresion;
    }

    public boolean isConstante() {
        return constante;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.DECL;
    }

    @Override
    public String toString() {
        String out = "{{I: Declaracion}";
        if(constante) out += "{Const}";
        out += "{" + tipoVariable + "}" + identificador;

        if(expresion != null) {
            out += "{{Inicializacion}";
            for(E v : expresion) {
                out += v;
            }
            out += "}";
        }

        out += "}";
        return out;
    }

    public TipoArray getTipo() {
        return tipoVariable;
    }

}
