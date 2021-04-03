package ast.E;

import ast.E.TipoE;

public class Not extends E {
	private E e1;
    public Not(E e1,int fila,int columna) {
        this.e1= e1;  
        this.fila = fila;
        this.columna = columna;
    } 
    
    public TipoE tipo() {return TipoE.NOT;}
    public String toString() {return "not(" + e1.toString() + ")";}
}
