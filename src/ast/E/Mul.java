package ast.E;

public class Mul extends EBin {
   public Mul(E opnd1, E opnd2, int fila, int columna) {
     super(opnd1,opnd2, fila, columna);  
   }     
   public TipoE tipo() {return TipoE.MUL;}
   public String toString() {return "mul("+opnd1().toString()+","+opnd2().toString()+")";}
}
