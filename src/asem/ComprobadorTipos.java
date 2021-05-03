package asem;

import ast.E.*;
import ast.I.*;
import ast.T.*;
import ast.Sentencia;
import errors.GestionErrores;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ComprobadorTipos {
    public boolean comprueba(I instruccion){
        switch (instruccion.tipoInstruccion()){
            case ASIG:
                InstAsignacion instruccionAsignacion = (InstAsignacion) instruccion;
                if(instruccionAsignacion.getIdentificador() instanceof Iden) {
                    Iden identificador = (Iden) instruccionAsignacion.getIdentificador();
                    if(identificador.getConstante()) {
                        GestionErrores.errorSemantico(
                                "Error de tipos. El identificador "
                                        + identificador.getNombre() +
                                        " corresponde con una constante o una función por lo que no es modificable.",
                                instruccion.getFila(), instruccion.getColumna());
                    }
                }
                E iden = instruccionAsignacion.getIdentificador();
                Tipo tipoOriginal = tiposExpresion();
                Tipo tipoAsignar = tiposExpresion();
                if(tipoOriginal.tipoEnumerado() == tipoAsignar.tipoEnumerado()) {
                    return true;
                }else {
                    GestionErrores.errorSemantico("Error de tipos en la asignación." +
                            " Los tipos no coinciden. Intentando asignar a " +
                            instruccionAsignacion.getIdentificador().toString() +
                            " el valor " + instruccionAsignacion.getValor().toString() +
                            ".Tipos: " + tipoOriginal + " " +
                            tipoAsignar, instruccion.getFila(), instruccion.getColumna());

                }
               break;

            case LLAMDADAPROC:
                InstLlamadaVoid intruccionLlamadaFun  = (InstLlamadaVoid) instruccion;
                Sentencia declaracion = intruccionLlamadaFun.getReferencia();
                InstDeclFun declaracionFunc = (InstDeclFun) declaracion;
                List<E> argumentos = intruccionLlamadaFun.getArgumentos();
                int i = 0;
                if(!(intruccionLlamadaFun.getNombre() instanceof Iden)) {
                    GestionErrores.errorSemantico(
                            "Error de tipos. El identificador de una función" +
                                    " tiene que ser de tipo iden",
                            instruccion.getFila(), instruccion.getColumna());
                }
                boolean correctArguments = true;
                for(TipoArgumento argumento : declaracionFunc.getArgumentos()) {
                    if(tiposExpresion() {
                        correctArguments = false;
                        GestionErrores.errorSemantico("Error tipos. El par�metro n�mero " + i + " no concuerda con el tipo del atributo de la funci�n. Atributo: " + ((Iden)atributo.getValue()).getNombre(),sentencia.getFila(),sentencia.getColumna());
                    }
                    i++;
                }
                if(correctArguments) return correctArguments;
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
