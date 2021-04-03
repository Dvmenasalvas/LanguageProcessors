package ast.E;

import ast.E.TipoE;

public class Verdadero extends E {
   public Verdadero(int fila,int columna) {
	     this.fila = fila;
	     this.columna = columna;
   }
   
   public TipoE tipo() {return TipoE.VERDADERO;}
   public String toString() {return "{true}";}
}
