package ast.E;

public class Suma extends EBin {
   public Suma(E opnd1, E opnd2, int fila,int columna) {
     super(opnd1,opnd2, fila, columna);  
   }     
   public TipoE tipo() {return TipoE.SUMA;}
   public String toString() {return "suma("+opnd1().toString()+","+opnd2().toString()+")";}
}
