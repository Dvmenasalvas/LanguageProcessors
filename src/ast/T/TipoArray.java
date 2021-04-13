package ast.T;

import ast.E.E;

public class TipoArray extends Tipo{
	private Tipo tipoBase;
	private E dimension;
	private int fila;
	private int columna;
	public TipoArray(Tipo tipoBase, E dimension,int fila,int columna) {
		this.tipoBase = tipoBase;
		this.dimension = dimension;
	    this.fila = fila;
	    this.columna = columna;
	}
	@Override
	public EnumeradoTipo tipoEnumerado() {
		return EnumeradoTipo.ARRAY;
	}
	public Tipo getTipoBase() {
		return tipoBase;
	}
	
	public void setTipoBase(Tipo tipoBase) {
		this.tipoBase = tipoBase;
	}
	
	public E getDimension() {
		return dimension;
	}
	public void setDimension(E dimension) {
		this.dimension = dimension;
	}
	
	@Override
	public String toString() {
		return tipoBase.toString() + "[]";
	}
}