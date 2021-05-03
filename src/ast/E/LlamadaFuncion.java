package ast.E;

import java.util.List;

import ast.Sentencia;
import ast.T.Tipo;

public class LlamadaFuncion extends E {
	private E nombreFuncion;
	private List<E> argumentos;
	private Tipo tipoReturn;
	private Sentencia referenciaDeclaracion;
	public LlamadaFuncion(E nombreFuncion, List<E> argumentos,int fila,int columna) {
        super(fila,columna);
		this.nombreFuncion = nombreFuncion;
	    this.argumentos = argumentos;
	}     
    @Override
    public TipoE tipoExpresion() {
        return TipoE.FUNCION;
    }



	@Override
	public String toString() {
		String aux = "{{" + "Llamada a funcion" + "}" + nombreFuncion + "{{Argumentos}";
		for(E argumento : argumentos) {
			aux += argumento;
		}
		aux += "}}";
		return aux;
	}

	public E getNombreFuncion() {
		return nombreFuncion;
	}

	public List<E> getArgumentos() {
		return argumentos;
	}

	public void setNombreFuncion(E nombreFuncion) {
		this.nombreFuncion = nombreFuncion;
	}

	public void setArgumentos(List<E> argumentos) {
		this.argumentos = argumentos;
	}

	public Tipo getTipoReturn() {
		return tipoReturn;
	}
	public void setTipoReturn(Tipo tipoReturn) {
		this.tipoReturn = tipoReturn;
	}

	public void setReferencia(Sentencia referenciaDeclaracion) {
		this.referenciaDeclaracion = referenciaDeclaracion;
	}
}