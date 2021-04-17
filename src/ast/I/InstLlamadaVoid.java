package ast.I;

import ast.E.E;
import ast.T.TipoArgumento;

import java.util.List;

public class InstLlamadaVoid extends I{
    private E nombre;
    private List<E> argumentos;

    @Override
    public String toString() {
        return "InstCallVoidFun{" +
                "nombre=" + nombre +
                ", argumentos=" + argumentos +
                '}';
    }

    public InstLlamadaVoid(E nombre, List<E> argumentos, int fila, int columna) {
        super(fila, columna);
        this.nombre = nombre;
        this.argumentos = argumentos;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.LLAMDADAPROC;
    }
}
