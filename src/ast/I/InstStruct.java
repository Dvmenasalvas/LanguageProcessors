package ast.I;

import ast.E.E;

import java.util.List;

public class InstStruct extends I{
    private E nombre;
    private List<I> declaraciones;

    public InstStruct(E nombre, List<I> declaraciones, int fila, int columna) {
        super(fila, columna);
        this.nombre = nombre;
        this.declaraciones = declaraciones;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.STRUCT;
    }

    @Override
    public String toString() {
        String aux = "{{I: Declaracion}" + nombre;
        for(I ins : declaraciones) aux += ins;
        return aux + "}";
    }
}
