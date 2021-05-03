package asem;

import ast.E.*;
import ast.I.*;
import ast.T.*;
import ast.Sentencia;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import errors.GestionErrores;

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
                I instruccion = (I) sentencia;
                switch(instruccion.tipoInstruccion()){
                    case ASIG:
                        InstAsignacion asignacion = (InstAsignacion) sentencia;
                        E iden = asignacion.getIdentificador();
                        iden.setAsignacion(true);
                        vincula(iden);
                        vincula(asignacion.getValor());
                        break;
                    default:
                        break;
                }
            case EXPRESION_BINARIA:
                EBin expBin = (EBin) sentencia;
                expBin.getOpnd1().setAsignacion(expBin.isAsignacion());
                expBin.getOpnd2().setAsignacion(expBin.isAsignacion());

                vincula(expBin.getOpnd1());
                vincula(expBin.getOpnd2());
                break;
            case EXPRESION:
                E expresion = (E) sentencia;

                switch(expresion.tipoExpresion()){
                    case FUNCION:
                        LlamadaFuncion llamada = (LlamadaFuncion) expresion;
                        Sentencia referenciaFuncion =
                                tabla.getSentenciaDeclaracion(((Iden) llamada.getNombreFuncion()).getNombre());
                        if(referenciaFuncion == null) {
                            GestionErrores.errorSemantico("Llamada a función " +
                                    ((Iden) llamada.getNombreFuncion()).getNombre() +
                                    " no existente.",sentencia.getFila(),sentencia.getColumna());
                        }else {
                            llamada.setReferencia(referenciaFuncion);
                            llamada.setTipoReturn(((InstDeclFun) referenciaFuncion).getTipo());
                            llamada.getArgumentos().forEach(x -> vincula(x));
                        }
                        break;
                    case IDEN:
                        Iden identificador = (Iden) expresion;
                        Sentencia declaracion = tabla.getSentenciaDeclaracion(identificador.getNombre());
                        //Constante
                        if((declaracion instanceof InstDecl) && ((InstDecl)declaracion).isConstante() && identificador.isAsignacion()) {
                            identificador.setTipoVariable(((InstDecl)declaracion).getTipo());
                            GestionErrores.errorSemantico("Se ha tratado de asignar un identificador constante: " +
                                    identificador.getNombre() +".", sentencia.getFila(), sentencia.getColumna());
                        //No existe identificador
                        }else if(declaracion == null) {
                                GestionErrores.errorSemantico("El identificador " + identificador.getNombre() +
                                        " no ha sido declarado.",sentencia.getFila(),sentencia.getColumna());
                        //Tipo base
                        }else if(declaracion instanceof InstDecl) {
                                    tabla.addTipoVariable(identificador.getNombre(), ((InstDecl)declaracion).getTipo());
                                    identificador.setTipoVariable(((InstDecl)declaracion).getTipo());
                                    identificador.setReferencia(declaracion);
                        //Argumento de función
                        }else if(declaracion instanceof InstDeclFun) {
                            InstDeclFun declaracionFuncion = (InstDeclFun) declaracion;
                            for(TipoArgumento argumento: declaracionFuncion.getArgumentos()) {
                                Iden identificadorArgumento = (Iden)argumento.getArgumento();
                                if(identificadorArgumento.getNombre().equals(identificador.getNombre())) {
                                    identificador.setTipoVariable(argumento.getTipo());
                                    break;
                                }
                            }
                        //Struct
                        }else if(declaracion instanceof InstStruct) {
                            TipoStruct tipoVariable = new TipoStruct(identificador, identificador.getFila(), identificador.getColumna());
                            tabla.addTipoVariable(identificador.getNombre(), tipoVariable);
                            identificador.setTipoVariable(tipoVariable);
                        }
                    case NOT:
                        Not expNot = (Not) expresion;
                        vincula(expNot.getOpnd1());
                        break;
                    default:
                        break;
                }
                break;

            case TIPO:
                Tipo tipo = (Tipo) sentencia;
                switch(tipo.tipoTipos()) {
                    case STRUCT:
                        TipoStruct tipoStruct = (TipoStruct) tipo;
                        Sentencia referenciaSentencia =
                                tabla.getSentenciaDeclaracion(((Iden) tipoStruct.getNombre()).getNombre());
                        if(referenciaSentencia == null) {
                            GestionErrores.errorSemantico("Struct " +
                                    ((Iden) tipoStruct.getNombre()).getNombre() + " no declarado."
                                    ,sentencia.getFila(),sentencia.getColumna());
                        }else {
                            tipoStruct.setReferenciaDeclaracion(referenciaSentencia);
                        }
                        break;
                    case ARRAY:
                        TipoArray tipoArray = (TipoArray) tipo;
                        vincula(tipoArray.getTipoBase());
                        tipoArray.getDimShape().forEach(x -> vincula(x));

                        break;
                    default:
                        break;
                }
                break;

        }
    }
}
