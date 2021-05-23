package ast.I;

import ast.Sentencia;
import ast.E.*;

import java.util.List;


public class InstSwitch extends I{

    private E condicion;
    private List<InstCase> instCases;
    private Sentencia referenciaDeclaraciónVariable;
    public InstSwitch(E condicion, List<InstCase> instCases, int fila, int columna) {
        super(fila, columna);
        this.condicion = condicion;
        this.instCases = instCases;
    }

    public List<InstCase> getCases() {
        return instCases;
    }

    public void setReferenciaDeclaraciónVariable(Sentencia referenciaDeclaraciónVariable) {
        this.referenciaDeclaraciónVariable = referenciaDeclaraciónVariable;
    }

    public E getCondicion() {
        return condicion;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.SWITCH;
    }


    @Override
    public String toString() {
        String aux = "{{Switch}{{Cond}" + condicion + "}";

        for (InstCase single_Inst_case : instCases) {
            aux += single_Inst_case;
        }
        aux += "}";

        return aux;
    }
}
