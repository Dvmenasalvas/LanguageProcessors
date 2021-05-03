package ast.E;


public class MenorIgual extends EBin {
   public MenorIgual(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
   }

    @Override
    public String nombreOp() {
        return "menorIgual";
    }

    public TipoE tipoExpresion() {return TipoE.MENORIGUAL;}
}