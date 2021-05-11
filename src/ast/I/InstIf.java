package ast.I;

import java.util.List;
import ast.E.*;

public class InstIf extends I {

    private E condicion;
    private List<I> cuerpoIf;
    private List<I> cuerpoElse;
    public InstIf(E condicion, List<I> cuerpoIf, List<I> cuerpoElse, int fila, int columna) {
        super(fila,columna);
        this.condicion = condicion;
        this.cuerpoIf = cuerpoIf;
        this.cuerpoElse = cuerpoElse;
    }

    public EnumeradoInstrucciones tipoInstruccion() {return EnumeradoInstrucciones.IF;}

    public List<I> getCuerpoElse() {
        return cuerpoElse;
    }

    public List<I> getCuerpoIf() {
        return cuerpoIf;
    }

    public E getCondicion() {
        return condicion;
    }

    @Override
    public String toString() {
        String out = "{{I: If}{{Condicion}" + condicion + "}{{Cuerpo}";

        for(I ins : cuerpoIf) out += ins.toString();
        out += "}";
        if(cuerpoElse != null) {
            out += "{{Else}";
            for(I ins : cuerpoElse) out += ins.toString();
            out += "}";
        }
        out += "}";

        return out;
    }
}
