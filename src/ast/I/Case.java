package ast.I;
import java.util.List;
import ast.E.E;

public class Case extends I{
    private E expresion;
    private List<I> cuerpoCase;
    public Case(E nombreCase, List<I> expresion, int fila, int columna) {
        super(fila, columna);
        this.expresion = nombreCase;
        this.cuerpoCase = expresion;
        this.fila = fila;
        this.columna = columna;
    }

    public List<I> getCuerpoCase() {
        return cuerpoCase;
    }

    public E getExpresion() {
        return expresion;
    }

    @Override
    public String toString() {
        String aux = expresion.toString();
        for(I ins : cuerpoCase) aux += ins;
        return aux;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.CASE;
    }
}