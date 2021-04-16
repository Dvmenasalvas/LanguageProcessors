package ast.T;

public class TipoBoolean extends Tipo{

    public TipoBoolean(int fila, int columna) {
        super(fila, columna);
    }

    @Override
    public EnumeradoTipo tipoTipos() {
        return EnumeradoTipo.BOOLEAN;
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Bool";
    }
    
}
