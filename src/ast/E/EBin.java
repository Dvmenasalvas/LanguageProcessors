package ast.E;

public abstract class EBin extends E {
   private E e1;
   private E e2;
   public EBin(E e1, E e2,int fila,int columna) {
      super(fila,columna);
    this.e1 = e1;
    this.e2 = e2;
  }
   public E e1() {return e1;}
   public E e2() {return e2;}    
}