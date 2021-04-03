package ast;

public class Ent extends E {
  private String v;
  public Ent(String v, int fila, int columna) {
    super(fila,columna);
    this.v = v;   
  }
  public String num() {return v;}
  public TipoE tipo() {return TipoE.ENT;}   
  public String toString() {return "{" + v + "}";}  
}
