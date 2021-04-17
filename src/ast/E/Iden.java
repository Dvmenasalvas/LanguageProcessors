package ast.E;

import ast.Sentencia;
import ast.T.Tipo;

public class Iden extends E {
	private String nombre;
	private Tipo tipoVariable;
	private Sentencia referencia;
	private boolean constante =false; //true si es procedimiento
	private int profundidadAnidamiento = 0;
	private int direccionMemoria;
	
	public Iden(String e1,int fila,int columna) {
        super(fila,columna);
        this.nombre = e1;  
   }    

   public TipoE tipo() {return TipoE.IDEN;}
   public String toString() {
	   return "{" + nombre + "}";
   }

   public void setTipo(Tipo tipo) {
	   tipoVariable = tipo;
   }
   public Tipo getTipo() {
	   return tipoVariable;
   }
	public Sentencia getReferencia() {
		return referencia;
	}

}
