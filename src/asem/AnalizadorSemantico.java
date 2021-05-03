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
                        break;
                    case NOT:
                        Not expNot = (Not) expresion;
                        vincula(expNot.opnd1());
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

        }
    }
}
