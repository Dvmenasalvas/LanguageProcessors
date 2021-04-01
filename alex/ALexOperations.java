
package alex;

import asint.ClaseLexica;

public class ALexOperations {
   private AnalizadorLexicoTiny alex;
   public ALexOperations(AnalizadorLexicoTiny alex) {
      this.alex = alex;   
   }
   public UnidadLexica unidadId() {
      return new UnidadLexica(alex.fila(),ClaseLexica.IDEN,alex.lexema()); 
   } 
   public UnidadLexica unidadEvalua() {
      return new UnidadLexica(alex.fila(),ClaseLexica.EVALUA,"evalua"); 
   } 
   public UnidadLexica unidadDonde() {
      return new UnidadLexica(alex.fila(),ClaseLexica.DONDE,"donde"); 
   } 
   public UnidadLexica unidadEnt() {
      return new UnidadLexica(alex.fila(),ClaseLexica.ENT,alex.lexema()); 
   } 
   public UnidadLexica unidadReal() {
      return new UnidadLexica(alex.fila(),ClaseLexica.REAL,alex.lexema()); 
   } 
   public UnidadLexica unidadSuma() {
      return new UnidadLexica(alex.fila(),ClaseLexica.MAS,"+"); 
   } 
   public UnidadLexica unidadResta() {
      return new UnidadLexica(alex.fila(),ClaseLexica.MENOS,"-"); 
   } 
   public UnidadLexica unidadMul() {
      return new UnidadLexica(alex.fila(),ClaseLexica.POR,"*"); 
   } 
   public UnidadLexica unidadDiv() {
      return new UnidadLexica(alex.fila(),ClaseLexica.DIV,"/"); 
   } 




   public UnidadLexica unidadAnd(){
     return new UnidadLexica(alex.fila(),ClaseLexica.AND, "&");
   }
   public UnidadLexica unidadOr() {
     return new UnidadLexica(alex.fila(), ClaseLexica.OR, "|");
   }
   public UnidadLexica unidadNot() {
      return new UnidadLexica(alex.fila(), ClaseLexica.NOT, "!");
   }
   public UnidadLexica unidadComparadorIgualdad(){
      return new UnidadLexica(alex.fila(), ClaseLexica.IGUALIGUAL, "==");
   }
   public UnidadLexica unidadLT() {
      return new UnidadLexica(alex.fila(), ClaseLexica.LT, "<");
   }
   public UnidadLexica unidadGT() {
      return new UnidadLexica(alex.fila(), ClaseLexica.GT, ">");
   }
   public UnidadLexica unidadLE() {
      return new UnidadLexica(alex.fila(), ClaseLexica.LE, ">=");
   }
   public UnidadLexica unidadGE() {
      return new UnidadLexica(alex.fila(), ClaseLexica.GE, "<=");
   }
   public UnidadLexica unidadLLaveAp() {
      return new UnidadLexica(alex.fila(),ClaseLexica.LLAP,"{"); 
   } 
   public UnidadLexica unidadLLaveCierre() {
      return new UnidadLexica(alex.fila(),ClaseLexica.LLCIERRE,"}"); 
   } 
   public UnidadLexica unidadPAp() {
      return new UnidadLexica(alex.fila(),ClaseLexica.PAP,"("); 
   } 
   public UnidadLexica unidadPCierre() {
      return new UnidadLexica(alex.fila(),ClaseLexica.PCIERRE,")"); 
   } 
   public UnidadLexica unidadCorAp() {
      return new UnidadLexica(alex.fila(),ClaseLexica.CAP,"["); 
   } 
   public UnidadLexica unidadCorCierre() {
      return new UnidadLexica(alex.fila(),ClaseLexica.CCIERRE,"]"); 
   } 
   public UnidadLexica unidadIgual() {
      return new UnidadLexica(alex.fila(),ClaseLexica.IGUAL,"="); 
   } 
   public UnidadLexica unidadComa() {
      return new UnidadLexica(alex.fila(),ClaseLexica.COMA,","); 
   } 
   public UnidadLexica unidadPuntoComa() {
      return new UnidadLexica(alex.fila(),ClaseLexica.PUNTOCOMA,";"); 
   }
   public UnidadLexica unidadPunto() {
      return new UnidadLexica(alex.fila(),ClaseLexica.PUNTO,"."); 
   }
   public UnidadLexica unidadPuntero() {
      return new UnidadLexica(alex.fila(),ClaseLexica.PUNTERO,"¡"); 
   }

  public UnidadLexica unidadEof() {
     return new UnidadLexica(alex.fila(),ClaseLexica.EOF,"<EOF>"); 
  }
}
