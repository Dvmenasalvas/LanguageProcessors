package ast.E;


public class MayorIgual extends EBin {
   public MayorIgual(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
   }

    @Override
    public String nombreOp() {
        return "mayorIgual";
    }

    @Override
    public String wasm_opcode() {
        return "i32.ge_s";
    }

    public TipoE tipoExpresion() {return TipoE.MAYORIGUAL;}

}