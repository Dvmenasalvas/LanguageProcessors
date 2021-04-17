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
        return "InstWhile{" +
                "condicion=" + condicion +
                ", cuerpo=" + cuerpo +
                '}';
    }

    @Override
    public Instrucciones tipoInstruccion() {
        return Instrucciones.WHILE;
    }
}
