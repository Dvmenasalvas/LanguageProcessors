package ast.E;

import ast.Sentencia;
import ast.T.Tipo;

import java.util.List;

public class Iden extends EFinal {
	private String nombre;
	private List<E> dimShape;
	private Tipo tipoVariable;
	private Sentencia referencia;

	public Iden(String e1, List<E> dimShape,int fila,int columna) {
        super(fila,columna);
        this.nombre = e1;
        this.dimShape = dimShape;
   }

    public Sentencia getReferencia() {
        return referencia;
    }

    public void setReferencia(Sentencia referencia) {
        this.referencia = referencia;
    }

    public Tipo getTipoVariable() {
        return tipoVariable;
    }

    public void setTipoVariable(Tipo tipoVariable) {
        this.tipoVariable = tipoVariable;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoE tipoExpresion() {return TipoE.IDEN;}

    @Override
    public String toString() {
        return "{"+ "Iden: " + nombre + dimShapePrint(dimShape) + '}';
    }


    public String dimShapePrint(List<E> dimShape){
	    String aux = "";

	    if (dimShape != null) {
            for (E dim : dimShape) {
                EFinal eFinal = (EFinal) dim;
                aux += "[" + eFinal.valor() + "]";
            }
        }

	    return aux;
    }

    @Override
    public String valor() {
	    return nombre;
    }


}
