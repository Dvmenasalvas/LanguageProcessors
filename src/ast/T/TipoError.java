package ast.T;

public class TipoError extends Tipo{
    public TipoError(int fila, int columna) {
        super(fila, columna);
    }

    @Override
    public EnumeradoTipo tipoTipos() {
        return EnumeradoTipo.ERROR;
    }

    @Override
    public String toString() {
        return "ERROR";
    }
}
