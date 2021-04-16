package ast.T;

import ast.E.E;

public class TipoArray extends Tipo{
	private Tipo tipoBase;
	private DimArray dim;
	private int fila;
	private int columna;
	public TipoArray(Tipo tipoBase, DimArray dim,int fila,int columna) {
		this.tipoBase = tipoBase;
		this.dim = dim;
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
	
	public DimArray getDimension() {
		return dim;
	}

	@Override
	public String toString() {
		return tipoBase.toString() + "[]";
	}
}