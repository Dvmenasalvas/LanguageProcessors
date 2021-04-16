package ast.I;

import ast.E.E;

public class InstAsignacion extends I {
    private E identificador;
    private E valor;

    public InstAsignacion(E iden, E valor,int fila,int columna) {
        super(fila,columna);
        this.identificador = iden;
        this.valor = valor;
    }

    public Instrucciones tipoInstruccion() {return Instrucciones.ASIG;}

    public String toString() {
        return  "{{_Asig__}" + identificador.toString() + valor.toString() + "}";
    }
    public E getIdentificador() {
        return identificador;
    }

    public E getValor() {
        return valor;
    }
}
