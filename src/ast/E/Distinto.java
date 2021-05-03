package ast.E;


public class Distinto extends EBin {
   public Distinto(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
   }

    @Override
    public String nombreOp() {
        return "distinto";
    }

    public TipoE tipoExpresion() {return TipoE.DISTINTO;}
}