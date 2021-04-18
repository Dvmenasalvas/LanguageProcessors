package ast.E;

public class Mod extends EBin {
   public Mod(E opnd1, E opnd2, int fila, int columna) {
     super(opnd1,opnd2, fila, columna);  
   }

    @Override
    public String nombreOp() {
        return "modulo";
    }

    public TipoE tipo() {return TipoE.MOD;}
}
