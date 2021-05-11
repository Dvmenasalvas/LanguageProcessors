package ast.T;

import ast.Sentencia;
import ast.E.*;

public class TipoStruct extends Tipo{
    private E nombre;
    private Sentencia referenciaDeclaracion;

    public TipoStruct(E lexema, int fila, int columna) {
        super(fila, columna);
        this.nombre = lexema;
    }

    @Override
    public EnumeradoTipo tipoTipos() {
        return EnumeradoTipo.STRUCT;
    }

    public String toString() { return nombre.toString(); }

    public E getNombre() {
        return nombre;
    }

    public Sentencia getReferenciaDeclaracion() {
        return referenciaDeclaracion;
    }

    public void setReferenciaDeclaracion(Sentencia referenciaDeclaracion) {
        this.referenciaDeclaracion = referenciaDeclaracion;
    }
}
