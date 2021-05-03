package asem;

import ast.E.*;
import ast.I.*;
import ast.Sentencia;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class AnalizadorSemantico {
    private List<I> programa;
    private TablaSimbolos tabla = new TablaSimbolos();
    private ComprobadorTipos comprobadorTipos = new ComprobadorTipos();

    public AnalizadorSemantico(List<I> programa){
        this.programa = programa;
    }

    public boolean analizar(){
        tabla.abreBloque();
        for(I  instruccion : programa) vincula(instruccion);
        tabla.cierraBloque();

        AtomicBoolean tiposCorrectos = new AtomicBoolean(true);
        programa.forEach(x -> {tiposCorrectos.set(comprobadorTipos.comprueba(x) && tiposCorrectos.get());});
        if(tiposCorrectos.get()) System.out.println("Comprobacion de Tipos sin errores");
        return tiposCorrectos.get();
    }

    private void vincula(Sentencia sentencia) {
        switch(sentencia.tipoSentencia()){
            case INSTRUCCION:
                break;
            case EXPRESION_BINARIA:
                EBin expBin = (EBin) sentencia;

                vincula(expBin.opnd1());
                vincula(expBin.opnd2());
                break;
            case EXPRESION:
                E expresion = (E) sentencia;

                switch(expresion.tipoExpresion()){
                    case FUNCION:
                        break;
                    case IDEN:
                        break;
                    case NOT:
                        break;
                }
        }
    }
}
