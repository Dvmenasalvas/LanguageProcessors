package ast.E;


public class Menor extends EBin {
   public Menor(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
   }

    @Override
    public String nombreOp() {
        return "menor";
    }

    @Override
    public String wasm_opcode() {
        return "i32.lt_s";
    }

    public TipoE tipoExpresion() {return TipoE.MENOR;}

}