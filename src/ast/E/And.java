package ast.E;


public class And extends EBin {
   public And(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
   }     
   public TipoE tipo() {return TipoE.AND;}
    public String toString() {return "(" + opnd1().toString()+" and "+ opnd2().toString() + ")";}


}