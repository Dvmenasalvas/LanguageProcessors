package ast.E;

public class Suma extends EBin {
   public Suma(E opnd1, E opnd2, int fila,int columna) {
     super(opnd1,opnd2, fila, columna);  
   }

    @Override
    public String nombreOp() {
        return "suma";
    }

    public TipoE tipoExpresion() {return TipoE.SUMA;}
}
