package ast.E;


public class And extends EBin {
    public And(E opnd1, E opnd2,int fila,int columna) {
        super(opnd1,opnd2,fila,columna);
    }

    @Override
    public String nombreOp() {
        return "and";
    }

    public TipoE tipoExpresion() {return TipoE.AND;}

}

