package codeGenerator;

import ast.E.E;
import ast.E.Ent;
import ast.E.Iden;
import ast.I.*;
import ast.T.Tipo;
import ast.T.TipoStruct;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeneradorCodigo {
    private File ficheroSalida;
    private List<Bloque> bloques = new ArrayList<>();
    private Bloque bloqueActual;

    private int proximaDireccion;
    private static int ambitoActual = 0;
    private int maxPila = 0;
    private int maxAmbitos = 0;

    private List<InstruccionMaquina> codigoGenerado = new ArrayList<>();
    private List<I> programa;

    public GeneradorCodigo(List<I> programa, String ficheroEntrada) {
        this.programa = programa;
        String fileName = ficheroEntrada;
        int lastIndex = fileName.lastIndexOf("/");
        String newFilename = fileName.substring(0, lastIndex+1) + "CodigoMaquina" + fileName.substring(lastIndex+1);
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
        codigoGenerado.add(new InstruccionMaquina(InstruccionesMaquinaEnumerado.SSP,0,Integer.toString(bloques.get(0).getSsp())));
        codigoGenerado.add(new InstruccionMaquina(InstruccionesMaquinaEnumerado.SEP,0));
        generaCodigoCuerpo(this.programa);
        int tamPila = tamanoPilaEvaluacion(1);
        codigoGenerado.get(1).setArgumento1(Integer.toString(tamPila));
        codigoGenerado.add(new InstruccionMaquina(InstruccionesMaquinaEnumerado.STP,0));

        //Escribimos código en archivo de salida
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(ficheroSalida));
            i = 0;
            for(InstruccionMaquina instruccion : codigoGenerado) {
                writer.write("{" + i + "} " + instruccion.toString());
                i++;
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al generar el archivo de salida.");
            e.printStackTrace();
        }
    }

    StringBuilder codeE (E expresion) {
        StringBuilder out = new StringBuilder();
        switch (expresion.tipoExpresion()){
            case ENT:
                out.append("i32.const").append(((Ent) expresion).valor());

        }

        return out;
    }

    StringBuilder codeD (Iden iden) {
        StringBuilder out = new StringBuilder();


        return out;
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
                for(Case ccase : instruccionSwitch.getCases()) {
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
                        //Por hacer
                        break;
                    default:
                        break;
                }

                break;

            case STRUCT:
                //Por hacer
                break;

            case DECLFUN:
                //Por hacer

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


}
