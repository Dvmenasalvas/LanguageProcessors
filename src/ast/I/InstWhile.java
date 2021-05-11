package ast.I;

import java.util.List;
import ast.E.*;

public class InstWhile extends I{
    private E condicion;
    private List<I> cuerpo;

    public InstWhile (E condicion, List<I> cuerpo, int fila, int columna){
        super(fila,columna);
        this.condicion = condicion;
        this.cuerpo = cuerpo;
    }

    public E getCondicion() {
        return condicion;
    }

    public List<I> getCuerpo() {
        return cuerpo;
    }

    @Override
    public String toString() {
        String aux = "{{I: While}{{Condicion}" + condicion + "}{{Cuerpo}";

        for(I ins : cuerpo) aux += ins.toString();
        aux += "}}";

        return aux;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.WHILE;
    }
}
