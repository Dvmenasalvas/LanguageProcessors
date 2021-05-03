package ast.E;


public class Mayor extends EBin {
   public Mayor(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
   }

    @Override
    public String nombreOp() {
        return "mayor";
    }

    public TipoE tipoExpresion() {return TipoE.MAYOR;}

}