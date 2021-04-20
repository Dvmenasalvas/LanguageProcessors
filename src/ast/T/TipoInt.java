package ast.T;

public class TipoInt extends Tipo{

    public TipoInt(int fila, int columna) {
        super(fila, columna);
    }

    @Override
    public EnumeradoTipo tipoTipos() {
        return EnumeradoTipo.INT;
    }


    @Override
    public String toString() {
        return "Int";
    }
}
