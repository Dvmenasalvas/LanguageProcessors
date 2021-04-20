package ast.E;

import java.util.List;

import ast.Sentencia;
import ast.T.Tipo;

public class LlamadaFuncion extends E {
	private E nombreFuncion;
	private List<E> argumentos;
	public LlamadaFuncion(E nombreFuncion, List<E> argumentos,int fila,int columna) {
        super(fila,columna);
		this.nombreFuncion = nombreFuncion;
	    this.argumentos = argumentos;
	}     
    @Override
    public TipoE tipo() {
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
}