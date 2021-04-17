package ast.I;

import java.util.List;
import ast.E.E;

public class InstIf extends I {

    private E condicion;
    private List<I> cuerpo_if;
    private List<I> cuerpo_else;
    public InstIf(E condicion, List<I> cuerpo_if, List<I> cuerpo_else,int fila,int columna) {
        super(fila,columna);
        this.condicion = condicion;
        this.cuerpo_if = cuerpo_if;
        this.cuerpo_else = cuerpo_else;
    }

    public EnumeradoInstrucciones tipoInstruccion() {return EnumeradoInstrucciones.IF;}

    @Override
    public String toString() {
        return "InstIf{" +
                "condicion=" + condicion +
                ", cuerpo_if=" + cuerpo_if +
                ", cuerpo_else=" + cuerpo_else +
                '}';
    }
}
