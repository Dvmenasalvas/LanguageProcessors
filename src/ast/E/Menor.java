package ast.E;

import ast.E.TipoE;

public class Menor extends EBin {
   public Menor(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
   }     
   public TipoE tipoExpresion() {return TipoE.MENOR;}
}