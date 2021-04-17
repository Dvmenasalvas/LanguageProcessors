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

    public Instrucciones tipoInstruccion() {return Instrucciones.IF;}
    public String toString() {
        String aux = "{{__If___}{{_Cond__}" + condicion + "}{{_Cuer__}";
        for(I ins : cuerpo_if) aux += ins;
        aux += "}";
        if(cuerpo_else != null) {
            aux += "{{_Else__}";
            for(I ins : cuerpo_else) aux += ins;
            aux += "}";
        }
        aux += "}";

        return aux;
    }

    public E getCondicion() {
        return condicion;
    }

    public List<I> getCuerpoIf() {
        return cuerpo_if;
    }

    public List<I> getCuerpoElse() {
        return cuerpo_else;
    }


}
