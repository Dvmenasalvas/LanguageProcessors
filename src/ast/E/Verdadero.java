package ast.E;

public class Verdadero extends E {
   public Verdadero(int fila,int columna) {
      super(fila,columna);
   }
   
   public TipoE tipo() {return TipoE.VERDADERO;}
   public String toString() {return "true";}
}
