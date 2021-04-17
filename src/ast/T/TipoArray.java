package ast.T;

import ast.E.E;
import ast.I.EnumeradoInstrucciones;

import java.util.List;

public class TipoArray extends Tipo{
	private Tipo tipoBase;
	private int dimNum;
	private List<E> dimShape;

	public TipoArray( Tipo tipoBase, int dimNum, List<E> dimShape, int fila, int columna) {
		super(fila, columna);
		this.tipoBase = tipoBase;
		this.dimShape = dimShape;
		this.dimNum = dimNum;
	}


	@Override
	public String toString() {
		return "TipoArray{" +
				"tipoBase=" + tipoBase +
				", dimNum=" + dimNum +
				", dimShape=" + dimShape +
				'}';
	}

	public EnumeradoTipo tipoTipos() {
		return EnumeradoTipo.ARRAY;
	}

}