package ast.E;

public class Pot extends EBin {
   public Pot(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
   }     
   public TipoE tipo() {return TipoE.ELEV;}
   public String toString() {return "elev(" + opnd1().toString() + opnd2().toString() + ")";}
}