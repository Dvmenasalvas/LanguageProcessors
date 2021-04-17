package ast.I;

import ast.E.E;
import java.util.List;

public class InstSwitch extends I{

    private E condicion;
    private List<Case> cases;
    private int fila;
    private int columna;
    public InstSwitch(E condicion, List<Case> cases,int fila,int columna) {
        super(fila, columna);
        this.condicion = condicion;
        this.cases = cases;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.SWITCH;
    }

    public E getCondicion() {
        return condicion;
    }

    public List<Case> getListCases() {
        return cases;
    }

}
