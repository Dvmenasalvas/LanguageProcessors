package ast.I;

import ast.E.E;
import ast.E.Iden;
import ast.T.EnumeradoTipo;
import ast.T.Tipo;

import java.util.*;

public class InstStruct extends I{
    private E identificador;
    private List<I> declaraciones;
    private Map<String, Tipo> campos;

    public InstStruct(E identificador, List<I> declaraciones, int fila, int columna) {
        super(fila, columna);
        this.identificador = identificador;
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
        return identificador;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.STRUCT;
    }

    @Override
    public String toString() {
        String aux = "{{I: Declaración Struct}" + identificador;
        for(I ins : declaraciones) aux += ins;
        return aux + "}";
    }
}
