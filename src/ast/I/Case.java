package ast.I;
import java.util.List;
import ast.E.E;

public class Case extends I{
    private E nombreCase;
    private List<I> cuerpoCase;
    public Case(E nombreCase, List<I> cuerpoCase, int fila, int columna) {
        super(fila, columna);
        this.nombreCase = nombreCase;
        this.cuerpoCase = cuerpoCase;
        this.fila = fila;
        this.columna = columna;
    }

    public List<I> getCuerpoCase() {
        return cuerpoCase;
    }

    @Override
    public String toString() {
        String aux = nombreCase.toString();
        for(I ins : cuerpoCase) aux += ins;
        return aux;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.CASE;
    }
}