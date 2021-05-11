package ast.I;

import ast.Sentencia;
import ast.E.*;

import java.util.List;

public class InstLlamadaVoid extends I{
    private E nombre;
    private List<E> argumentos;
    private Sentencia referencia;

    public InstLlamadaVoid(E nombre, List<E> argumentos, int fila, int columna) {
        super(fila, columna);
        this.nombre = nombre;
        this.argumentos = argumentos;
    }



    @Override
    public String toString() {
        String aux = "{{LlamadaFuncion}{" + nombre + "}{{Argumentos}";
        if(argumentos!=null)for(E argumento : argumentos) aux += argumento.toString();
        aux += "}}";
        return aux;
    }

    public E getNombre() {
        return nombre;
    }

    public List<E> getArgumentos() {
        return argumentos;
    }

    public Sentencia getReferencia() {
        return referencia;
    }

    public void setReferencia(Sentencia referencia) {
        this.referencia = referencia;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.LLAMDADAPROC;
    }
}
