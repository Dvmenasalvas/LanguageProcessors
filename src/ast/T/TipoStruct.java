package ast.T;

import ast.E.E;

public class TipoStruct extends Tipo{
    private E nombre;

    @Override
    public String toString() {
        return "TipoStruct{" +
                "nombre=" + nombre +
                '}';
    }

    public TipoStruct(E lexema, int fila, int columna) {
        super(fila, columna);
        this.nombre = lexema;
    }

    @Override
    public EnumeradoTipo tipoTipos() {
        return EnumeradoTipo.STRUCT;
    }
}
