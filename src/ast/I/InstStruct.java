package ast.I;

import java.util.List;
import ast.E.*;
import ast.T.EnumeradoTipo;
import ast.T.Tipo;

import java.util.*;

public class InstStruct extends I{
    private E nombreTipo;
    private List<I> declaraciones;
    private Map<String, Tipo> campos;

    public InstStruct(E nombreTipo, List<I> declaraciones, int fila, int columna) {
        super(fila, columna);
        this.nombreTipo = nombreTipo;
        this.declaraciones = declaraciones;
        campos = new HashMap<>();
        for (I declaracion : declaraciones){
            campos.put(((Iden)((InstDecl) declaracion).getIdentificador()).getNombre(), ((InstDecl) declaracion).getTipo());
        }
    }

    public Map<String, Tipo> getCampos() {
        return campos;
    }

    public List<I> getDeclaraciones() {
        return declaraciones;
    }

    public E getIdentificador() {
        return nombreTipo;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.STRUCT;
    }

    @Override
    public String toString() {
        String aux = "{{I: Declaracion}" + nombreTipo;
        for(I ins : declaraciones) aux += ins;
        return aux + "}";
    }
}
