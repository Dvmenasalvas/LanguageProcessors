package ast.E;

import ast.EnumeradoSentencia;

public abstract class EBin extends E {
   private E opnd1;
   private E opnd2;
   public EBin(E e1, E e2,int fila,int columna) {
      super(fila,columna);
    this.opnd1 = e1;
    this.opnd2 = e2;
  }
   public E opnd1() {return opnd1;}
   public E opnd2() {return opnd2;}
    public abstract String nombreOp();

    @Override
    public String toString() {
        return "{" + "{" + "EBin: " + nombreOp() + "}" + opnd1 + opnd2 + '}';
    }
    public EnumeradoSentencia tipoSentencia() {
        return EnumeradoSentencia.EXPRESION_BINARIA;
    }
}
