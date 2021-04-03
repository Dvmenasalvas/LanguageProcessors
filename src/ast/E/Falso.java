package ast.E;

public class Falso extends E {
   public Falso(int fila,int columna) {
      super(fila,columna);
   }
   
   public TipoE tipo() {return TipoE.FALSO;}
   public String toString() {return "{false}";}
}