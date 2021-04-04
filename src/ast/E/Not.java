package ast.E;

public class Not extends E {
	private E e1;
    public Not(E e1,int fila,int columna) {
        super(fila,columna);
        this.e1= e1;  
    } 
    
    public TipoE tipo() {return TipoE.NOT;}
    public String toString() {return "not(" + e1.toString() + ")";}
}