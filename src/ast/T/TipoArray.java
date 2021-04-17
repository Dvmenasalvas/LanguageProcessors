package ast.T;

import ast.E.E;

import java.util.List;

public class TipoArray extends Tipo{
	private Tipo tipoBase;
	private int dimNum;

	@Override
	public String toString() {
		return "TipoArray{" +
				"tipoBase=" + tipoBase +
				", dimNum=" + dimNum +
				", dimShape=" + dimShape +
				'}';
	}

	private List<E> dimShape;

	public TipoArray( Tipo tipoBase, int dimNum, List<E> dimShape,
					  int fila, int columna) {
		super(fila, columna);
		this.tipoBase = tipoBase;
		this.dimNum = dimNum;
		this.dimShape = dimShape;
	}

	public Tipo getTipoBase() {
		return tipoBase;
	}

	public List<E> getDimShape() {
		return dimShape;
	}

	public int getDimNum() {
		return dimNum;
	}

	@Override
	public EnumeradoTipo tipoTipos() {
		return EnumeradoTipo.ARRAY;
	}
}