package ast.E;

public class Resta extends EBin {
   public Resta(E opnd1, E opnd2, int fila,int columna) {
     super(opnd1,opnd2, fila, columna);  
   }

    @Override
    public String nombreOp() {
        return "resta";
    }

    @Override
    public String wasm_opcode() {
        return "i32.sub";
    }

    public TipoE tipoExpresion() {return TipoE.RESTA;}
}
