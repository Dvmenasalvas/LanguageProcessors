package ast.E;

public class Resta extends EBin {
   public Resta(E opnd1, E opnd2, int fila,int columna) {
     super(opnd1,opnd2, fila, columna);  
   }     
   public TipoE tipo() {return TipoE.RESTA;}
   public String toString() {return "resta("+opnd1().toString()+","+opnd2().toString()+")";}
}
