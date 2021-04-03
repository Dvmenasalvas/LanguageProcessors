package ast;

public abstract class Sentencia {
	protected int fila;
	protected int columna;

	public Sentencia(int fila, int columna){
		this.fila = fila;
		this.columna = columna;
	}
	public int getFila() {
		return fila;
	}
	public int getColumna() {
		return columna;
	}
	
