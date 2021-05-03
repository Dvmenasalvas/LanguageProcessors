package ast.E;

import ast.Sentencia;
import ast.T.Tipo;

import java.util.List;

public class Iden extends EFinal {
	private String nombre;
	private List<E> dimShape;

	public Iden(String e1, List<E> dimShape,int fila,int columna) {
        super(fila,columna);
        this.nombre = e1;
        this.dimShape = dimShape;
   }    

   public TipoE tipoExpresion() {return TipoE.IDEN;}

    @Override
    public String toString() {
        return "{"+ "Iden: " + nombre + dimShapePrint(dimShape) + '}';
    }

    public String getNombre() {
        return nombre;
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
