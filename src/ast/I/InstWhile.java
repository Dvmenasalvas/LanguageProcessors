package ast.I;

import ast.E.E;

import java.util.List;

public class InstWhile extends I{
    private E condicion;
    private List<I> cuerpo;

    public InstWhile (E condicion, List<I> cuerpo, int fila, int columna){
        super(fila,columna);
        this.condicion = condicion;
        this.cuerpo = cuerpo;
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
