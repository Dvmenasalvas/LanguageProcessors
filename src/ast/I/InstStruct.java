package ast.I;

import ast.E.E;

import java.util.List;

public class InstStruct extends I{
    private E identificador;
    private List<I> declaraciones;

    public InstStruct(E identificador, List<I> declaraciones, int fila, int columna) {
        super(fila, columna);
        this.identificador = identificador;
        this.declaraciones = declaraciones;
    }

    public List<I> getDeclaraciones() {
        return declaraciones;
    }

    public E getIdentificador() {
        return identificador;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.STRUCT;
    }

    @Override
    public String toString() {
        String aux = "{{I: Declaracion}" + identificador;
        for(I ins : declaraciones) aux += ins;
        return aux + "}";
    }
}
