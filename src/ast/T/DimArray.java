package ast.T;

import ast.E.E;

import java.util.List;

public class DimArray {
    private int columna, fila;
    private int dimNum;
    private List<E> dimSize;

    public DimArray(int dimNum, List<E> dimSize, int fila, int columna) {
        this.dimNum = dimNum;
        this.dimSize = dimSize;
        this.fila = fila;
        this.columna = columna;
    }

    public int getDimNum() {
        return dimNum;
    }

    public List<E> getDimSize() {
        return dimSize;
    }

}
