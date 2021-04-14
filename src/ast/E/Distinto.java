package ast.E;


public class Distinto extends EBin {
   public Distinto(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
   }     
   public TipoE tipo() {return TipoE.DISTINTO;}
    public String toString() {return "distinto(" + opnd1().toString()+","+ opnd2().toString() + ")";}

}