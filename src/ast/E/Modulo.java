package ast;

public class Mod extends EBin {
   public Mod(E opnd1, E opnd2, int fila, int columna) {
     super(opnd1,opnd2, fila, columna);  
   }     
   public TipoE tipo() {return TipoE.MOD;}
}
