package ast.I;
import java.util.List;
import ast.E.E;

public class Case extends I{
    private E expresion;
    private List<I> cuerpoCase;
    private boolean isDefault;
    public Case(E nombreCase, List<I> expresion, boolean isDefault, int fila, int columna) {
        super(fila, columna);
        this.expresion = nombreCase;
        this.cuerpoCase = expresion;
        this.fila = fila;
        this.columna = columna;
        this.isDefault = isDefault;
    }

    public List<I> getCuerpoCase() {
        return cuerpoCase;
    }


    public E getExpresion() {
        return expresion;
    }

    @Override
    public String toString() {
        String aux = "{{Case: " + expresion + "}";
        if (isDefault) aux = "{{Default}";
        for(I ins : cuerpoCase) aux +=  ins;
        return aux + "}";
    }

    public boolean isDefault() {
        return isDefault;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.CASE;
    }
}