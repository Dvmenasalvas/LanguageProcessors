package ast.E;

public class Mul extends EBin {
   public Mul(E opnd1, E opnd2, int fila, int columna) {
     super(opnd1,opnd2, fila, columna);  
   }

    @Override
    public String nombreOp() {
        return "multiplicacion";
    }

    public TipoE tipoExpresion() {return TipoE.MUL;}
}
