package ast.E;

public class Pot extends EBin {
   public Pot(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
   }

    @Override
    public String nombreOp() {
        return "potencia";
    }

    @Override
    public String wasm_opcode() {
        return "f32.sqrt";
    }

    public TipoE tipoExpresion() {return TipoE.ELEV;}
}
