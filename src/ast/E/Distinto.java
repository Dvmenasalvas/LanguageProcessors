package ast.E;

import ast.E.TipoE;

public class Distinto extends EBin {
   public Distinto(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
   }     
   public TipoE tipoExpresion() {return TipoE.DISTINTO;}
}