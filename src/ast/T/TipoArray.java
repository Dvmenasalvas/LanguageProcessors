package ast.T;

import ast.E.E;
import ast.I.Instrucciones;

public class TipoArray extends Tipo{
	private Tipo tipoBase;
	private E dim;
	public TipoArray(Tipo tipoBase, E dim,int fila,int columna) {
		super(fila, columna);
		this.tipoBase = tipoBase;
		this.dim = dim;
	}


	public Tipo getTipoBase() {
		return tipoBase;
	}

	public E getDimension() {
		return dim;
	}

	@Override
	public String toString() {
		return tipoBase.toString() + "[]";
	}

	@Override
	public EnumeradoTipo tipoTipos() {
		return EnumeradoTipo.ARRAY;
	}
}