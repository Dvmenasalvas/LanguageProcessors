package ast.E;

public class Ent extends E {
  private String v;
  public Ent(String v, int fila, int columna) {
    super(fila,columna);
    this.v = v;   
  }
  public TipoE tipoExpresion() {return TipoE.ENT;}
  public String toString() {return  v ;}
  public String valor() {
    return v;
  }
}
