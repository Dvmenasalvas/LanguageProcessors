package ast.E;

import ast.Sentencia;
import ast.E.TipoE;
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
	public void setConstante(boolean constante) {
		this.constante = constante;
	}
	public boolean esConstante() {
		return constante;
	}
   public TipoE tipo() {return TipoE.IDEN;}
   public String toString() {
	   return "{" + nombre + "}";
   }
   public String getNombre() {
	   return nombre;
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
	public void setReferencia(Sentencia referencia) {
		this.referencia = referencia;
	}
	public void setPa(int pa) {
		  profundidadAnidamiento = pa;
	}
	public int getPa() {
		  return profundidadAnidamiento;
	}
	public int getDireccionMemoria() {
		return direccionMemoria;
	}
	public void setDireccionMemoria(int direccionMemoria) {
		this.direccionMemoria = direccionMemoria;
	}
}
