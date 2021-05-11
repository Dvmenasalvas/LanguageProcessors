package ast.I;

import ast.E.*;

public class InstPrint extends I{
    E expresion;
    public InstPrint(E expresion, int fila, int columna) {
        super(fila, columna);
        this.expresion = expresion;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.PRINT;
    }

    public String toString() {
        return "{{I: Print}{" + expresion + "}}";
    }
}
