package ast.E;

public class Div extends EBin {
   public Div(E opnd1, E opnd2, int fila, int columna) {
     super(opnd1,opnd2, fila, columna);  
   }

    @Override
    public String nombreOp() {
        return "division";
    }

    public TipoE tipo() {return TipoE.DIV;}
}
