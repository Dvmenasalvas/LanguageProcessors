package ast.E;


public class MayorIgual extends EBin {
   public MayorIgual(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
   }     
   public TipoE tipo() {return TipoE.MAYORIGUAL;}
    public String toString() {return "("+ opnd1().toString()+" mayor o igual que "+ opnd2().toString()+")";}

}