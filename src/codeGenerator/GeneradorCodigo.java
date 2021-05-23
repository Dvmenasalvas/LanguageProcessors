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
        switch (expresion.tipoExpresion()){
            case AND:
            case DISTINTO:
            case DIV:
            case IGUALIGUAL:
            case MAYOR:
            case MAYORIGUAL:
            case MENOR:
            case MENORIGUAL:
            case MOD:
            case MUL:
            case OR:
            case ELEV:
            case RESTA:
            case SUMA:
                EBin expresionBinaria = (EBin) expresion;
                codeE(expresionBinaria.getOpnd1());
                codeE(expresionBinaria.getOpnd2());
                codigoGenerado.add(expresionBinaria.wasm_opcode());
                break;
            case ENT:
                codigoGenerado.add("i32.const " + ((Ent) expresion).valor());
                break;
            case FALSO:
                codigoGenerado.add("i32.const 0");
                break;
            case VERDADERO:
                codigoGenerado.add("i32.const 1");
                break;
            case IDEN:
                codeD((Iden) expresion);
                codigoGenerado.add("i32.load");
                break;

        }
    }

    void codeD (Iden iden) {
        //Array
        if (iden.getDimShape() != null) {
            codeD(new Iden(iden.getNombre(), null, iden.getFila(), iden.getColumna()));
            if (iden.getDimShape().size() == 1) {
                if (iden.getTipoVariable().tipoTipos() == EnumeradoTipo.BOOLEAN ||
                    iden.getTipoVariable().tipoTipos() == EnumeradoTipo.INT) {
                    codigoGenerado.add("i32.const 1");

                    //Struct
                } else {
                    codigoGenerado.add("i32.const " + bloques.get(ambitoActual).getTamanoTipo(iden.valor()));
                }
                codeE(iden.getDimShape().get(0));
                codigoGenerado.add("i32.mul");
                codigoGenerado.add("i32.add");
            }
        }
        //Struct
        else if(iden.getReferencia() != null){
            if (iden.getTipoVariable().tipoTipos() == EnumeradoTipo.STRUCT){
                codigoGenerado.add("i32.const " + bloques.get(ambitoActual).getDireccionIdentificador(iden.valor()));
                codigoGenerado.add("i32.const " + bloques.get(ambitoActual).getInicioMemoriaMarco());
                codigoGenerado.add("i32.add");
        }



        //Identificador (entero o boolean)
        } else {
            codigoGenerado.add("i32.const " + bloques.get(ambitoActual).getDireccionIdentificador(iden.valor()));
            codigoGenerado.add("i32.const " + bloques.get(ambitoActual).getInicioMemoriaMarco());
            codigoGenerado.add("i32.add");
        }
    }

    void codeI (I instruccion) {
        switch (instruccion.tipoInstruccion()){
            case DECL:
                InstDecl instDecl = (InstDecl) instruccion;
                if (instDecl.getExpresiones() != null && instDecl.getExpresiones().size() > 0) {
                    codeD((Iden) instDecl.getIdentificador());
                    //Codigo inicio contexto (d)
                    //codigoGenerado.add("i32.const 0"); // Provisional
                    //codigoGenerado.add("i32.add");
                    if (instDecl.getExpresiones().size() == 1) {
                        codeE(instDecl.getExpresiones().get(0));
                    }
                    codigoGenerado.add("i32.store");
                }
                break;

            case ASIG:
                InstAsignacion instAsignacion = (InstAsignacion) instruccion;

                codeD((Iden) instAsignacion.getIdentificador());
                //Codigo inicio contexto (d)
                //codigoGenerado.add("i32.const 0"); // Provisional
                //codigoGenerado.add("i32.add");
                if(instAsignacion.getValor().size() == 1) {
                    codeE(instAsignacion.getValor().get(0));
                }
                codigoGenerado.add("i32.store");
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


    //############### -1- GENERACION DE DIRECCIONES #################
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
                        insertaIdentificadorBloqueActual(idenDeclaracion, tamano);
                        break;
                    case ARRAY:
                        int aux1 = 0, aux2 = 0;
                        List<Integer> aux = new ArrayList<Integer>();
                        obtenerInformacionArray(instruccionDeclaracion, aux1, aux2, aux);
                        int tamArray = aux1;
                        int tamTipoBase = aux2;
                        List<Integer> dimensionesArray = aux;

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
                int tamStruct = 0;
                Map<String, Integer> tamCamposStruct = new HashMap<>();
                obtenerInformacionStruct(instruccionStruct, tamStruct, tamCamposStruct);
                bloqueActual.insertaTamanoTipo(nombreStruct, tamStruct);
                bloqueActual.insertaCamposStruct(nombreStruct, tamCamposStruct);
                break;

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

            default:
                break;
        }
    }

    //####################### -3- Funciones auxiliares para bloques ##############################
    private Bloque getBloqueNivelActual() {
        return bloques.get(ambitoActual);
    }

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






    private void obtenerInformacionArray(InstDecl declaracionArray, int tamanoArray, int tamanoTipoBase,
                                    List<Integer> dimensiones) {
        //Si la declaracion pasada no es de tipo array => ERROR
        if(declaracionArray.getTipo().tipoTipos() == EnumeradoTipo.ARRAY){
            dimensiones = new ArrayList<Integer>();
            tamanoArray = 1; //El tamano total sera el multiplicatorio de sus dimensiones por el tipo base
            tamanoTipoBase = 1;

            TipoArray t = (TipoArray) declaracionArray.getTipo();
            int dimension;
            for (E exp : t.getDimShape()) { //Mientras siga habiendo dimensiones
                dimension = obtenerDimensionArray(exp);
                dimensiones.add(dimension);
                tamanoArray *= dimension;
            }


            Tipo tipoBase = ((TipoArray) declaracionArray.getTipo()).getTipoBase();
            if(tipoBase instanceof TipoStruct) {
                tamanoTipoBase = this.bloqueActual.getTamanoTipo(((Iden) ((TipoStruct) tipoBase).getNombre()).getNombre());
                ;
                tamanoArray *= tamanoArray;
            }
        }
    }

    private void obtenerInformacionStruct(InstStruct struct, int tamStruct, Map<String, Integer> tamCamposStruct) {
        tamStruct = 0;
        tamCamposStruct = new HashMap<>();

        for(I instruccion : struct.getDeclaraciones()) { //Para cada declaracion del struct, sumamos su tamano
            InstDecl declaracion = (InstDecl) instruccion;
            String idenDeclaracion = ((Iden) declaracion.getIdentificador()).getNombre();
            int tamCampo = 0;

            switch(declaracion.getTipo().tipoTipos()) {
                case INT:
                case BOOLEAN:
                    //Sumamos 1 (lo que ocupan int y boolean)
                    tamCampo = 1;
                    break;
                case STRUCT:
                    //Sumamos el tamano del tipo struct, que lo tendremos almacenado en un bloque previo
                    TipoStruct tipoStruct = (TipoStruct) declaracion.getTipo().getTipoBase();
                    tamCampo = bloqueActual.getTamanoTipo(((Iden)tipoStruct.getNombre()).getNombre());
                    break;
                case ARRAY:
                    //Sumamos el tamano del tipo array y almacenamos sus dimensiones y tamano del tipo base
                    int aux1 = 0, aux2 = 0;
                    List<Integer> aux = new ArrayList<>();
                    obtenerInformacionArray(declaracion, aux1, aux2, aux);
                    tamCampo = aux1;

                    List<Integer> dimensionesArray = aux;
                    this.bloqueActual.insertaDimensionesArray(idenDeclaracion, dimensionesArray); //OJO! POSIBLE ERROR, QUIZAS DEBERIA SER idenStruct + "." + idenDeclaracion
                    //PERO ESO NO NOS SIRVE PARA EL SQUAREBRACKET :/, HAY QUE PENSARLO
                    int tamanoBaseArray = aux2;
                    this.bloqueActual.insertaTamanoTipo(idenDeclaracion, tamanoBaseArray);
                    break;
                default:
                    break;
            }
            tamCamposStruct.put(idenDeclaracion, tamCampo);
            tamStruct += tamCampo;
        }
    }



}
