package ast.T;

import ast.E.E;
import ast.E.Ent;
import ast.E.Iden;
import ast.I.*;

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
		return "{"+ tipoBase + dimShapePrint(dimShape) + "}";
	}

	public String dimShapePrint(List<E> dimShape) {
		String aux = "";
		if (dimShape != null) {
			for (E dim : dimShape) {
				if (dim instanceof Ent) {
					Ent dimEnt = (Ent) dim;
					aux += "[" + dimEnt.valor() + "]";
				} else if (dim instanceof Iden) {
					Iden dimIden = (Iden) dim;
					aux += "[" + dimIden.getNombre() + "]";
				} else  {
					aux += "[" + dim + "]";
				}
			}
		}
		return aux;
	}

	public EnumeradoTipo tipoTipos() {
		if(dimShape == null){
			if(tipoBase instanceof TipoInt)
				return EnumeradoTipo.INT;
			else if(tipoBase instanceof TipoBoolean)
				return EnumeradoTipo.BOOLEAN;
			else if(tipoBase instanceof TipoStruct)
				return EnumeradoTipo.STRUCT;
			else
				return EnumeradoTipo.ERROR;
		}
		return EnumeradoTipo.ARRAY;
	}

	public Tipo getTipoBase() {
		return tipoBase;
	}

	public List<E> getDimShape() {
		return dimShape;
	}
}