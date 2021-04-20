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
        String out = "{{I: If}{{Condicion}" + condicion + "}{{Cuerpo}";

        for(I ins : cuerpo_if) out += ins.toString();
        out += "}";
        if(cuerpo_else != null) {
            out += "{{Else}";
            for(I ins : cuerpo_else) out += ins.toString();
            out += "}";
        }
        out += "}";

        return out;
    }
}
