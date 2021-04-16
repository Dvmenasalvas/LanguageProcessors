package alex;

public class TokenValue {
	private String lexema;
	private int fila;
	private int columna;
	public TokenValue(String lexema, int fila, int columna) {
		super();
		this.lexema = lexema;
		this.fila = fila;
		this.columna = columna;
	}

	public String getLexema() {
		return lexema;
	}
	public int getFila() {
		return fila;
	}
	public int getColumna() {
		return columna;
	}


	@Override
	public String toString() {
		return "TokenValue{" +
				"lexema='" + lexema + '\'' +
				", fila=" + fila +
				", columna=" + columna +
				'}';
	}
}