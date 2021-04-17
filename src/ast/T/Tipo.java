package ast.T;

import ast.EnumeradoSentencia;
import ast.I.Instrucciones;
import ast.Sentencia;

public abstract class Tipo extends Sentencia {
	public Tipo (int fila, int columna) {
		super(fila, columna);
	}

	public EnumeradoSentencia tipoSentencia() {
		return EnumeradoSentencia.TIPO;
	}

}
