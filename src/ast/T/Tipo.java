package ast.T;

public abstract class Tipo {
    public EnumeradoSentencia tipoSentencia() {
		return EnumeradoSentencia.TIPO;
	}
	public abstract EnumeradoTipo tipoEnumerado();
	
	public abstract String toString();	  
}
