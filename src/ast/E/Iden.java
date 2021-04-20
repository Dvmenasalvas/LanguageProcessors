package ast.E;

import ast.Sentencia;
import ast.T.Tipo;

import java.util.List;

public class Iden extends E {
	private String nombre;
	private List<E> dimShape;

	public Iden(String e1, List<E> dimShape,int fila,int columna) {
        super(fila,columna);
        this.nombre = e1;
        this.dimShape = dimShape;
   }    

   public TipoE tipo() {return TipoE.IDEN;}

    @Override
    public String toString() {
        return "{"+ nombre + dimShapePrint(dimShape) + '}';
    }

    public String dimShapePrint(List<E> dimShape){
	    String aux = "";

	    if (dimShape != null) {
            for (int i = 0; i < dimShape.size(); i++) {
                aux = aux + "[" + dimShape.get(i).toString() + "]";
            }
        }

	    return aux;
    }
}
