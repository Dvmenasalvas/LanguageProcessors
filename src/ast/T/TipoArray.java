package ast.T;

import ast.E.E;
import ast.I.Instrucciones;

import java.util.List;

public class TipoArray extends Tipo{
	private Tipo tipoBase;
	private int dimNum;
	private List<E> dimShape;

	@Override
	public String toString() {
		return "TipoArray{" +
				"tipoBase=" + tipoBase +
				", dimNum=" + dimNum +
				", dimShape=" + dimShape +
				'}';
	}


	private List<E> dimShape;



	public TipoArray( Tipo tipoBase, List<E> dimShape,
					  int fila, int columna) {
		super(fila, columna);
		this.tipoBase = tipoBase;
		this.dimShape = dimShape;
	}


	@Override
	public EnumeradoTipo tipoTipos() {
		return EnumeradoTipo.ARRAY;
	}
	@Override
	public String toString() {
		return "TipoArray{" +
				"tipoBase=" + tipoBase +
				", dimShape=" + dimShape +
				'}';
	}
}