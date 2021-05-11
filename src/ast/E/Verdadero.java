package ast.E;

public class Verdadero extends E {
   public Verdadero(int fila,int columna) {
      super(fila,columna);
   }
   
   public TipoE tipoExpresion() {return TipoE.VERDADERO;}
   public String toString() {return "true";}
}
