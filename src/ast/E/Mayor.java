package ast.E;

import ast.E.TipoE;

public class Mayor extends EBin {
   public Mayor(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
   }     
   public TipoE tipoExpresion() {return TipoE.MAYOR;}
}