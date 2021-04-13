
//----------------------------------------------------
// The following code was generated by CUP v0.11b beta 20140220
// Tue Apr 13 18:45:41 CEST 2021
//----------------------------------------------------

package constructorast;

import java_cup.runtime.*;
import alex.AnalizadorLexico;
import alex.UnidadLexica;
import alex.TokenValue;
import errors.GestionErrores;
import ast.E.*;
import ast.T.*;
import ast.AS;
import java.util.List;
import java.util.ArrayList;
import java_cup.runtime.ComplexSymbolFactory.Location;

/** CUP v0.11b beta 20140220 generated parser.
  * @version Tue Apr 13 18:45:41 CEST 2021
  */
public class ConstructAST extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public ConstructAST() {super();}

  /** Constructor which sets the default scanner. */
  public ConstructAST(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public ConstructAST(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\051\000\002\006\005\000\002\002\004\000\002\006" +
    "\003\000\002\007\005\000\002\007\003\000\002\010\005" +
    "\000\002\010\005\000\002\010\005\000\002\010\005\000" +
    "\002\010\005\000\002\010\005\000\002\010\003\000\002" +
    "\011\005\000\002\011\005\000\002\011\003\000\002\012" +
    "\005\000\002\012\005\000\002\012\005\000\002\013\005" +
    "\000\002\013\003\000\002\014\004\000\002\014\003\000" +
    "\002\015\006\000\002\015\004\000\002\015\007\000\002" +
    "\015\006\000\002\015\003\000\002\016\003\000\002\016" +
    "\003\000\002\016\005\000\002\016\003\000\002\016\003" +
    "\000\002\016\006\000\002\020\002\000\002\020\004\000" +
    "\002\022\005\000\002\022\002\000\002\002\003\000\002" +
    "\003\003\000\002\003\003\000\002\005\006" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\105\000\002\001\002\000\044\002\ufff3\004\ufff3\005" +
    "\ufff3\006\ufff3\007\ufff3\010\ufff3\011\ufff3\012\ufff3\013\ufff3" +
    "\015\ufff3\016\ufff3\017\033\020\032\021\031\025\ufff3\033" +
    "\ufff3\040\ufff3\001\002\000\036\002\ufff6\004\ufff6\005\ufff6" +
    "\006\021\007\025\010\024\011\017\012\016\013\022\015" +
    "\020\016\023\025\ufff6\033\ufff6\040\ufff6\001\002\000\016" +
    "\002\ufffd\004\ufffd\005\014\025\ufffd\033\ufffd\040\ufffd\001" +
    "\002\000\014\002\uffff\004\012\025\uffff\033\uffff\040\uffff" +
    "\001\002\000\004\002\011\001\002\000\004\002\000\001" +
    "\002\000\002\001\002\000\012\002\001\025\001\033\001" +
    "\040\001\001\002\000\002\001\002\000\014\002\ufffe\004" +
    "\ufffe\025\ufffe\033\ufffe\040\ufffe\001\002\000\002\001\002" +
    "\000\002\001\002\000\002\001\002\000\002\001\002\000" +
    "\002\001\002\000\002\001\002\000\002\001\002\000\002" +
    "\001\002\000\022\002\ufffb\004\ufffb\005\ufffb\015\020\016" +
    "\023\025\ufffb\033\ufffb\040\ufffb\001\002\000\022\002\ufffa" +
    "\004\ufffa\005\ufffa\015\020\016\023\025\ufffa\033\ufffa\040" +
    "\ufffa\001\002\000\044\002\ufff4\004\ufff4\005\ufff4\006\ufff4" +
    "\007\ufff4\010\ufff4\011\ufff4\012\ufff4\013\ufff4\015\ufff4\016" +
    "\ufff4\017\033\020\032\021\031\025\ufff4\033\ufff4\040\ufff4" +
    "\001\002\000\022\023\046\026\047\027\035\030\036\031" +
    "\037\032\034\036\040\037\041\001\002\000\022\023\046" +
    "\026\047\027\035\030\036\031\037\032\034\036\040\037" +
    "\041\001\002\000\022\023\046\026\047\027\035\030\036" +
    "\031\037\032\034\036\040\037\041\001\002\000\002\001" +
    "\002\000\006\042\072\043\070\001\002\000\050\002\uffe6" +
    "\004\uffe6\005\uffe6\006\uffe6\007\uffe6\010\uffe6\011\uffe6\012" +
    "\uffe6\013\uffe6\015\uffe6\016\uffe6\017\uffe6\020\uffe6\021\uffe6" +
    "\022\uffe6\024\uffe6\025\uffe6\033\uffe6\040\uffe6\001\002\000" +
    "\052\002\uffe5\004\uffe5\005\uffe5\006\uffe5\007\uffe5\010\uffe5" +
    "\011\uffe5\012\uffe5\013\uffe5\015\uffe5\016\uffe5\017\uffe5\020" +
    "\uffe5\021\uffe5\022\uffe5\024\uffe5\025\uffe5\032\057\033\uffe5" +
    "\040\uffe5\001\002\000\050\002\uffe3\004\uffe3\005\uffe3\006" +
    "\uffe3\007\uffe3\010\uffe3\011\uffe3\012\uffe3\013\uffe3\015\uffe3" +
    "\016\uffe3\017\uffe3\020\uffe3\021\uffe3\022\uffe3\024\uffe3\025" +
    "\uffe3\033\uffe3\040\uffe3\001\002\000\050\002\uffe2\004\uffe2" +
    "\005\uffe2\006\uffe2\007\uffe2\010\uffe2\011\uffe2\012\uffe2\013" +
    "\uffe2\015\uffe2\016\uffe2\017\uffe2\020\uffe2\021\uffe2\022\uffe2" +
    "\024\uffe2\025\uffe2\033\uffe2\040\uffe2\001\002\000\050\002" +
    "\uffe7\004\uffe7\005\uffe7\006\uffe7\007\uffe7\010\uffe7\011\uffe7" +
    "\012\uffe7\013\uffe7\015\uffe7\016\uffe7\017\uffe7\020\uffe7\021" +
    "\uffe7\022\uffe7\024\uffe7\025\uffe7\033\uffe7\040\uffe7\001\002" +
    "\000\050\002\uffec\004\uffec\005\uffec\006\uffec\007\uffec\010" +
    "\uffec\011\uffec\012\uffec\013\uffec\015\uffec\016\uffec\017\uffec" +
    "\020\uffec\021\uffec\022\uffec\024\054\025\uffec\033\uffec\040" +
    "\uffec\001\002\000\046\002\uffee\004\uffee\005\uffee\006\uffee" +
    "\007\uffee\010\uffee\011\uffee\012\uffee\013\uffee\015\uffee\016" +
    "\uffee\017\uffee\020\uffee\021\uffee\022\uffee\025\uffee\033\uffee" +
    "\040\uffee\001\002\000\046\002\ufff2\004\ufff2\005\ufff2\006" +
    "\ufff2\007\ufff2\010\ufff2\011\ufff2\012\ufff2\013\ufff2\015\ufff2" +
    "\016\ufff2\017\ufff2\020\ufff2\021\ufff2\022\052\025\ufff2\033" +
    "\ufff2\040\ufff2\001\002\000\022\023\046\026\047\027\035" +
    "\030\036\031\037\032\034\036\040\037\041\001\002\000" +
    "\002\001\002\000\050\002\uffea\004\uffea\005\uffea\006\uffea" +
    "\007\uffea\010\uffea\011\uffea\012\uffea\013\uffea\015\uffea\016" +
    "\uffea\017\uffea\020\uffea\021\uffea\022\uffea\024\uffea\025\uffea" +
    "\033\uffea\040\uffea\001\002\000\046\002\uffed\004\uffed\005" +
    "\uffed\006\uffed\007\uffed\010\uffed\011\uffed\012\uffed\013\uffed" +
    "\015\uffed\016\uffed\017\uffed\020\uffed\021\uffed\022\uffed\025" +
    "\uffed\033\uffed\040\uffed\001\002\000\022\023\046\026\047" +
    "\027\035\030\036\031\037\032\034\036\040\037\041\001" +
    "\002\000\046\002\uffef\004\uffef\005\uffef\006\uffef\007\uffef" +
    "\010\uffef\011\uffef\012\uffef\013\uffef\015\uffef\016\uffef\017" +
    "\uffef\020\uffef\021\uffef\022\uffef\025\uffef\033\uffef\040\uffef" +
    "\001\002\000\002\001\002\000\004\025\056\001\002\000" +
    "\050\002\uffeb\004\uffeb\005\uffeb\006\uffeb\007\uffeb\010\uffeb" +
    "\011\uffeb\012\uffeb\013\uffeb\015\uffeb\016\uffeb\017\uffeb\020" +
    "\uffeb\021\uffeb\022\uffeb\024\uffeb\025\uffeb\033\uffeb\040\uffeb" +
    "\001\002\000\004\033\uffe0\001\002\000\004\033\066\001" +
    "\002\000\006\033\uffdd\040\062\001\002\000\002\001\002" +
    "\000\004\033\uffdf\001\002\000\006\033\uffdd\040\062\001" +
    "\002\000\004\033\uffde\001\002\000\050\002\uffe1\004\uffe1" +
    "\005\uffe1\006\uffe1\007\uffe1\010\uffe1\011\uffe1\012\uffe1\013" +
    "\uffe1\015\uffe1\016\uffe1\017\uffe1\020\uffe1\021\uffe1\022\uffe1" +
    "\024\uffe1\025\uffe1\033\uffe1\040\uffe1\001\002\000\004\024" +
    "\uffdc\001\002\000\004\024\uffda\001\002\000\004\024\073" +
    "\001\002\000\004\024\uffdb\001\002\000\004\025\074\001" +
    "\002\000\050\002\uffe8\004\uffe8\005\uffe8\006\uffe8\007\uffe8" +
    "\010\uffe8\011\uffe8\012\uffe8\013\uffe8\015\uffe8\016\uffe8\017" +
    "\uffe8\020\uffe8\021\uffe8\022\uffe8\024\uffe8\025\uffe8\033\uffe8" +
    "\040\uffe8\001\002\000\004\025\076\001\002\000\050\002" +
    "\uffe9\004\uffe9\005\uffe9\006\uffe9\007\uffe9\010\uffe9\011\uffe9" +
    "\012\uffe9\013\uffe9\015\uffe9\016\uffe9\017\uffe9\020\uffe9\021" +
    "\uffe9\022\uffe9\024\uffe9\025\uffe9\033\uffe9\040\uffe9\001\002" +
    "\000\004\033\100\001\002\000\050\002\uffe4\004\uffe4\005" +
    "\uffe4\006\uffe4\007\uffe4\010\uffe4\011\uffe4\012\uffe4\013\uffe4" +
    "\015\uffe4\016\uffe4\017\uffe4\020\uffe4\021\uffe4\022\uffe4\024" +
    "\uffe4\025\uffe4\033\uffe4\040\uffe4\001\002\000\046\002\ufff1" +
    "\004\ufff1\005\ufff1\006\ufff1\007\ufff1\010\ufff1\011\ufff1\012" +
    "\ufff1\013\ufff1\015\ufff1\016\ufff1\017\ufff1\020\ufff1\021\ufff1" +
    "\022\052\025\ufff1\033\ufff1\040\ufff1\001\002\000\046\002" +
    "\ufff0\004\ufff0\005\ufff0\006\ufff0\007\ufff0\010\ufff0\011\ufff0" +
    "\012\ufff0\013\ufff0\015\ufff0\016\ufff0\017\ufff0\020\ufff0\021" +
    "\ufff0\022\052\025\ufff0\033\ufff0\040\ufff0\001\002\000\022" +
    "\002\ufff7\004\ufff7\005\ufff7\015\020\016\023\025\ufff7\033" +
    "\ufff7\040\ufff7\001\002\000\022\002\ufffc\004\ufffc\005\ufffc" +
    "\015\020\016\023\025\ufffc\033\ufffc\040\ufffc\001\002\000" +
    "\044\002\ufff5\004\ufff5\005\ufff5\006\ufff5\007\ufff5\010\ufff5" +
    "\011\ufff5\012\ufff5\013\ufff5\015\ufff5\016\ufff5\017\033\020" +
    "\032\021\031\025\ufff5\033\ufff5\040\ufff5\001\002\000\022" +
    "\002\ufff9\004\ufff9\005\ufff9\015\020\016\023\025\ufff9\033" +
    "\ufff9\040\ufff9\001\002\000\022\002\ufff8\004\ufff8\005\ufff8" +
    "\015\020\016\023\025\ufff8\033\ufff8\040\ufff8\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\105\000\014\006\007\007\006\010\005\011\004\012" +
    "\003\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\014\006\012\007\006\010\005\011\004\012\003" +
    "\001\001\000\002\001\001\000\012\007\014\010\005\011" +
    "\004\012\003\001\001\000\002\001\001\000\006\011\106" +
    "\012\003\001\001\000\006\011\105\012\003\001\001\000" +
    "\004\012\104\001\001\000\006\011\103\012\003\001\001" +
    "\000\006\011\102\012\003\001\001\000\004\012\027\001" +
    "\001\000\006\011\026\012\003\001\001\000\006\011\025" +
    "\012\003\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\012\013\101\014\043\015\042\016\041" +
    "\001\001\000\012\013\100\014\043\015\042\016\041\001" +
    "\001\000\012\013\044\014\043\015\042\016\041\001\001" +
    "\000\014\006\076\007\006\010\005\011\004\012\003\001" +
    "\001\000\006\002\070\003\066\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\010\014\050\015\042\016\041\001\001\000" +
    "\004\017\047\001\001\000\002\001\001\000\002\001\001" +
    "\000\010\014\052\015\042\016\041\001\001\000\002\001" +
    "\001\000\014\006\054\007\006\010\005\011\004\012\003" +
    "\001\001\000\002\001\001\000\002\001\001\000\016\006" +
    "\060\007\006\010\005\011\004\012\003\020\057\001\001" +
    "\000\002\001\001\000\004\022\062\001\001\000\014\006" +
    "\063\007\006\010\005\011\004\012\003\001\001\000\002" +
    "\001\001\000\004\022\064\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\014\006\074\007\006\010" +
    "\005\011\004\012\003\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$ConstructAST$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$ConstructAST$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$ConstructAST$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


  /** User initialization code. */
  public void user_init() throws java.lang.Exception
    {
 
   errores = new GestionErrores();
   AnalizadorLexico alex = (AnalizadorLexico)getScanner();
   alex.fijaGestionErrores(errores);

    }

  /** Scan to get the next Symbol. */
  public java_cup.runtime.Symbol scan()
    throws java.lang.Exception
    {
 return getScanner().next_token(); 
    }

 
   private GestionErrores errores;
   public void syntax_error(Symbol unidadLexica) {
     errores.errorSintactico((UnidadLexica)unidadLexica);
   }

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$ConstructAST$actions {


   private AS as = new AS();

  private final ConstructAST parser;

  /** Constructor */
  CUP$ConstructAST$actions(ConstructAST parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$ConstructAST$do_action_part00000000(
    int                        CUP$ConstructAST$act_num,
    java_cup.runtime.lr_parser CUP$ConstructAST$parser,
    java.util.Stack            CUP$ConstructAST$stack,
    int                        CUP$ConstructAST$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$ConstructAST$result;

      /* select the action based on the action number */
      switch (CUP$ConstructAST$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // Exp0 ::= Exp1 OR Exp0 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		TokenValue or = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		E op2 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT = as.or(op1, op2, or.getFila(), or.getColumna()); 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp0",4, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= Exp0 EOF 
            {
              Object RESULT =null;
		E start_val = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		RESULT = start_val;
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("$START",0, RESULT);
            }
          /* ACCEPT */
          CUP$ConstructAST$parser.done_parsing();
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // Exp0 ::= Exp1 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT = op1; 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp0",4, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // Exp1 ::= Exp2 AND Exp1 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		TokenValue and = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		E op2 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=as.and(op1, op2, and.getFila(), and.getColumna()); 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp1",5, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // Exp1 ::= Exp2 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT = op1; 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp1",5, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // Exp2 ::= Exp3 IGUALIGUAL Exp3 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		TokenValue ig = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		E op2 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT = as.igualIgual(op1, op2, ig.getFila(), ig.getColumna()); 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp2",6, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // Exp2 ::= Exp3 MAYOR Exp3 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		TokenValue ma = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		E op2 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT = as.mayor(op1, op2, ma.getFila(), ma.getColumna()); 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp2",6, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // Exp2 ::= Exp3 MENOR Exp3 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		TokenValue me = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		E op2 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT = as.menor(op1, op2, me.getFila(), me.getColumna()); 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp2",6, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // Exp2 ::= Exp3 MAYORIGUAL Exp3 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		TokenValue mi = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		E op2 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT = as.mayorIgual(op1, op2, mi.getFila(), mi.getColumna()); 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp2",6, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // Exp2 ::= Exp3 MENORIGUAL Exp3 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		TokenValue mi = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		E op2 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT = as.menorIgual(op1, op2, mi.getFila(), mi.getColumna()); 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp2",6, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // Exp2 ::= Exp3 DISTINTO Exp3 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		TokenValue dist = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		E op2 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT = as.distinto(op1, op2, dist.getFila(), dist.getColumna()); 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp2",6, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // Exp2 ::= Exp3 
            {
              E RESULT =null;
		E op = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT = op; 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp2",6, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // Exp3 ::= Exp3 MAS Exp4 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		TokenValue mas = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		E op2 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=as.suma(op1, op2, mas.getFila(), mas.getColumna()); 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp3",7, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // Exp3 ::= Exp3 MENOS Exp4 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		TokenValue menos = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		E op2 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=as.resta(op1, op2, menos.getFila(), menos.getColumna()); 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp3",7, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // Exp3 ::= Exp4 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=op1; 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp3",7, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // Exp4 ::= Exp4 POR Exp5 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		TokenValue por = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		E op2 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=as.mul(op1, op2, por.getFila(), por.getColumna()); 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp4",8, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // Exp4 ::= Exp4 DIV Exp5 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		TokenValue div = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		E op2 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=as.div(op1, op2, div.getFila(), div.getColumna()); 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp4",8, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // Exp4 ::= Exp4 MOD Exp5 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		TokenValue mod = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		E op2 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=as.mod(op1, op2, mod.getFila(), mod.getColumna()); 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp4",8, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // Exp5 ::= Exp5 POT Exp6 
            {
              E RESULT =null;
		E e1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		TokenValue pot = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		E e2 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=as.pot(e1, e2, pot.getFila(), pot.getColumna());
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp5",9, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // Exp5 ::= Exp6 
            {
              E RESULT =null;
		E e1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=e1;
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp5",9, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // Exp6 ::= NOT Exp6 
            {
              E RESULT =null;
		TokenValue not = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		E e1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=as.not(e1, not.getFila(), not.getColumna());
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp6",10, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // Exp6 ::= Exp7 
            {
              E RESULT =null;
		E e1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=e1;
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp6",10, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // Exp7 ::= Exp7 COR_A Exp0 COR_C 
            {
              E RESULT =null;
		E e1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-3)).value;
		TokenValue ca = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		E e2 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		RESULT=as.corchete(e1, e2, ca.getFila(), ca.getColumna());
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp7",11, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // Exp7 ::= PUNTERO Exp9 
            {
              E RESULT =null;
		TokenValue punt = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT = as.dolar(op1, punt.getFila(), punt.getColumna());
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp7",11, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // Exp7 ::= NEW TIPO COR_A Exp0 COR_C 
            {
              E RESULT =null;
		TokenValue nuevo = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-4)).value;
		Tipo tipo = (Tipo)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-3)).value;
		E tam = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		RESULT = as.nuevo(tipo, tam, nuevo.getFila(), nuevo.getColumna());
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp7",11, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // Exp7 ::= NEW TIPO COR_A COR_C 
            {
              E RESULT =null;
		TokenValue nuevo = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-3)).value;
		Tipo tipo = (Tipo)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		RESULT = as.nuevo(
   tipo, as.ent("1", nuevo.getFila(), nuevo.getColumna()), 
   nuevo.getFila(), nuevo.getColumna());
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp7",11, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // Exp7 ::= Exp8 
            {
              E RESULT =null;
		E op1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=op1;
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp7",11, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // Exp8 ::= ENT 
            {
              E RESULT =null;
		TokenValue e1 = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=as.ent(e1.getLexema(), e1.getFila(), e1.getColumna());
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp8",12, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // Exp8 ::= IDEN 
            {
              E RESULT =null;
		TokenValue e1 = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=as.iden(e1.getLexema(), e1.getFila(), e1.getColumna());
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp8",12, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29: // Exp8 ::= PAREN_A Exp0 PAREN_C 
            {
              E RESULT =null;
		E e1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		RESULT=e1;
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp8",12, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 30: // Exp8 ::= TRUE 
            {
              E RESULT =null;
		TokenValue tru = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=as.verdadero(tru.getFila(), tru.getColumna());
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp8",12, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 31: // Exp8 ::= FALSE 
            {
              E RESULT =null;
		TokenValue fal = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=as.falso(fal.getFila(), fal.getColumna());
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp8",12, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 32: // Exp8 ::= IDEN PAREN_A LIST_ARG PAREN_C 
            {
              E RESULT =null;
		TokenValue id = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-3)).value;
		List<E> args = (List<E>)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		TokenValue pc = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT=as.llamadaFuncion(as.iden(id.getLexema(), id.getFila(), id.getColumna()), args, pc.getFila(), pc.getColumna());
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("Exp8",12, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 33: // LIST_ARG ::= 
            {
              List<E> RESULT =null;
		RESULT=new ArrayList<E>();
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("LIST_ARG",14, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 34: // LIST_ARG ::= Exp0 ARG 
            {
              List<E> RESULT =null;
		E e1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		List<E> e2 = (List<E>)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		e2.add(0, e1); RESULT=e2;
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("LIST_ARG",14, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 35: // ARG ::= COMA Exp0 ARG 
            {
              List<E> RESULT =null;
		E e1 = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-1)).value;
		List<E> e2 = (List<E>)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		e2.add(0, e1); RESULT=e2;
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("ARG",16, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 36: // ARG ::= 
            {
              List<E> RESULT =null;
		RESULT=new ArrayList<E>();
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("ARG",16, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 37: // TIPO ::= TIPO_BASICO 
            {
              Tipo RESULT =null;
		Tipo tipoBasico = (Tipo)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT = tipoBasico; 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("TIPO",0, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 38: // TIPO_BASICO ::= INT 
            {
              Tipo RESULT =null;
		TokenValue tipoBasico = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT = new TipoInt();
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("TIPO_BASICO",1, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 39: // TIPO_BASICO ::= BOOLEAN 
            {
              Tipo RESULT =null;
		TokenValue tipoBasico = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT = new TipoBoolean(); 
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("TIPO_BASICO",1, RESULT);
            }
          return CUP$ConstructAST$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 40: // TIPO_ARRAY ::= COR_A Exp0 COR_C TIPO_ARRAY 
            {
              Tipo RESULT =null;
		TokenValue ca = (TokenValue)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-3)).value;
		E dimension = (E)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.elementAt(CUP$ConstructAST$top-2)).value;
		Tipo tipoArray = (Tipo)((java_cup.runtime.Symbol) CUP$ConstructAST$stack.peek()).value;
		RESULT = new TipoArray(tipoArray, dimension, ca.getFila(), ca.getColumna());
              CUP$ConstructAST$result = parser.getSymbolFactory().newSymbol("TIPO_ARRAY",3, RESULT);
            }
          return CUP$ConstructAST$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$ConstructAST$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$ConstructAST$do_action(
    int                        CUP$ConstructAST$act_num,
    java_cup.runtime.lr_parser CUP$ConstructAST$parser,
    java.util.Stack            CUP$ConstructAST$stack,
    int                        CUP$ConstructAST$top)
    throws java.lang.Exception
    {
              return CUP$ConstructAST$do_action_part00000000(
                               CUP$ConstructAST$act_num,
                               CUP$ConstructAST$parser,
                               CUP$ConstructAST$stack,
                               CUP$ConstructAST$top);
    }
}

