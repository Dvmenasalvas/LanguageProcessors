package ast.E;

import ast.E.TipoE;

public class Corchete extends EBin {
   public Corchete(E e1, E e2,int fila,int columna) {
	     super(e1,e2,fila,columna); 
   }     
   public TipoE tipo() {return TipoE.CORCHETE;}
   public String toString() {
	  return "corchete(" + opnd1().toString() + opnd2().toString() + ")";
   }
}
