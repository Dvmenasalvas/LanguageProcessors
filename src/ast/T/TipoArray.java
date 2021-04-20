package ast.T;

import ast.E.E;
import ast.I.EnumeradoInstrucciones;

import java.util.List;

public class TipoArray extends Tipo{
	private Tipo tipoBase;
	private List<E> dimShape;

	public TipoArray( Tipo tipoBase, List<E> dimShape, int fila, int columna) {
		super(fila, columna);
		this.tipoBase = tipoBase;
		this.dimShape = dimShape;
	}


	@Override
	public String toString() {
		return tipoBase + dimShapePrint(dimShape);
	}

	public String dimShapePrint(List<E> dimShape) {
		String aux = "";
		if (dimShape != null) {
			for (int i = 0; i < dimShape.size(); i++) {
				aux = aux + "[" + dimShape.get(i).toString() + "]";
			}
		}
		return aux;
	}

	public EnumeradoTipo tipoTipos() {
		return EnumeradoTipo.ARRAY;
	}

}