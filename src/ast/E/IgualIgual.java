package ast.E;

public class IgualIgual extends EBin {
	public IgualIgual(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
	}     
	public TipoE tipo() {return TipoE.IGUALIGUAL;}
	public String toString() {return  "(" + opnd1().toString()+" Igual que "+ opnd2().toString() + ")";}
}
