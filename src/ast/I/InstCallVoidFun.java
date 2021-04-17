package ast.I;

import ast.E.E;
import ast.T.Tipo;
import ast.T.TipoArgumento;

import java.util.List;

public class InstCallVoidFun extends I{
    private E nombre;
    private List<E> argumentos;

    @Override
    public String toString() {
        return "InstCallVoidFun{" +
                "nombre=" + nombre +
                ", argumentos=" + argumentos +
                '}';
    }

    public InstCallVoidFun(E nombre, List<E> argumentos, int fila, int columna) {
        super(fila, columna);
        this.nombre = nombre;
        this.argumentos = argumentos;
    }

    @Override
    public Instrucciones tipoInstruccion() {
        return null;
    }
}
