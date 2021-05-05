package ast.I;

import ast.E.E;

import java.util.List;

public class InstAsignacion extends I {
    private E identificador;
    private List<E> valores;

    public InstAsignacion(E iden, List<E> valores, int fila,int columna) {
        super(fila,columna);
        this.identificador = iden;
        this.valores = valores;
    }

    public List<E> getValor() {
        return valores;
    }

    public E getIdentificador() {
        return identificador;
    }

    public EnumeradoInstrucciones tipoInstruccion() {return EnumeradoInstrucciones.ASIG;}

    @Override
    public String toString() {
        String aux = "{{Asignacion}";
        for(E valor : valores) aux += valor;
        return aux + "}";
    }
}
