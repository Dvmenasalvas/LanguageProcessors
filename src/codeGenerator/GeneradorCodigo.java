package codeGenerator;

import ast.E.*;
import ast.I.*;
import ast.T.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class GeneradorCodigo {
    private File ficheroSalida;
    private String inicioPath = "src/codeGenerator/InitialCode.wat";

    private List<Bloque> bloques = new ArrayList<>();
    private Bloque bloqueActual;

    private int proximaDireccion;
    private static int ambitoActual = 0;
    private int maxPila = 0;
    private int maxAmbito = 0;

    private List<String> codigoGenerado = new ArrayList<>();
    private List<I> programa;

    public GeneradorCodigo(List<I> programa, String ficheroEntrada) {
        this.programa = programa;
        String fileName = ficheroEntrada;
        int lastIndex = fileName.lastIndexOf("/");
        String newFilename = "codeGenerated/CodigoMaquina.wat";
        ficheroSalida = new File(newFilename);
    }

    public void generaCodigo() {
        //Asignamos direcciones a las declaraciones
        generaDireccionesPrograma();
        int i = 0;
        for(Bloque bloque : bloques) {
            System.out.println("----------" + "BLOQUE " + i + "----------");
            System.out.println(bloque.toString());
            i++;
        }

        //Generamos código
        programa.forEach(instruccion -> codeI(instruccion));
        //int tamPila = tamanoPilaEvaluacion(1);

        //Escribimos código en archivo de salida
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(ficheroSalida));
            File iniCodeFile = new File(inicioPath);
            FileInputStream inCodeIni = new FileInputStream(iniCodeFile);
            int c;
            while ((c = inCodeIni.read()) != -1)
                writer.write(c);

            inCodeIni.close();

            i = 0;
            for(String instruccion : codigoGenerado) {
                writer.write(instruccion + '\n');
                i++;
            }

            writer.write(")\n");
            writer.write(")");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al generar el archivo de salida.");
            e.printStackTrace();
        }
    }

    //Generadores de código
    void codeE (E expresion) {
        switch (expresion.tipoExpresion()) {
            case AND, DISTINTO, DIV, IGUALIGUAL, MAYOR, MAYORIGUAL, MENOR, MENORIGUAL, MOD, MUL, OR, ELEV, RESTA, SUMA -> {
                EBin expresionBinaria = (EBin) expresion;
                codeE(expresionBinaria.getOpnd1());
                codeE(expresionBinaria.getOpnd2());
                codigoGenerado.add(expresionBinaria.wasm_opcode());
            }
            case ENT ->
                codigoGenerado.add("i32.const " + ((Ent) expresion).valor());
            case FALSO ->
                codigoGenerado.add("i32.const 0");
            case VERDADERO ->
                codigoGenerado.add("i32.const 1");
            case IDEN -> {
                codeD((Iden) expresion);
                codigoGenerado.add("i32.load");
            }
        }
    }

    void codeD (Iden iden) {
        //Array
        if (iden.getDimShape() != null) {
            codeD(new Iden(iden.getNombre(), null, iden.getFila(), iden.getColumna()));
            if (iden.getDimShape().size() == 1) {
                if (iden.getTipoVariable() != null &&
                    (((TipoArray)iden.getTipoVariable()).getTipoBase().tipoTipos() == EnumeradoTipo.BOOLEAN ||
                    ((TipoArray)iden.getTipoVariable()).getTipoBase().tipoTipos() == EnumeradoTipo.INT)
                || !bloques.get(ambitoActual).isStruct(iden.valor())) {
                    codigoGenerado.add("i32.const 4");
                //Struct
                } else {
                    codigoGenerado.add(
                            "i32.const " +
                            (bloques.get(ambitoActual)
                                    .getTamanoTipo(((Iden)((TipoStruct)((TipoArray)iden.getTipoVariable()).getTipoBase()).getNombre()).valor()))*4);
                }
                codeE(iden.getDimShape().get(0));
                codigoGenerado.add("i32.mul");
                codigoGenerado.add("i32.add");
            }
        //Identificador (entero o boolean) o struct
        } else {
            codigoGenerado.add("i32.const " + bloques.get(ambitoActual).getDireccionIdentificador(iden.valor()));
            codigoGenerado.add("i32.const " + bloques.get(ambitoActual).getInicioMemoriaMarco());
            codigoGenerado.add("i32.add");
        }
    }
    void asignacion(E identificador, List<E> expresiones) {
        if (identificador instanceof AccederStruct){
            AccederStruct accederStruct = (AccederStruct) identificador;
            String nombreStruct  = ((Iden) accederStruct.getStruct()).valor();
            String campoStruct = ((Iden) accederStruct.getCampo()).valor();
            String tipoStruct = bloques.get(ambitoActual).getVarTipo(nombreStruct);
            codigoGenerado.add("i32.const " + bloques.get(ambitoActual).getDireccionCampoStruct(tipoStruct, nombreStruct,campoStruct));
        }
        else {
            codeD((Iden) identificador);
        }
        //Codigo inicio contexto (d)
        if (expresiones.size() == 1) {
            codeE(expresiones.get(0));
        }
        codigoGenerado.add("i32.store");
    }

    void codeI (I instruccion) {
        switch (instruccion.tipoInstruccion()){
            case DECL:
                InstDecl instDecl = (InstDecl) instruccion;
                if (instDecl.getExpresiones() != null && instDecl.getExpresiones().size() > 0) {
                    if(instDecl.getExpresiones().size() == 1){
                        asignacion(instDecl.getIdentificador(), instDecl.getExpresiones());
                    } else {
                        Iden iden = (Iden) instDecl.getIdentificador();
                        for (int i = 0; i < instDecl.getExpresiones().size(); i++){
                            asignacion(new Iden(iden.valor(), List.of(new Ent(((Integer)i).toString(), instDecl.getFila(), instDecl.getColumna())),
                                            instDecl.getFila(), instDecl.getColumna()),
                                    List.of(instDecl.getExpresiones().get(i)));
                        }
                    }
                }
                break;

            case ASIG:
                InstAsignacion instAsignacion = (InstAsignacion) instruccion;
                if(instAsignacion.getValor().size() == 1){
                    asignacion(instAsignacion.getIdentificador(), instAsignacion.getValor());
                } else {
                    Iden iden = (Iden) instAsignacion.getIdentificador();
                    for (int i = 0; i < instAsignacion.getValor().size(); i++){
                        asignacion(new Iden(iden.valor(), List.of(new Ent(((Integer)i).toString(), instAsignacion.getFila(), instAsignacion.getColumna())),
                                        instAsignacion.getFila(), instAsignacion.getColumna()),
                                List.of(instAsignacion.getValor().get(i)));
                    }
                }
                break;

            case IF:
                maxAmbito++;
                ambitoActual = maxAmbito;
                InstIf instIf = (InstIf) instruccion;
                codeE(instIf.getCondicion());
                codigoGenerado.add("if");
                for (I ins: instIf.getCuerpoIf()){
                    codeI(ins);
                }
                ambitoActual = bloques.get(ambitoActual).getBloquePadre().getPosicionBloque();

                if(instIf.getCuerpoElse() != null) {
                    maxAmbito++;
                    ambitoActual = maxAmbito;
                    codigoGenerado.add("else");
                    for (I ins : instIf.getCuerpoElse()) {
                        codeI(ins);
                    }
                    ambitoActual = bloques.get(ambitoActual).getBloquePadre().getPosicionBloque();
                }


                codigoGenerado.add("end");
                break;

            case PRINT:
                InstPrint instPrint = (InstPrint) instruccion;
                codeE(instPrint.getExpresion());
                codigoGenerado.add("call $print");
                break;
            case WHILE:
                maxAmbito++;
                ambitoActual = maxAmbito;
                InstWhile instWhile = (InstWhile) instruccion;
                codigoGenerado.add("block");
                codigoGenerado.add("loop");
                codeE(instWhile.getCondicion());
                codigoGenerado.add("i32.eqz");
                codigoGenerado.add("br_if 1");
                instWhile.getCuerpo().forEach(this::codeI);
                codigoGenerado.add("br 0");
                codigoGenerado.add("end");
                codigoGenerado.add("end");
                ambitoActual = bloques.get(ambitoActual).getBloquePadre().getPosicionBloque();
                break;
            case SWITCH:
                InstSwitch instSwitch = (InstSwitch) instruccion;
                instSwitch.getCases().forEach(caso -> codigoGenerado.add("block"));
                codigoGenerado.add("block");
                codeE(instSwitch.getCondicion());
                StringBuilder table = new StringBuilder("br_table");
                for (int i = 0; i < instSwitch.getCases().size(); i++) {
                    table.append(" " + (i));
                }
                codigoGenerado.add(table.toString());
                for(int i = 0; i < instSwitch.getCases().size(); i++){
                   codigoGenerado.add("end");
                   codeI(instSwitch.getCases().get(i));
                   codigoGenerado.add("br " + (instSwitch.getCases().size() - i - 1));
                }
                codigoGenerado.add("end");
                break;
            case CASE:
                ((InstCase) instruccion).getCuerpoCase().forEach(this::codeI);
                break;
        }
    }


    //Generación de direcciones
    private void generaDireccionesPrograma() {
        crearNuevoBloque(true);
        for(I instruccion : this.programa) {
            generaDireccionesInstruccion(instruccion);
        }
        guardarBloqueActual();
    }

    private void generaDireccionesCuerpo(List<I> instrucciones) {
        crearNuevoBloque(false);
        for(I instruccion : instrucciones) {
            generaDireccionesInstruccion(instruccion);
        }
        guardarBloqueActual();
    }

    private void generaDireccionesInstruccion(I instruccion) {
        switch(instruccion.tipoInstruccion()) {
            case IF:
                InstIf instruccionIf = (InstIf) instruccion;
                generaDireccionesCuerpo(instruccionIf.getCuerpoIf());
                if(instruccionIf.getCuerpoElse() != null) generaDireccionesCuerpo(instruccionIf.getCuerpoElse());
                break;

            case WHILE:
                InstWhile instruccionWhile = (InstWhile) instruccion;
                generaDireccionesCuerpo(instruccionWhile.getCuerpo());
                break;

            case SWITCH:
                InstSwitch instruccionSwitch = (InstSwitch) instruccion;

                List<I> cuerpoSwitch = new ArrayList<I>();
                for(InstCase ccase : instruccionSwitch.getCases()) {
                    cuerpoSwitch.addAll(ccase.getCuerpoCase());
                }
                generaDireccionesCuerpo(cuerpoSwitch);
                break;

            case DECL:
                InstDecl instruccionDeclaracion = (InstDecl) instruccion;
                String idenDeclaracion = ((Iden) instruccionDeclaracion.getIdentificador()).getNombre();

                switch(instruccionDeclaracion.getTipo().tipoTipos()) {
                    case INT:
                    case BOOLEAN:
                        insertaIdentificadorBloqueActual(idenDeclaracion, 1);
                        break;
                    case STRUCT:
                        String nombreStruct = ((Iden)((TipoStruct) instruccionDeclaracion.getTipo().getTipoBase()).getNombre()).getNombre();
                        int tamano = bloqueActual.getTamanoTipo(nombreStruct);
                        bloqueActual.putVarTipo(idenDeclaracion, nombreStruct);
                        insertaIdentificadorBloqueActual(idenDeclaracion, tamano);
                        break;
                    case ARRAY:
                        int tamArray = obtenerInformacionArray(instruccionDeclaracion);
                        int tamTipoBase = 1;
                        List<Integer> dimensionesArray =  new ArrayList<Integer>();;


                        Tipo tipoBase = ((TipoArray) instruccionDeclaracion.getTipo()).getTipoBase();
                        if(tipoBase instanceof TipoStruct) {
                            tamTipoBase = this.bloqueActual.getTamanoTipo(((Iden) ((TipoStruct) tipoBase).getNombre()).getNombre());
                            tamArray *= tamArray;
                        }

                        TipoArray t = (TipoArray) instruccionDeclaracion.getTipo();
                        int dimension;
                        for (E exp : t.getDimShape()) { //Mientras siga habiendo dimensiones
                            dimension = obtenerDimensionArray(exp);
                            dimensionesArray.add(dimension);
                        }



                        insertaIdentificadorBloqueActual(idenDeclaracion, tamArray);
                        bloqueActual.insertaTamanoTipo(idenDeclaracion, tamTipoBase);
                        bloqueActual.insertaDimensionesArray(idenDeclaracion, dimensionesArray);
                        break;
                    default:
                        break;
                }

                break;

            case STRUCT:
                InstStruct instruccionStruct = (InstStruct) instruccion;
                String nombreStruct = ((Iden) instruccionStruct.getIdentificador()).getNombre();
                Map<String, Integer> tamCamposStruct = tamanoCamposStruct(instruccionStruct);
                int tamStruct = tamCamposStruct.values().stream().mapToInt(tam -> tam).sum();
                bloqueActual.insertaTamanoTipo(nombreStruct, tamStruct);
                bloqueActual.insertaCamposStruct(nombreStruct, tamCamposStruct);
                break;

                /*
            case DECLFUN:
                InstDeclFun instruccionDeclFun = (InstDeclFun) instruccion;
                instruccionDeclFun.setProfundidadAnidamiento(bloqueActual.getProfundidadAnidamiento());

                //Abrimos ambito
                crearNuevoBloque(true);

                //Asignamos direccion a cada parametro
                for(TipoArgumento arg: instruccionDeclFun.getArgumentos()) {
                    //Transformamos cada argumento en una declaracion (REVISAR)
                    InstDecl argumentoDeclaracion = new InstDecl((TipoArray) arg.getTipo(), arg.getArgumento(),
                            null, false, ((Iden)arg.getArgumento()).getFila(),((Iden)arg.getArgumento()).getColumna());
                    generaDireccionesInstruccion(argumentoDeclaracion);
                }

                //Asignamos direccion a cada instruccion del cuerpo (no llamamos a
                //la funcion generaDireccionesCuerpo porque generariamos otro ambito)
                for(I instr : instruccionDeclFun.getCuerpo()) {
                    generaDireccionesInstruccion(instr);
                }

                //Cerramos ambito
                guardarBloqueActual();

                break;
                 */
            default:
                break;
        }
    }

    //Funciones auxiliares para bloques
    private void crearNuevoBloque(boolean ambitoFuncion) {
        Bloque bloque = new Bloque(bloqueActual, bloques.size(), ambitoFuncion);
        bloques.add(bloque);
        bloqueActual = bloque;
    }

    private void guardarBloqueActual() {
        proximaDireccion = proximaDireccion - bloqueActual.getTamanoBloque();
        bloqueActual = bloqueActual.getBloquePadre();
    }

    private void insertaIdentificadorBloqueActual(String nombreIdentificador, int tamanoIdentificador) {
        bloqueActual.insertaIdentificador(nombreIdentificador, tamanoIdentificador);
        proximaDireccion += tamanoIdentificador;
    }

    private int obtenerDimensionArray(E expresion) {
        int dimension = 0;
        switch(expresion.tipoExpresion()) {
            case IDEN: //Case base 1
                Iden iden = (Iden) expresion;
                InstDecl referencia = (InstDecl) iden.getReferencia();
                if(referencia.isConstante()) return obtenerDimensionArray(referencia.getExpresiones().get(0));
                else {
                    System.err.println("Error de ejecucion: La dimension del array debe ser un valor constante.");
                    return 0;
                }
            case ENT: //Caso base 2
                return Integer.parseInt(((Ent) expresion).toString());
            //Casos recursivos
            case SUMA:
                E suma = ((Suma) expresion);
                return obtenerDimensionArray(suma.getOpnd1()) + obtenerDimensionArray(suma.getOpnd2());
            case RESTA:
                E resta = ((Resta) expresion);
                return obtenerDimensionArray(resta.getOpnd1()) - obtenerDimensionArray(resta.getOpnd2());
            case MUL:
                E mul = ((Mul) expresion);
                return obtenerDimensionArray(mul.getOpnd1()) * obtenerDimensionArray(mul.getOpnd2());
            case DIV:
                E div = ((Div) expresion);
                return obtenerDimensionArray(div.getOpnd1()) / obtenerDimensionArray(div.getOpnd2());
            default:
                System.err.println("Error de ejecucion: Operacion no soportada para la dimension de un array");
                break;
        }

        return dimension;
    }

    private int obtenerInformacionArray(InstDecl declaracionArray) {
        int tamArray = 0;
        int tamanoTipoBase;
        List<Integer> dimensiones;

        //Si la declaracion pasada no es de tipo array => ERROR
        if(declaracionArray.getTipo().tipoTipos() == EnumeradoTipo.ARRAY){
            dimensiones = new ArrayList<Integer>();
            tamArray = 1; //El tamano total sera el multiplicatorio de sus dimensiones por el tipo base
            tamanoTipoBase = 1;

            TipoArray t = (TipoArray) declaracionArray.getTipo();
            int dimension;
            for (E exp : t.getDimShape()) { //Mientras siga habiendo dimensiones
                dimension = obtenerDimensionArray(exp);
                dimensiones.add(dimension);
                tamArray *= dimension;
            }


            Tipo tipoBase = ((TipoArray) declaracionArray.getTipo()).getTipoBase();
            if(tipoBase instanceof TipoStruct) {
                tamanoTipoBase = this.bloqueActual.getTamanoTipo(((Iden) ((TipoStruct) tipoBase).getNombre()).getNombre());
            }
        }
        return tamArray;
    }

    private Map<String,Integer> tamanoCamposStruct(InstStruct struct) {
        Map<String, Integer> tamCamposStruct = new HashMap<>();

        for(I instruccion : struct.getDeclaraciones()) {
            InstDecl declaracion = (InstDecl) instruccion;
            String idenDeclaracion = ((Iden) declaracion.getIdentificador()).getNombre();
            int tamCampo = 0;

            switch(declaracion.getTipo().tipoTipos()) {
                case INT:
                case BOOLEAN:
                    tamCampo = 1;
                    break;
                case STRUCT:
                    TipoStruct tipoStruct = (TipoStruct) declaracion.getTipo().getTipoBase();
                    tamCampo = bloqueActual.getTamanoTipo(((Iden)tipoStruct.getNombre()).getNombre());
                    break;
                case ARRAY:
                    /*
                    int aux1 = 0, aux2 = 0;
                    List<Integer> aux = new ArrayList<>();
                    aux1 = obtenerInformacionArray(declaracion);
                    tamCampo = aux1;
                    List<Integer> dimensionesArray = aux;
                    this.bloqueActual.insertaDimensionesArray(idenDeclaracion, dimensionesArray);
                    int tamanoBaseArray = aux2;
                    this.bloqueActual.insertaTamanoTipo(idenDeclaracion, tamanoBaseArray);
                     */
                    break;
                default:
                    break;
            }
            tamCamposStruct.put(idenDeclaracion, tamCampo);
        }
        return tamCamposStruct;
    }
}

