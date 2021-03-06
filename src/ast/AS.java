package ast;

import java.util.List;

import ast.E.*;
import ast.I.*;
import ast.T.*;

public class AS {
  //Expresiones
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
  public E mul(E e1, E e2, int fila, int columna)
    {return new Mul(e1, e2, fila, columna);}
  public E div(E e1, E e2, int fila, int columna)
    {return new Div(e1, e2, fila, columna);}
  public E mod(E e1, E e2, int fila, int columna)
    {return new Mod(e1, e2, fila, columna);}
  public E pot(E e1, E e2, int fila, int columna) 
    {return new Pot(e1, e2, fila,columna);}
  
  //Not, Punto, Corchete llamadaFuncion
  public E accederStruct(Iden iden, Iden iden2, int fila, int columna){
    iden2.setCampoStruct(true);
    return new AccederStruct(iden, iden2, fila, columna);
  }
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
  public Iden iden(String iden, List<E> dimShape, int fila, int columna)
  {return new Iden(iden, dimShape, fila,columna);}

  //Instrucciones
  public I instIf(E condicion, List<I> cuerpo_if, List<I> cuerpo_else, int fila, int columna) {return new InstIf(condicion, cuerpo_if, cuerpo_else,fila,columna);}
  public I instAsignacion(E iden, List<E> valores, int fila,int columna) {return new InstAsignacion(iden, valores,fila,columna);};
  public I instWhile(E cond, List<I> cuerpo, int fila, int columna) {
    return new InstWhile(cond, cuerpo, fila, columna);
  }
  public I instSwitch(E condicion, List<InstCase> instCases, int fila, int columna) {return new InstSwitch(condicion, instCases, fila, columna);};
  public InstCase createCase(E nombreCase, List<I> cuerpo_case, boolean isDefault, int fila, int columna) {return new InstCase(nombreCase, cuerpo_case, isDefault,fila, columna );};
  public I instDeclFun(Tipo tipo, E nombre, List<TipoArgumento> args, List<I> cuerpo, E ret, int fila, int columna) {
    return new InstDeclFun(tipo, nombre, args, cuerpo, ret, fila, columna);
  }
  public I instCallVoidFun(E nombre, List<E> args, int fila, int columna){
    return new InstLlamadaVoid(nombre, args, fila, columna);
  }
  public I instPrint(E exp, int fila, int columna){return new InstPrint(exp, fila, columna);}

  public I instDecl(TipoArray tipo, E iden, List<E> expr,Boolean constante, int fila, int columna) {return new InstDecl(tipo, iden, expr, constante, fila, columna);}
  public I instStruct(E nombreTipo, List<I> lista, int fila, int columna) {return new InstStruct(nombreTipo, lista, fila, columna);}


  //Tipo
  public Tipo tipoInt(int fila, int columna) {return new TipoInt(fila, columna);}
  public Tipo tipoBoolean(int fila, int columna) {return new TipoBoolean(fila, columna);}
  public TipoArray tipoArray(Tipo tipo_base, List<E> dimShape,  int fila, int columna) {return new TipoArray(tipo_base, dimShape,  fila, columna);}
  public Tipo tipoStruct(E nombre,  int fila, int columna) {return new TipoStruct(nombre, fila, columna);}
}
