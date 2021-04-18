package ast.E;


public class Or extends EBin {
   public Or(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna);
   }

    @Override
    public String nombreOp() {
        return "or";
    }

    public TipoE tipo() {return TipoE.OR;}

}