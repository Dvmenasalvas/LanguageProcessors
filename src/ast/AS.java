package ast;

import java.util.List;

import ast.E.Corchete;
import ast.E.Dolar;
import ast.E.E;
import ast.E.Ent;
import ast.E.Falso;
import ast.E.Iden;
import ast.E.LlamadaFuncion;
import ast.E.Not;
import ast.E.Nuevo;
import ast.E.Pot;
import ast.E.Verdadero;
import ast.T.Tipo;

public class AS {
  //EXPRESIONES
  public E or(E e1, E e2, int fila, int columna)
    {return new Or(e1, e2, fila, columna);}
  public E and(E e1, E e2, int fila, int columna)
    {return new And(e1, e2, fila, columna);}
  public E igualIgual(E e1, E e2, int fila, int columna)
    {return new IgualIgual(e1, e2, fila, columna);}
  public E mayor(E e1, E e2, int fila, int columna)
    {return new Mayor(e1, e2, fila, columna);}
  public E menor(E e1, E e2, int fila, int columna)
    {return new Menor(e1, e2, fila, columna);}
  public E mayorIgual(E e1, E e2, int fila, int columna)
    {return new MayorIgual(e1, e2, fila, columna);}
  public E menorIgual(E e1, E e2, int fila, int columna)
    {return new MenorIgual(e1, e2, fila, columna);}
  public E distinto(E e1, E e2, int fila, int columna)
    {return new Distinto(e1, e2, fila, columna);}
    

  //Operaciones
  public E suma(E e1, E e2, int fila, int columna)
    {return new Suma(e1, e2, fila, columna);}
  public E resta(E e1, E e2, int fila, int columna)
    {return new Resta(e1, e2, fila, columna);}
  public E multiplicacion(E e1, E e2, int fila, int columna)
    {return new Multiplicacion(e1, e2, fila, columna);}
  public E division(E e1, E e2, int fila, int columna)
    {return new Division(e1, e2, fila, columna);}
  public E modulo(E e1, E e2, int fila, int columna)
    {return new Modulo(e1, e2, fila, columna);}
  public E pot(E e1, E e2, int fila, int columna) 
    {return new Pot(e1, e2, fila,columna);}
  
  //Corchetes, punteros, new, not, llamadaFuncion
  public E corchete(E e1, E e2,int fila,int columna) 
  {return new Corchete(e1,e2,fila,columna);}
  public E dolar(E e1,int fila,int columna) 
  {return new Dolar(e1,fila,columna);}
  public E nuevo(Tipo tipo, E tam,int fila,int columna) 
  {return new Nuevo(tipo, tam,fila,columna);} 
  public E not(E e1,int fila,int columna) 
  {return new Not(e1,fila,columna);}
  public E llamadaFuncion(E nombre, List<E> args,int fila,int columna) 
  {return new LlamadaFuncion(nombre, args, fila, columna);}


  //Terminales
  public E ent(String num, int fila, int columna) 
  {return new Ent(num, fila,columna);}
  public E verdadero(int fila, int columna) 
  {return new Verdadero(fila,columna);}
  public E falso(int fila, int columna) 
  {return new Falso(fila,columna);}
  public E iden(String iden, int fila, int columna) 
  {return new Iden(iden, fila,columna);}
}
