package asem;

import ast.E.*;
import ast.I.*;
import ast.T.*;

import java.util.List;

public class AnalizadorSemantico {
    private List<I> programa;
    private TablaSimbolos tabla = new TablaSimbolos();

    public AnalizadorSemantico(List<I> programa){
        this.programa = programa;
    }

    public boolean analizar(){
        return true;
    }
}
