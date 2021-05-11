package ast.E;

public class AccederStruct extends E{
    private E id1, id2;
    public AccederStruct(E iden, E iden2, int fila, int columna) {
        super(fila, columna);
        this.id1 = iden;
        this.id2 = iden2;
    }

    @Override
    public TipoE tipo() {
        return TipoE.PUNTO;
    }
}
