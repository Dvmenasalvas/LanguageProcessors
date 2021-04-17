package ast.T;

import ast.E.E;

public class TipoStruct extends Tipo{
    private E lexema;

    public TipoStruct(E lexema, int fila, int columna) {
        super(fila, columna);
        this.lexema = lexema;
    }

    @Override
    public EnumeradoTipo tipoTipos() {
        return EnumeradoTipo.STRUCT;
    }
}
