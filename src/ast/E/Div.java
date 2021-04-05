package ast;

public class Div extends EBin {
   public Div(E opnd1, E opnd2, int fila, int columna) {
     super(opnd1,opnd2, fila, columna);  
   }     
   public TipoE tipo() {return TipoE.DIV;}
   public String toString() {return "div("+opnd1().toString()+","+opnd2().toString()+")";}
}
