package ast.E;

import java.util.List;

import ast.Sentencia;
import ast.T.Tipo;

public class LlamadaFuncion extends E {
	private Tipo tipoReturn;
	private E nombreFuncion;
	private List<E> argumentos;
	private Sentencia referenciaDeclaracion;
	public LlamadaFuncion(E nombreFuncion, List<E> argumentos,int fila,int columna) {
        super(fila,columna);
		this.nombreFuncion = nombreFuncion;
	    this.argumentos = argumentos;
	}     
    @Override
    public TipoE tipo() {
        return TipoE.FUNCION;
    }
	public String toString() {
		String aux = "{{_Call__}{" + nombreFuncion + "}{{_Args__}";
		for(E argumento : argumentos) aux += argumento.toString();		
		aux += "}}";
		return aux;
	}
	public E getNombreFuncion() {
		return nombreFuncion;
	}
	public List<E> getArgumentos() {
		return argumentos;
	}
	public Sentencia getReferencia() {
		return referenciaDeclaracion;
	}
	public void setReferencia(Sentencia referenciaDeclaracion) {
		this.referenciaDeclaracion = referenciaDeclaracion;
	}
	public Tipo getTipoReturn() {
		return tipoReturn;
	}
	public void setTipoReturn(Tipo tipoReturn) {
		this.tipoReturn = tipoReturn;
	}
	
	
}