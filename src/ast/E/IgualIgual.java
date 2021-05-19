package ast.E;

public class IgualIgual extends EBin {
	public IgualIgual(E opnd1, E opnd2,int fila,int columna) {
	     super(opnd1,opnd2,fila,columna); 
	}

	@Override
	public String nombreOp() {
		return "igualigual";
	}

	@Override
	public String wasm_opcode() {
		return "i32.eq";
	}

	public TipoE tipoExpresion() {return TipoE.IGUALIGUAL;}
}
