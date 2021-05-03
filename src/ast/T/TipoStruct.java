package ast.T;

import ast.E.E;
import ast.Sentencia;

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

    public void setReferenciaDeclaracion(Sentencia referenciaDeclaracion) {
        this.referenciaDeclaracion = referenciaDeclaracion;
    }
}
