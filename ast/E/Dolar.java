package ast.E;

public class Dolar extends E{
	E e1;
	public Dolar(E e1,int fila,int columna){
		this.e1 = e1;
	    this.fila = fila;
	    this.columna = columna;
	}

	public TipoE tipo() {
		return TipoE.DOLAR;
	}
	public E e1() {
		return e1;
	} 
	
   public String toString() {
		  return "{{_Dolar_}" + opnd1().toString() + "}";
   }
	
}
