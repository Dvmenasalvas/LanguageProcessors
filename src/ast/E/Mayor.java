package ast.E;


public class Mayor extends EBin {
   public Mayor(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
   }     
   public TipoE tipo() {return TipoE.MAYOR;}
    public String toString() {return "("+ opnd1().toString()+" mayor que "+ opnd2().toString()+")";}

}