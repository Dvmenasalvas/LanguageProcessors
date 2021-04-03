package ast.E;

import ast.T.Tipo;

public class New extends E{
	private Tipo tipo;
	private E tam;
	public New(Tipo tipo, E tam,int fila,int columna) {
        super(fila,columna);
		this.tipo = tipo;
		this.tam = tam;
	}
	public TipoE tipoExpresion() {
		return TipoE.NUEVO;
	}

	public Tipo getTipo() {
		return tipo;
	}
	
	public E getTam() {
		return tam;
	}

	public String toString() {
		if(tam != null) return "nuevo(" + tipo.toString() + "[" + tam.toString().substring(1, tam.toString().length()-1) + "])";
		else return "nuevo(" + tipo.toString() + ")";
}
