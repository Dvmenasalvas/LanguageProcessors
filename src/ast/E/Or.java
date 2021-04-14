package ast.E;


public class Or extends EBin {
   public Or(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna);
   }
   public TipoE tipo() {return TipoE.OR;}
    public String toString() {return "(" + opnd1().toString()+" or "+ opnd2().toString() + ")";}

}