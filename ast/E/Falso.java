package ast.E;

import ast.E.TipoE;

public class Falso extends E {
   public Falso(int fila,int columna) {
	     this.fila = fila;
	     this.columna = columna;
   }
   
   public TipoE tipo() {return TipoE.FALSO;}
   public String toString() {return "{false}";}
}