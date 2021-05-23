package codeGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bloque {
    private Map<String,Integer> direccionIdentificadores = new HashMap<>();
    private Map<String,Integer> tamanoTipos = new HashMap<>();
    private Map<String,String> varTipo = new HashMap<>();
    private Map<String, List<Integer>> dimensionesArrays = new HashMap<>();
    //Nombre struct -> nombre propiedad -> posición
    private Map<String, Map<String, Integer>> camposStructs = new HashMap<>();
    private int tamanoBloque;
    private int posicionBloque;
    private Bloque bloquePadre;
    private int proximaDireccion;
    private int profundidadAnidamiento;
    boolean nuevoMarcoActivacion;
    int inicioMemoriaMarco;

    public Bloque(Bloque bloquePadre, int posicionBloque, boolean nuevoMarcoActivacion) {
        this.tamanoBloque = 0;
        this.posicionBloque = posicionBloque;
        this.bloquePadre = bloquePadre;
        this.nuevoMarcoActivacion = nuevoMarcoActivacion;

        if(bloquePadre == null) { //Primer bloque
            this.proximaDireccion = 0;
            this.profundidadAnidamiento = 0;
        } else {
            if (nuevoMarcoActivacion) {
                inicioMemoriaMarco = bloquePadre.getInicioMemoriaMarco() + bloquePadre.getTamanoBloque();
                this.proximaDireccion = 5;
                this.profundidadAnidamiento = bloquePadre.getProfundidadAnidamiento() + 1;
            } else {
                inicioMemoriaMarco = bloquePadre.getInicioMemoriaMarco();
                this.proximaDireccion = bloquePadre.getProximaDireccion();
                this.profundidadAnidamiento = bloquePadre.getProfundidadAnidamiento();
            }
        }
    }

    public int getInicioMemoriaMarco() {
        return inicioMemoriaMarco;
    }

    public int getTamanoBloque() {
        return tamanoBloque;
    }

    public int getPosicionBloque() {
        return posicionBloque;
    }
    public void putVarTipo(String var, String struct) {
        varTipo.put(var,struct);
    }
    public String getVarTipo(String var) {
        if(varTipo.containsKey(var)) return varTipo.get(var);
        else if(!nuevoMarcoActivacion && bloquePadre != null) return bloquePadre.getVarTipo(var);
        else {
            System.out.println("Se ha accedido a un struct no declarado.");
            return "";
        }
    }

    public Bloque getBloquePadre() {
        return bloquePadre;
    }

    public int getProximaDireccion() {
        return proximaDireccion;
    }

    public int getProfundidadAnidamiento() {
        return profundidadAnidamiento;
    }

    public boolean estaIdentificador(String identificador) {
        if(direccionIdentificadores.containsKey(identificador)) return true;
        else if(!nuevoMarcoActivacion) return bloquePadre.estaIdentificador(identificador);
        else return false;
    }

    public int getDireccionIdentificador(String iden) {
        if(direccionIdentificadores.containsKey(iden)) return direccionIdentificadores.get(iden) * 4;
        else if(bloquePadre ==null) {
            System.out.println("El identificador " + iden + " no está guardado.");
            return -1;
        }
        else return bloquePadre.getDireccionIdentificador(iden);
    }

    public void insertaIdentificador(String identificador,int tamanoIdentificador) {
        direccionIdentificadores.put(identificador, proximaDireccion);
        proximaDireccion += tamanoIdentificador;

        if(!nuevoMarcoActivacion) tamanoBloque += tamanoIdentificador;
    }

    public int getTamanoTipo(String nombreTipo) {
        if(tamanoTipos.containsKey(nombreTipo)) return tamanoTipos.get(nombreTipo);
        else return bloquePadre.getTamanoTipo(nombreTipo);
    }

    public void insertaTamanoTipo(String nombreTipo, int tamanoTipo) {
        tamanoTipos.put(nombreTipo, tamanoTipo);
    }

    public List<Integer> getDimensionesArray(String nombreArray) {
        if(dimensionesArrays.containsKey(nombreArray)) return dimensionesArrays.get(nombreArray);
        else return bloquePadre.getDimensionesArray(nombreArray);
    }

    public void insertaDimensionesArray(String nombreArray, List<Integer> dimensiones) {
        dimensionesArrays.put(nombreArray, dimensiones);
    }

    public int getDireccionElementoArray(String nombreArray, List<Integer> indices) throws Exception{
        List<Integer> dimensiones = getDimensionesArray(nombreArray);
        if(indices.size() != dimensiones.size()) return -1;
        else {
            int tamanoTipoBase = getTamanoTipo(nombreArray);
            int direccion = getDireccionIdentificador(nombreArray);
            for(int i = 0; i < indices.size(); i++) {
                if(indices.get(i) >= dimensiones.get(i))
                    throw new Exception("Error de ejecucion al acceder al vector " + nombreArray +
                            ". Indice (" + indices.get(i) + ") fuera de rango (" + dimensiones.get(i) + ")");
                else {
                    if(i == indices.size() - 1) direccion += indices.get(i) * tamanoTipoBase;
                    else direccion += dimensiones.get(i) * indices.get(i) * tamanoTipoBase;
                }
            }
            return direccion;
        }
    }

    public int getDireccionRelativaCampoStruct(String tipoStruct, String nombreCampo) {
        if(camposStructs.containsKey(tipoStruct)) return camposStructs.get(tipoStruct).get(nombreCampo);
        else if(bloquePadre ==null) {
            System.out.println("El struct " + tipoStruct + " no tiene guarda la dirección del campo " + nombreCampo);
            return -1;
        }
        else return bloquePadre.getDireccionRelativaCampoStruct(tipoStruct, nombreCampo);
    }
    public boolean isStruct(String var){
        return varTipo.containsKey(var);
    }

    public int getDireccionCampoStruct(String tipoStruct, String nombreStruct, String nombreCampo) {
        return getDireccionIdentificador(nombreStruct) + getDireccionRelativaCampoStruct(tipoStruct, nombreCampo) * 4;
    }

    public void insertaCamposStruct(String nombreStruct, Map<String, Integer> tamanoCamposStruct) {
        int direccionRelativa = 0;
        Map<String, Integer> direccionesCamposStruct = new HashMap<>();

        for(Map.Entry<String, Integer> campoStruct : tamanoCamposStruct.entrySet()) {
            direccionesCamposStruct.put(campoStruct.getKey(), direccionRelativa);
            direccionRelativa += campoStruct.getValue();
        }

        camposStructs.put(nombreStruct, direccionesCamposStruct);
    }

    public String toString() {
        String aux = "";

        aux += "Direccion identificadores: \n";
        for(Map.Entry<String, Integer> entry : direccionIdentificadores.entrySet()) {
            aux += "\t(" + entry.getValue() + ") " + entry.getKey() + "\n";
        }

        aux += "Tamano de los tipos: \n";
        for(Map.Entry<String, Integer> entry : tamanoTipos.entrySet()) {
            aux += "\t" + entry.getKey() + " " + entry.getValue() + "\n";
        }

        aux += "Tamano Bloque: " + tamanoBloque + "\n";

        aux += "Posicion Bloque: " + posicionBloque + "\n";
        if(bloquePadre != null) aux += "Bloque Padre: " + bloquePadre.getPosicionBloque() + "\n";
        aux += "Proxima Direccion: " + proximaDireccion + "\n";
        aux += "Profundidad Anidamiento: " + profundidadAnidamiento + "\n";
        aux += "Marco Activacion: " + nuevoMarcoActivacion + "\n";

        return aux;
    }

}
