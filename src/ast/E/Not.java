package ast.E;

public class Not extends E {
    private E e1;

    public Not(E e1, int fila, int columna) {
        super(fila, columna);
        this.e1 = e1;
    }

    public TipoE tipoExpresion() {
        return TipoE.NOT;
    }

    public String toString() {
        return "{E: not}" + e1 + "}";
    }

    public E getOpnd1() {
        return e1;
    }

    public String wasm_opcode() {
        return "i32.neg";
    }
}
