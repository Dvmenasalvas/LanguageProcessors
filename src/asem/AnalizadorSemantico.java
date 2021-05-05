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
        for(I  instruccion : programa) {
            vincula(instruccion);
        }
        tabla.cierraBloque();

        AtomicBoolean tiposCorrectos = new AtomicBoolean(true);
        programa.forEach(x -> {tiposCorrectos.set(comprobadorTipos.compruebaInstruccion(x) && tiposCorrectos.get());});
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

                    case DECL:
                        InstDecl declaracion = (InstDecl) sentencia;
                        Iden identificadorV = (Iden)declaracion.getIdentificador();
                        identificadorV.setConstante(declaracion.isConstante());
                        identificadorV.setReferencia(declaracion);
                        identificadorV.setTipoVariable(declaracion.getTipo());
                        vincula(declaracion.getTipo());
                        tabla.insertaId(identificadorV.getNombre(), declaracion);
                        List<E> valorInicial = declaracion.getExpresiones();
                        if(valorInicial != null) valorInicial.forEach(x -> vincula(x));
                        break;

                    case LLAMDADAPROC:
                        InstLlamadaVoid llamadaVoid = (InstLlamadaVoid) sentencia;
                        Iden id  =(Iden)llamadaVoid.getNombre();
                        Sentencia referenciaDeclaracion =
                                tabla.getSentenciaDeclaracion(
                                        id.getNombre());
                        if(referenciaDeclaracion!=null) {
                            llamadaVoid.setReferencia(referenciaDeclaracion);
                            llamadaVoid.getArgumentos().forEach(x -> vincula(x));
                        }else {
                            GestionErrores.errorSemantico("El procedimiento " +
                                    llamadaVoid.getNombre() +
                                    " no ha sido declarado. Solo se puede llamar a funciones declaradas anteriormente",
                                    sentencia.getFila(),sentencia.getColumna());
                        }
                        break;


                    case DECLFUN:
                        InstDeclFun declaracionFun = (InstDeclFun) sentencia;
                        Tipo tipoFuncion = declaracionFun.getTipo();
                        if(tipoFuncion != null) vincula(tipoFuncion);
                        Iden identificadorFuncion = (Iden)declaracionFun.getIdentificador();
                        tabla.insertaId(identificadorFuncion.getNombre(), declaracionFun);
                        identificadorFuncion.setConstante(true);

                        tabla.abreBloque();
                        List<TipoArgumento> listaArgumentos = declaracionFun.getArgumentos();
                        for(TipoArgumento argumento : listaArgumentos) {
                            tabla.insertaId(((Iden)argumento.getArgumento()).getNombre(), declaracionFun);
                            Iden identificadorParametro = (Iden)argumento.getArgumento();
                            identificadorParametro.setTipoVariable(argumento.getTipo());
                            tabla.addTipoVariable(identificadorParametro.getNombre(), argumento.getTipo());
                            vincula(argumento.getTipo());
                        }

                        List<I> cuerpoFuncion = declaracionFun.getCuerpo();
                        cuerpoFuncion.forEach(x -> vincula(x));
                        if(tipoFuncion!= null)vincula(declaracionFun.getRetorno());
                        tabla.cierraBloque();
                        break;

                    case IF:
                        InstIf instIf = (InstIf) sentencia;
                        vincula(instIf.getCondicion());
                        tabla.abreBloque();
                        instIf.getCuerpoIf().forEach(x->vincula(x));
                        tabla.cierraBloque();
                        if(instIf.getCuerpoElse() != null) {
                            tabla.abreBloque();
                            instIf.getCuerpoElse().forEach(x->vincula(x));
                            tabla.cierraBloque();
                        }
                        break;
                    case STRUCT:
                        InstStruct instStruct = (InstStruct) sentencia;
                        tabla.insertaId(((Iden) instStruct.getIdentificador()).getNombre(), instStruct);
                        tabla.abreBloque();
                        instStruct.getDeclaraciones().forEach(x->vincula(x));
                        tabla.cierraBloque();
                        break;
                    case SWITCH:
                        InstSwitch instSwitch = (InstSwitch) sentencia;

                        Sentencia referenciaVariableSwitch = tabla.getSentenciaDeclaracion(((Iden)instSwitch.getCondicion()).getNombre());
                        vincula((Iden)instSwitch.getCondicion());
                        if(referenciaVariableSwitch == null) {
                            GestionErrores.errorSemantico("La variable del switch no ha sido declarada. Nombre: " +
                                            ((Iden)instSwitch.getCondicion()).getNombre(),sentencia.getFila(),sentencia.getColumna());
                        }else {
                            instSwitch.setReferenciaDeclaraciónVariable(referenciaVariableSwitch);

                            List<Case> casos = instSwitch.getCases();
                            for(Case caso : casos) {
                                tabla.abreBloque();
                                caso.getCuerpoCase().forEach(x->vincula(x));
                                tabla.cierraBloque();
                            }
                        }
                        break;
                    case WHILE:
                        InstWhile instWhile = (InstWhile) sentencia;
                        vincula(instWhile.getCondicion());
                        tabla.abreBloque();
                        instWhile.getCuerpo().forEach(x-> vincula(x));
                        tabla.cierraBloque();
                        break;
                    default:
                        break;
                }
                break;
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
                            llamada.setReferenciaDeclaracion(referenciaFuncion);
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
                        //Declarando
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
                        break;
                    case NOT:
                        Not expNot = (Not) expresion;
                        vincula(expNot.getOpnd1());
                        break;
                    case PUNTO:
                        AccederStruct expAccederStruct= (AccederStruct) expresion;
                        if (expAccederStruct.getStruct() instanceof Iden && expAccederStruct.getCampo() instanceof  Iden){
                            Iden struct = (Iden) expAccederStruct.getStruct();
                            Sentencia declaracionInstanciaStruct = tabla.getSentenciaDeclaracion(struct.getNombre());
                            if (declaracionInstanciaStruct == null) {
                                GestionErrores.errorSemantico("Llamada a struct " +
                                        struct.getNombre() +
                                        " no existente.",sentencia.getFila(),sentencia.getColumna());
                            } else if (declaracionInstanciaStruct instanceof InstDecl &&
                                    ((InstDecl) declaracionInstanciaStruct).getTipo().tipoTipos() == EnumeradoTipo.STRUCT){
                                Sentencia declaracionStruct = tabla.getSentenciaDeclaracion(
                                                ((Iden)((TipoStruct)((InstDecl) declaracionInstanciaStruct).getTipo().getTipoBase()).getNombre()).getNombre());
                                expAccederStruct.setDeclaracion(declaracionStruct);
                                String nombreCampo = ((Iden) expAccederStruct.getCampo()).getNombre();
                                if (!((InstStruct) declaracionStruct).getCampos().containsKey(nombreCampo)){
                                    GestionErrores.errorSemantico("Se ha llamado al campo " + nombreCampo + " no existente en" +
                                                    " el struct " +  struct.getNombre() + "."
                                            ,sentencia.getFila(),sentencia.getColumna());
                                }
                                expAccederStruct.setTipoReferencia(((InstStruct) declaracionStruct).getCampos().get(nombreCampo));
                            } else {
                                System.out.print(declaracionInstanciaStruct);
                                GestionErrores.errorSemantico("Se ha usado terminología para llamar a un struct con un identificador: " +
                                        struct.getNombre() +
                                        " que no ha sido asignado a un struct, si no a " + struct.getTipoVariable() + "."
                                        ,sentencia.getFila(),sentencia.getColumna());
                            }
                        }

                    default:
                        break;
                }
                break;

            case TIPO:
                Tipo tipo = (Tipo) sentencia;
                switch(tipo.tipoTipos()) {
                    case STRUCT:
                        TipoStruct tipoStruct = ((TipoStruct) ((TipoArray) tipo).getTipoBase());
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
                        if(tipoArray.getDimShape() != null)
                            tipoArray.getDimShape().forEach(x -> vincula(x));

                        break;
                    default:
                        break;
                }
                break;

        }
    }
}
