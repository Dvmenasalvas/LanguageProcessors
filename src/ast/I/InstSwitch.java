package ast.I;

import ast.E.E;
import ast.Sentencia;

import java.util.List;

public class InstSwitch extends I{

    private E condicion;
    private List<Case> cases;
    private Sentencia referenciaDeclaraciónVariable;
    public InstSwitch(E condicion, List<Case> cases,int fila,int columna) {
        super(fila, columna);
        this.condicion = condicion;
        this.cases = cases;
    }

    public List<Case> getCases() {
        return cases;
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

        for (Case single_case : cases) {
            aux += "{" + single_case + "}";
        }
        aux += "}";

        return aux;
    }
}
