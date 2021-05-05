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


                    if (identificador.getConstante()) {
                        GestionErrores.errorSemantico(
                                "Error de tipos. El identificador "
                                        + identificador.getNombre() +
                                        " corresponde con una constante o una función por lo que no es modificable.",
                                instruccion.getFila(), instruccion.getColumna());
                        return false;
                    }
                    E iden = instruccionAsignacion.getIdentificador();
                    Tipo tipoOriginal = tipoExpresion(instruccionAsignacion.getIdentificador());
                    Tipo tipoAsignar;

                    EnumeradoTipo tipoBaseElemento = null;
                    TipoArray tipoArray = null;
                    if (tipoOriginal != null && tipoOriginal.tipoTipos() == EnumeradoTipo.ARRAY) {
                        tipoArray = (TipoArray) tipoOriginal;
                        tipoBaseElemento = tipoArray.getTipoBase().tipoTipos();

                        EnumeradoTipo tipoBaseArray = tipoArray.getTipoBase().tipoTipos();


                        int primeraDim = Integer.parseInt(((Ent) tipoArray.getDimShape().get(0)).valor());
                        if (primeraDim != instruccionAsignacion.getValor().size()){
                            GestionErrores.errorSemantico("Error de tipos. Debe haber tantos valores como se indica en la " +
                                            "primera dimensión del vector. Dimensión indicada: " + primeraDim +
                                            ", dimensión recibida: " + instruccionAsignacion.getValor().size(),
                                    instruccion.getFila(),instruccion.getColumna());
                        }


                        if (tipoArray.getDimShape().size() == 1) {
                            for (E val: instruccionAsignacion.getValor()) {
                                EnumeradoTipo tipoElemento = tipoExpresion(val).tipoTipos();
                                tipoBaseElemento = tipoArray.getTipoBase().tipoTipos();
                                if (tipoBaseArray != tipoElemento) {
                                    GestionErrores.errorSemantico("Error de tipos. Se ha intentado formar un array de tipo " +
                                                    tipoBaseArray + " con el elemento " + val +
                                                    " de tipo " + tipoElemento + ".",
                                            instruccion.getFila(), instruccion.getColumna());
                                }
                            }
                        }
                        //Arrays varias dims
                        else {
                            for (E val : instruccionAsignacion.getValor()) {
                                EnumeradoTipo tipoElemento = tipoExpresion(val).tipoTipos();
                                if (tipoExpresion(val).tipoTipos() == EnumeradoTipo.ARRAY) {
                                    TipoArray tipoElementoArray = (TipoArray) tipoExpresion(val);
                                    EnumeradoTipo tipoBaseElemento2 = tipoElementoArray.getTipoBase().tipoTipos();


                                    if (tipoBaseElemento != tipoBaseElemento2) {
                                        GestionErrores.errorSemantico("Error de tipos. Se ha intentado formar un array de tipo " +
                                                tipoBaseElemento + " con el array " + val +
                                                " de tipo " + tipoBaseElemento2 + ".", instruccion.getFila(), instruccion.getColumna());
                                        return false;
                                    }

                                    int dimArray = tipoArray.getDimShape().size();
                                    int dimElemento = tipoElementoArray.getDimShape().size();
                                    int dimArrayMenosUno = dimArray - 1;
                                    if (dimArrayMenosUno != dimElemento) {
                                        GestionErrores.errorSemantico("Error de tipos. Se ha intentado formar un array de " +
                                                        dimArray + " dimensiones con el array " + val +
                                                        " de dimensión " + dimElemento + ". La dimensión de " + val + " debería ser " +
                                                        dimArrayMenosUno + ".",
                                                instruccion.getFila(), instruccion.getColumna());
                                        return false;
                                    }
                                    //Comprobamos que las dimensiones sean iguales una a una
                                    for (int j = 1; j < dimArray; j++) {
                                        int dimA = Integer.parseInt(((Ent) tipoArray.getDimShape().get(j)).valor());
                                        int dimB = Integer.parseInt(((Ent) tipoElementoArray.getDimShape().get(j - 1)).valor());
                                        if (dimA != dimB) {
                                            GestionErrores.errorSemantico("Error de tipos. Al formar el array " +
                                                            instruccionAsignacion.getIdentificador() + " con el elemento "
                                                            + val + ". No encaja la dimensión número " + j
                                                            + " del array (" + dimA + ") con la del elemento (" +
                                                            dimB + ").",
                                                    instruccion.getFila(), instruccion.getColumna());
                                            return false;
                                        }
                                    }
                                } else {
                                    GestionErrores.errorSemantico("Error de tipos. Se ha intentado formar un array " +
                                                    "multidimensional " + instruccionAsignacion.getIdentificador() +
                                                    " con el elemento " + val + " de tipo " + tipoElemento + " (no array).",
                                            instruccion.getFila(),instruccion.getColumna());
                                    return false;
                                }
                            }
                        }
                    }
                    return true;
                } else {
                    GestionErrores.errorSemantico(
                            "Error de tipos. Una asignación debe comenzar con un identificador.",
                            instruccion.getFila(), instruccion.getColumna());
                }
            case LLAMDADAPROC:
                InstLlamadaVoid intruccionLlamadaFun  = (InstLlamadaVoid) instruccion;
                InstDeclFun declaracionFunc = (InstDeclFun) intruccionLlamadaFun.getReferencia();
                List<E> argumentos = intruccionLlamadaFun.getArgumentos();
                int i = 0;
                if(!(intruccionLlamadaFun.getNombre() instanceof Iden)) {
                    GestionErrores.errorSemantico(
                            "Error de tipos. El identificador de una función" +
                                    " tiene que ser de tipo iden",
                            instruccion.getFila(), instruccion.getColumna());
                    return false;
                }
                boolean correctArguments = true;
                if(declaracionFunc != null ) {
                    for (TipoArgumento argumento : declaracionFunc.getArgumentos()) {
                        if (tipoExpresion(argumentos.get(i)).tipoTipos() != argumento.getTipo().tipoTipos()) {
                            correctArguments = false;
                            GestionErrores.errorSemantico("Error tipos. El parámetro número " + i +
                                    " no concuerda con el tipo del atributo de la función. Argumento: " +
                                    ((Iden) argumento.getArgumento()).getNombre(), instruccion.getFila(), instruccion.getColumna());
                            return false;
                        }
                        i++;
                    }
                }
                if(correctArguments) return true;
                break;
            case DECL:
                InstDecl instruccionDeclaracion = (InstDecl) instruccion;
                if(instruccionDeclaracion.getIdentificador().tipoExpresion() == TipoE.IDEN) {
                    Tipo tipoDeclaracion = instruccionDeclaracion.getTipo();
                    boolean correct = true;
                    if(tipoDeclaracion.tipoTipos() == EnumeradoTipo.ARRAY) {
                        TipoArray tipoArray = (TipoArray) tipoDeclaracion;
                        for (E dimExp : tipoArray.getDimShape()) {
                            if (!(dimExp instanceof Ent)) {
                                GestionErrores.errorSemantico("La dimension de los arrays debe ser indicada con un entero." +
                                                " No se permiten identificadores u otras expresiones: " + dimExp,
                                        instruccion.getFila(), instruccion.getColumna());
                            }
                        }
                    }
                    if(instruccionDeclaracion.getExpresiones() != null) {
                        if(tipoDeclaracion.tipoTipos() == EnumeradoTipo.ARRAY) {
                           TipoArray tipoArray = (TipoArray)tipoDeclaracion;
                           int primeraDim = Integer.parseInt(((Ent) tipoArray.getDimShape().get(0)).valor());
                           if (primeraDim != instruccionDeclaracion.getExpresiones().size()){
                               GestionErrores.errorSemantico("Error de tipos. Debe haber tantos valores como se indica en la " +
                                               "primera dimensión del vector. Dimensión indicada: " + primeraDim +
                                       ", dimensión recibida: " + instruccionDeclaracion.getExpresiones().size(),
                                       instruccion.getFila(),instruccion.getColumna());
                           }
                           EnumeradoTipo tipoBaseArray = tipoArray.getTipoBase().tipoTipos();
                           //Array de 1 dimensión
                           if (tipoArray.getDimShape().size() == 1){
                               for (E elemento: instruccionDeclaracion.getExpresiones()){
                                   EnumeradoTipo tipoElemento = tipoExpresion(elemento).tipoTipos();
                                   if (tipoBaseArray != tipoElemento){
                                       GestionErrores.errorSemantico("Error de tipos. Se ha intentado formar un array de tipo " +
                                                       tipoBaseArray + " con el elemento " + elemento +
                                                        " de tipo " + tipoElemento + ".",
                                               instruccion.getFila(),instruccion.getColumna());
                                   }
                               }
                           //Array de varias dimensiones
                           } else {
                               for (E elemento: instruccionDeclaracion.getExpresiones()){
                                   EnumeradoTipo tipoElemento = tipoExpresion(elemento).tipoTipos();
                                   if (tipoExpresion(elemento).tipoTipos() == EnumeradoTipo.ARRAY){
                                       TipoArray tipoElementoArray = (TipoArray) tipoExpresion(elemento);
                                       EnumeradoTipo tipoBaseElemento = tipoElementoArray.getTipoBase().tipoTipos();
                                       if (tipoBaseArray != tipoBaseElemento){
                                           GestionErrores.errorSemantico("Error de tipos. Se ha intentado formar un array de tipo " +
                                                           tipoBaseArray + " con el array " + elemento +
                                                           " de tipo " + tipoBaseElemento + ".",
                                                   instruccion.getFila(),instruccion.getColumna());
                                           return false;
                                       }
                                       int dimArray = tipoArray.getDimShape().size();
                                       int dimElemento = tipoElementoArray.getDimShape().size();
                                       int dimArrayMenosUno = dimArray - 1;
                                       if (dimArrayMenosUno != dimElemento){
                                           GestionErrores.errorSemantico("Error de tipos. Se ha intentado formar un array de " +
                                                           dimArray + " dimensiones con el array " + elemento +
                                                           " de dimensión " + dimElemento + ". La dimensión de " + elemento + " debería ser " +
                                                    dimArrayMenosUno + ".",
                                                   instruccion.getFila(),instruccion.getColumna());
                                           return false;
                                       }
                                       //Comprobamos que las dimensiones sean iguales una a una
                                       for(int j = 1; j < dimArray; j++){
                                           int dimA = Integer.parseInt(((Ent)tipoArray.getDimShape().get(j)).valor());
                                           int dimB = Integer.parseInt(((Ent)tipoElementoArray.getDimShape().get(j - 1)).valor());
                                           if(dimA != dimB){
                                               GestionErrores.errorSemantico("Error de tipos. Al formar el array " +
                                                               instruccionDeclaracion.getIdentificador()+ " con el elemento " + elemento +
                                                               ". No encaja la dimensión número " + j + " del array (" + dimA + ") con la del elemento (" +
                                                               dimB + ").",
                                                       instruccion.getFila(),instruccion.getColumna());
                                               return false;
                                           }
                                       }
                                   } else {
                                       GestionErrores.errorSemantico("Error de tipos. Se ha intentado formar un array " +
                                                       "multidimensional " + instruccionDeclaracion.getIdentificador() +
                                                       " con el elemento " + elemento + " de tipo " + tipoElemento + " (no array).",
                                               instruccion.getFila(),instruccion.getColumna());
                                       return false;
                                   }
                               }

                           }
                        } else {
                            E valor = instruccionDeclaracion.getExpresiones().get(0);
                            Tipo tipoValor = tipoExpresion(valor);
                            if (tipoValor.tipoTipos() != tipoDeclaracion.tipoTipos()) {
                                correct = false;
                                GestionErrores.errorSemantico("Error de tipos. " +
                                                "La declaración está mal tipada. Asigna a la variable: " +
                                                instruccionDeclaracion.getIdentificador() + ", el valor: " + valor +
                                                ". Tipo variable: " + tipoDeclaracion + ". " + tipoValor + "."
                                        , instruccion.getFila(), instruccion.getColumna());
                            }
                        }
                        return correct;
                    }
                }else {
                    GestionErrores.errorSemantico("Error de tipos. La variable de una declaración " +
                                    "tiene que ser necesariamente un identificador.", instruccion.getFila(),instruccion.getColumna());
                }
                break;
            case DECLFUN:
                InstDeclFun instruccionDeclaracionFuncion = (InstDeclFun) instruccion;
                Tipo tipoReturn = null;
                if(!(instruccionDeclaracionFuncion.getIdentificador() instanceof Iden)) {
                    GestionErrores.errorSemantico(
                            "Error: El identificador de la función no es un identificador",
                            instruccion.getFila(),instruccion.getColumna());
                    return false;
                }
                if(instruccionDeclaracionFuncion.getTipo() != null)
                    tipoReturn = tipoExpresion(instruccionDeclaracionFuncion.getRetorno());

                if(instruccionDeclaracionFuncion.getIdentificador().tipoExpresion() == TipoE.IDEN) {
                    AtomicBoolean correcto = new AtomicBoolean(true);

                    if(tipoReturn != null && tipoReturn.tipoTipos() != instruccionDeclaracionFuncion.getTipo().tipoTipos()){
                        GestionErrores.errorSemantico(
                                "Error de tipos. El tipo del return no coincide con el de la función.",
                                instruccion.getFila(),instruccion.getColumna());
                        return false;
                    }
                    instruccionDeclaracionFuncion.getCuerpo().forEach(x -> {correcto.set(compruebaInstruccion(x) && correcto.get());});
                    if(instruccionDeclaracionFuncion.getTipo() != null)
                        correcto.set(correcto.get() && tipoReturn == instruccionDeclaracionFuncion.getTipo());
                    return correcto.get();
                } else {
                    GestionErrores.errorSemantico(
                            "Error de tipos. El nombre de la funcion tiene que ser necesariamente un identificador.",
                            instruccion.getFila(),instruccion.getColumna());
                    return false;
                }

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
                    return false;
                }
            case STRUCT:
                InstStruct instruccionStruct = (InstStruct) instruccion;
                if(!(instruccionStruct.getIdentificador() instanceof Iden)) {
                    GestionErrores.errorSemantico("Error de tipos. El identificador del struct no es un identificador.",
                            instruccion.getFila(),instruccion.getColumna());
                    return false;
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
                            if(tipoCondicion != null && !caso.isDefault() &&
                                    tipoCondicion.tipoTipos() != tipoExpresion(caso.getExpresion()).tipoTipos()) {
                                correct.set(false);
                                GestionErrores.errorSemantico("Error de tipos. " +
                                        "El tipo del case no coincide con el de la variable del switch.",
                                        instruccion.getFila(), instruccion.getColumna());
                                return false;
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
                    GestionErrores.errorSemantico(
                            "Error de tipos. La condición del while no es un booleano.",
                            instruccion.getFila(),instruccion.getColumna());
                    return false;
                }

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
                        if (declaracionFuncion != null) {
                            if (tiposLlamada.size() == declaracionFuncion.getArgumentos().size()) {
                                for (TipoArgumento atributo : declaracionFuncion.getArgumentos()) {
                                    if (atributo.getTipo().tipoTipos() != tiposLlamada.get(i).tipoTipos()) {
                                        coincidenTipos = false;
                                        GestionErrores.errorSemantico(
                                                "Error de tipos. El tipo del parámetro número " + i +
                                                        " no es correcto (no coincide con su argumento)." +
                                                        ((Iden) atributo.getArgumento()).getNombre(),
                                                sentencia.getFila(), sentencia.getColumna());
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
                        GestionErrores.errorSemantico(
                                "La operación de negación (!) solo debe de ser aplicada a un booleano",
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
                    System.out.println(
                            "Operando devuelve null. Fallo en la casuística previa. Operando: " + operando1 );
                }
                else if (tipoOperando2 == null) {
                    System.out.println(
                            "Operando devuelve null. Fallo en la casuística previa. Operando: " + operando2 );
                }
                else if (tipoOperando1.tipoTipos() != EnumeradoTipo.ERROR
                        && tipoOperando2.tipoTipos() != EnumeradoTipo.ERROR) {
                    switch (ebin.tipoExpresion()) {
                        case AND:
                        case OR:
                            if (tipoOperando1.tipoTipos() == EnumeradoTipo.BOOLEAN &&
                                    tipoOperando2.tipoTipos() == EnumeradoTipo.BOOLEAN) {
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
                            if (tipoOperando1.tipoTipos() == EnumeradoTipo.INT &&
                                    tipoOperando2.tipoTipos() == EnumeradoTipo.INT) {
                                return new TipoInt(sentencia.getFila(), sentencia.getColumna());
                            }
                            GestionErrores.errorSemantico(
                                    "Error de tipos. Uno de los operandos de la operación aritmética no es entero." +
                                    " Operandos: " + operando1.toString() + " y " + operando2.toString(), sentencia.getFila(),
                                    sentencia.getColumna());
                            break;
                        case IGUALIGUAL:
                        case DISTINTO:
                            if ((tipoOperando1.tipoTipos() == EnumeradoTipo.BOOLEAN ||
                                    tipoOperando1.tipoTipos() == EnumeradoTipo.INT)
                                    && tipoOperando1.tipoTipos() == tipoOperando2.tipoTipos()) {
                                return new TipoBoolean(sentencia.getFila(), sentencia.getColumna());
                            }
                            GestionErrores.errorSemantico(
                                    "Error de tipos. Los tipos para la comparación de igualdad no coinciden " +
                                    "o no son válidos. Operandos: " + operando1.toString() + " y " + operando2.toString(),
                                    sentencia.getFila(), sentencia.getColumna());
                            break;
                        case MAYORIGUAL:
                        case MAYOR:
                        case MENORIGUAL:
                        case MENOR:
                            if (tipoOperando1.tipoTipos() == EnumeradoTipo.INT &&
                                    tipoOperando2.tipoTipos() == EnumeradoTipo.INT) {
                                return new TipoBoolean(sentencia.getFila(), sentencia.getColumna());
                            }
                            GestionErrores.errorSemantico("Error de tipos. Los tipos para la comparación no son enteros. Operandos: "
                                    + operando1.toString() + " y " + operando2.toString(), sentencia.getFila(), sentencia.getColumna());
                            break;
                        case PUNTO:
                            if(tipoOperando1.tipoTipos() == EnumeradoTipo.STRUCT){
                                TipoStruct tipoStruct = (TipoStruct) tipoOperando1;
                                InstStruct sentenciaStruct = (InstStruct) tipoStruct.getReferenciaDeclaracion();
                                if(sentenciaStruct == null) {
                                    GestionErrores.errorSemantico(
                                            "No existe ningun struct con este nombre" + operando1
                                                    , sentencia.getFila(), sentencia.getColumna());
                                }
                                else{
                                    for(I instruccion : sentenciaStruct.getDeclaraciones()) {
                                        if(instruccion instanceof InstDecl) {
                                            Iden atributoStruct = (Iden)((InstDecl) instruccion).getIdentificador();
                                            if(atributoStruct.getNombre() == ((Iden) operando2).getNombre()) {
                                                return atributoStruct.getTipoVariable();
                                            }
                                        }
                                    }
                                }
                            }
                            GestionErrores.errorSemantico(
                                    "Error de tipos. Operandos: " +
                                            operando1.toString() + " y " +
                                            operando2.toString(), sentencia.getFila(), sentencia.getColumna());

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
