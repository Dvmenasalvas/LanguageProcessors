package ast.T;

import ast.EnumeradoSentencia;
import ast.I.Instrucciones;
import ast.Sentencia;

public abstract class Tipo extends Sentencia {
	public Tipo (int fila, int columna) {
		super(fila, columna);
	}

	public abstract EnumeradoTipo tipoTipos();
	public EnumeradoSentencia tipoSentencia() {
		return EnumeradoSentencia.TIPO;
	}

	public String num() {
		throw new UnsupportedOperationException("tipo");
	}
}
