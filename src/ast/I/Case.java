package ast.I;
import java.util.List;
import ast.E.E;

public class Case extends I{
    private E nombreCase;
    private List<I> cuerpo_case;
    public Case(E nombreCase, List<I> cuerpo_case, int fila, int columna) {
        super(fila, columna);
        this.nombreCase = nombreCase;
        this.cuerpo_case = cuerpo_case;
        this.fila = fila;
        this.columna = columna;
    }


    public String toString() {
        String aux = "case( nombre: " + nombreCase + ", instrucciones: ";

        for(I ins : cuerpo_case) aux += ins.toString();
        aux += "}";

        return aux;

    }


    @Override
    public Instrucciones tipoInstruccion() {
        return Instrucciones.CASE;
    }
}