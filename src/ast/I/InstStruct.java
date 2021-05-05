package ast.I;

import ast.E.E;

import java.util.List;

public class InstStruct extends I{
    private E nombreTipo;
    private List<I> declaraciones;

    public InstStruct(E nombreTipo, List<I> declaraciones, int fila, int columna) {
        super(fila, columna);
        this.nombreTipo = nombreTipo;
        this.declaraciones = declaraciones;
    }

    public List<I> getDeclaraciones() {
        return declaraciones;
    }

    public E getIdentificador() {
        return nombreTipo;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.STRUCT;
    }

    @Override
    public String toString() {
        String aux = "{{I: Declaracion}" + nombreTipo;
        for(I ins : declaraciones) aux += ins;
        return aux + "}";
    }
}
