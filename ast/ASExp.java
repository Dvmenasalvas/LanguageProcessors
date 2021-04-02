package ast;

public class ASExp {
  public E suma(E opnd1, E opnd2) {return new Suma(opnd1,opnd2);}  
  public E mul(E opnd1, E opnd2) {return new Mul(opnd1,opnd2);}  
  public E num(String num) {return new Num(num);}      
}
