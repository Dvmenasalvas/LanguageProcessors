package asem;

import ast.I.I;

public class ComprobadorTipos {
    public boolean comprueba(I instruccion){
        switch (instruccion.tipoInstruccion()){
            case ASIG:
               break;
            case LLAMDADAPROC:
                break;
            case DECL:
                break;
            case DECLFUN:
                break;
            case STRUCT:
                break;
            case SWITCH:
                break;
            case WHILE:
                break;

        }
        return true;
    }
}
