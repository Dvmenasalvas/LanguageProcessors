package ast.E;


public class MenorIgual extends EBin {
   public MenorIgual(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
   }     
   public TipoE tipo() {return TipoE.MENORIGUAL;}
}