package ast.E;

public class Dolar extends E{
	E e1;
	public Dolar(E e1,int fila,int columna){
		super(fila,columna);
		this.e1 = e1;
	}

	public TipoE tipo() {
		return TipoE.DOLAR;
	}

	
   public String toString() {
		  return "{{_Dolar_}" + opnd1().toString() + "}";
   }
	
}
