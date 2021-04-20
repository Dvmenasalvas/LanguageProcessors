package ast.I;

import ast.E.E;
import java.util.List;

public class InstSwitch extends I{

    private E condicion;
    private List<Case> cases;
    public InstSwitch(E condicion, List<Case> cases,int fila,int columna) {
        super(fila, columna);
        this.condicion = condicion;
        this.cases = cases;
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
