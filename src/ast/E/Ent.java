package ast.E;

public class Ent extends EFinal {
  private String valor;
  public Ent(String v, int fila, int columna) {
    super(fila,columna);
    this.valor = v;
  }
  public TipoE tipo() {return TipoE.ENT;}

  public String toString() {return  "{" + "E: " + valor + "}";}

  @Override
  public String valor() {
      return valor;
  }
}
