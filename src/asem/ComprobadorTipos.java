package asem;

import ast.E.*;
import ast.I.*;
import ast.T.*;
import ast.Sentencia;
import errors.GestionErrores;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ComprobadorTipos {
    public boolean compruebaInstruccion(I instruccion){
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
                Tipo tipoOriginal = tipoExpresion(instruccionAsignacion.getIdentificador());
                Tipo tipoAsignar = tipoExpresion(instruccionAsignacion.getValor());
                if(tipoOriginal.tipoTipos() == tipoAsignar.tipoTipos()) {
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
                for(TipoArgumento atributo : declaracionFunc.getArgumentos()) {
                    if(tipoExpresion(argumentos.get(i)).tipoTipos() != atributo.getTipo().tipoTipos()) {
                        correctArguments = false;
                        GestionErrores.errorSemantico("Error tipos. El par�metro n�mero " + i +
                                " no concuerda con el tipo del atributo de la funci�n. Atributo: " +
                                ((Iden)atributo.getArgumento()).getNombre(),instruccion.getFila(),instruccion.getColumna());
                    }
                    i++;
                }
                if(correctArguments) return correctArguments;
                break;
            case DECL:
                break;
            case DECLFUN:
                InstDeclFun instruccionDeclaracionFuncion = (InstDeclFun) instruccion;
                Tipo tipoReturn = null;
                if(!(instruccionDeclaracionFuncion.getIdentificador() instanceof Iden)) {
                    GestionErrores.errorSemantico("Error: El identificador de la función no es un identificador",
                            instruccion.getFila(),instruccion.getColumna());
                }
                if(instruccionDeclaracionFuncion.getTipo() != null)
                    tipoReturn = tipoExpresion(instruccionDeclaracionFuncion.getRetorno());

                if(instruccionDeclaracionFuncion.getIdentificador().tipoExpresion() == TipoE.IDEN) {
                    AtomicBoolean correcto = new AtomicBoolean(true);

                    if(tipoReturn != null && tipoReturn.tipoTipos() != instruccionDeclaracionFuncion.getTipo().tipoTipos()){
                        GestionErrores.errorSemantico("Error de tipos. El tipo del return no coincide con el de la función.",
                                instruccion.getFila(),instruccion.getColumna());
                    }
                    instruccionDeclaracionFuncion.getCuerpo().forEach(x -> {correcto.set(compruebaInstruccion(x) && correcto.get());});
                    if(instruccionDeclaracionFuncion.getTipo() != null) correcto.set(correcto.get() && tipoReturn == instruccionDeclaracionFuncion.getTipo());
                    return correcto.get();
                } else {
                    GestionErrores.errorSemantico(
                            "Error de tipos. El nombre de la funcion tiene que ser necesariamente un identificador.",
                            instruccion.getFila(),instruccion.getColumna());
                }
                break;

            case IF:
                InstIf instruccionIf = (InstIf) instruccion;
                if(tipoExpresion(instruccionIf.getCondicion()).tipoTipos() == EnumeradoTipo.BOOLEAN){
                    AtomicBoolean correcto = new AtomicBoolean(true);
                    instruccionIf.getCuerpoIf().forEach(x -> {correcto.set(compruebaInstruccion(x) && correcto.get());});

                    if(instruccionIf.getCuerpoElse() != null) {
                        instruccionIf.getCuerpoElse().forEach(x -> {correcto.set(compruebaInstruccion(x) && correcto.get());});
                    }
                    return correcto.get();
                }else {
                    GestionErrores.errorSemantico("Error de tipos. La condición del if no es un booleano",
                            instruccion.getFila(),instruccion.getColumna());
                }
                break;
            case STRUCT:
                InstStruct instruccionStruct = (InstStruct) instruccion;
                if(!(instruccionStruct.getIdentificador() instanceof Iden)) {
                    GestionErrores.errorSemantico("Error de tipos. El identificador del struct no es un identificador.",
                            instruccion.getFila(),instruccion.getColumna());
                }
                AtomicBoolean correcto = new AtomicBoolean(true);
                instruccionStruct.getDeclaraciones().forEach(x -> {correcto.set(compruebaInstruccion(x) && correcto.get());});
                return correcto.get();
            case SWITCH:
                InstSwitch instruccionSwitch = (InstSwitch) instruccion;
                Tipo tipoCondicion = tipoExpresion(instruccionSwitch.getCondicion());
                AtomicBoolean correct = new AtomicBoolean(true);
                for(Case caso : instruccionSwitch.getCases()) {
                    if(!caso.getCuerpoCase().isEmpty()) {
                        if(caso.getCuerpoCase()!=null) {
                            if(tipoCondicion.tipoTipos() != tipoExpresion(caso.getExpresion()).tipoTipos()) {
                                correct.set(false);
                                GestionErrores.errorSemantico("Error de tipos. " +
                                        "El tipo del case no coincide con el de la variable del switch.",instruccion.getFila(),
                                        instruccion.getColumna());

                            }
                        }
                        caso.getCuerpoCase().forEach(x -> {correct.set(compruebaInstruccion(x) && correct.get());});
                    }
                }

                return correct.get();

            case WHILE:
                InstWhile instruccionWhile = (InstWhile) instruccion;
                if(tipoExpresion(instruccionWhile.getCondicion()).tipoTipos() == EnumeradoTipo.BOOLEAN) {
                    AtomicBoolean correctWhile = new AtomicBoolean(true);
                    instruccionWhile.getCuerpo().forEach(x -> {correctWhile.set(compruebaInstruccion(x) && correctWhile.get());});
                    return correctWhile.get();
                }else {
                    GestionErrores.errorSemantico("Error de tipos. La condición del while no es un booleano.",
                            instruccion.getFila(),instruccion.getColumna());
                }
                break;

        }
        return true;
    }
    public Tipo tipoExpresion(Sentencia sentencia){
        switch (sentencia.tipoSentencia()){
            case EXPRESION:
                E expresion = (E) sentencia;
                switch(expresion.tipoExpresion()) {
                    case FALSO:
                    case VERDADERO:
                        return new TipoBoolean(expresion.getFila(), expresion.getColumna());
                    case ENT:
                        return new TipoInt(expresion.getFila(), expresion.getColumna());
                    case FUNCION:
                        LlamadaFuncion llamada = (LlamadaFuncion) expresion;
                        List<E> argumentos = llamada.getArgumentos();
                        List<Tipo> tiposLlamada = new ArrayList<>();
                        if (!(llamada.getNombreFuncion() instanceof Iden)) {
                            GestionErrores.errorSemantico("El nombre de la función no es un identificador",
                                    sentencia.getFila(), sentencia.getColumna());
                        }
                        for (E argumento : argumentos) {
                            Tipo ti = tipoExpresion(argumento);
                            if (ti != null)
                                tiposLlamada.add(ti);
                        }
                        InstDeclFun declaracionFuncion = (InstDeclFun) llamada.getReferenciaDeclaracion();
                        int i = 0;
                        boolean coincidenTipos = true;
                        if (tiposLlamada.size() == declaracionFuncion.getArgumentos().size()) {
                            for (TipoArgumento atributo : declaracionFuncion.getArgumentos()) {
                                if (atributo.getTipo().tipoTipos() != tiposLlamada.get(i).tipoTipos()) {
                                    coincidenTipos = false;
                                    GestionErrores.errorSemantico("Error de tipos. El tipo del parámetro número " + i +
                                            " no es correcto (no coincide con su argumento)." +
                                            ((Iden) atributo.getArgumento()).getNombre(), sentencia.getFila(), sentencia.getColumna());
                                }
                                i++;
                            }
                            if (coincidenTipos) {
                                return llamada.getTipoReturn();
                            }
                        } else {
                            GestionErrores.errorSemantico("No hay el mismo número de atributos que de parámetros.",
                                    sentencia.getFila(), sentencia.getColumna());
                        }
                        break;
                    case IDEN:
                        Iden identificador = (Iden) expresion;
                        return identificador.getTipoVariable();
                    case NOT:
                        Not not = (Not) expresion;
                        if (tipoExpresion(not.getOpnd1()).tipoTipos() == EnumeradoTipo.BOOLEAN) {
                            return new TipoBoolean(expresion.getFila(), expresion.getColumna());
                        }
                        GestionErrores.errorSemantico("La operación de negación (!) solo debe de ser aplicada a un booleano",
                                sentencia.getFila(), sentencia.getColumna());
                        break;

                }
                break;
            case EXPRESION_BINARIA:
                EBin ebin = (EBin) sentencia;
                E operando1= ebin.getOpnd1();
                E operando2 = ebin.getOpnd2();
                Tipo tipoOperando1 = tipoExpresion(operando1);
                Tipo tipoOperando2 = tipoExpresion(operando2);

                if (tipoOperando1 == null) {
                    System.out.println("Operando devuelve null. Fallo en la casuística previa. Operando: " + operando1 );
                }
                else if (tipoOperando2 == null) {
                    System.out.println("Operando devuelve null. Fallo en la casuística previa. Operando: " + operando2 );
                }
                else if (tipoOperando1.tipoTipos() != EnumeradoTipo.ERROR && tipoOperando2.tipoTipos() != EnumeradoTipo.ERROR) {
                    switch (ebin.tipoExpresion()) {
                        case AND:
                        case OR:
                            if (tipoOperando1.tipoTipos() == EnumeradoTipo.BOOLEAN && tipoOperando2.tipoTipos() == EnumeradoTipo.BOOLEAN) {
                                return new TipoBoolean(sentencia.getFila(), sentencia.getColumna());
                            }
                            GestionErrores.errorSemantico("Error de tipos. Uno de los operandos de la operación booleana no es booleano. Operandos: "
                                    + operando1.toString() + " y " + operando2.toString(), sentencia.getFila(), sentencia.getColumna());
                            break;
                        case DIV:
                        case ELEV:
                        case MUL:
                        case RESTA:
                        case SUMA:
                        case MOD:
                            if (tipoOperando1.tipoTipos() == EnumeradoTipo.INT && tipoOperando2.tipoTipos() == EnumeradoTipo.INT) {
                                return new TipoInt(sentencia.getFila(), sentencia.getColumna());
                            }
                            GestionErrores.errorSemantico("Error de tipos. Uno de los operandos de la operación aritmética no es entero." +
                                    " Operandos: " + operando1.toString() + " y " + operando2.toString(), sentencia.getFila(),
                                    sentencia.getColumna());
                            break;
                        case IGUALIGUAL:
                        case DISTINTO:
                            if ((tipoOperando1.tipoTipos() == EnumeradoTipo.BOOLEAN || tipoOperando1.tipoTipos() == EnumeradoTipo.INT)
                                    && tipoOperando1.tipoTipos() == tipoOperando2.tipoTipos()) {
                                return new TipoBoolean(sentencia.getFila(), sentencia.getColumna());
                            }
                            GestionErrores.errorSemantico("Error de tipos. Los tipos para la comparación de igualdad no coinciden " +
                                    "o no son válidos. Operandos: " + operando1.toString() + " y " + operando2.toString(),
                                    sentencia.getFila(), sentencia.getColumna());
                            break;
                        case MAYORIGUAL:
                        case MAYOR:
                        case MENORIGUAL:
                        case MENOR:
                            if (tipoOperando1.tipoTipos() == EnumeradoTipo.INT && tipoOperando2.tipoTipos() == EnumeradoTipo.INT) {
                                return new TipoBoolean(sentencia.getFila(), sentencia.getColumna());
                            }
                            GestionErrores.errorSemantico("Error de tipos. Los tipos para la comparación no son enteros. Operandos: "
                                    + operando1.toString() + " y " + operando2.toString(), sentencia.getFila(), sentencia.getColumna());
                            break;
                        default:
                            break;

                    }
                }
            break;
            default:
                break;
        }
        return new TipoError(sentencia.getFila(), sentencia.getColumna());
    }
}
