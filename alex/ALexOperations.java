
package alex;

import asint.ClaseLexica;

public class ALexOperations {
   private AnalizadorLexicoTiny alex;
   public ALexOperations(AnalizadorLexicoTiny alex) {
      this.alex = alex;   
   }
   public UnidadLexica unidadEof() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.EOF, "EOF"); 
   }
   public UnidadLexica unidadId() {
      return new UnidadLexica(alex.fila(),ClaseLexica.IDEN,alex.lexema()); 
   } 
   public UnidadLexica unidadEnt() {
      return new UnidadLexica(alex.fila(),ClaseLexica.ENT,alex.lexema()); 
   } 

   public UnidadLexica unidadInt() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.INT, "int");
   }
   public UnidadLexica unidadBoolean() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.BOOLEAN, "boolean");
   }
   public UnidadLexica unidadIf() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.IF, "if");
   }  
   public UnidadLexica unidadElse() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.ELSE, "else");
   }
   public UnidadLexica unidadReturn() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.RETURN, "return");
   }
   public UnidadLexica unidadWhile() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.WHILE, "while");
   }
   public UnidadLexica unidadSwitch() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.SWITCH, "switch");
   }
   public UnidadLexica unidadCase() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.CASE, "case");
   }
   public UnidadLexica unidadDefault() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.DEFAULT, "default");
   }
   public UnidadLexica unidadConst() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.CONST, "const");
   }
   public UnidadLexica unidadTrue() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.TRUE, "true");
   }
   public UnidadLexica unidadFalse() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.FALSE, "false");
   }
   public UnidadLexica unidadStruct() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.STRUCT, "struct");
   }  
   public UnidadLexica unidadNew() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NEW, "new");
   }
   public UnidadLexica unidadVoid() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.VOID, "void");
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
   public UnidadLexica unidadPot() {
      return new UnidadLexica(alex.fila(),ClaseLexica.POT,"**"); 
   } 
   public UnidadLexica unidadDiv() {
      return new UnidadLexica(alex.fila(),ClaseLexica.DIV,"/"); 
   } 
   public UnidadLexica unidadMod() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.MOD, "%"); 
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
   public UnidadLexica unidadEqual(){
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
   public UnidadLexica unidadCorAp() {
      return new UnidadLexica(alex.fila(),ClaseLexica.CAP,"["); 
   } 
   public UnidadLexica unidadCorCierre() {
      return new UnidadLexica(alex.fila(),ClaseLexica.CC,"]"); 
   } 
   public UnidadLexica unidadPAp() {
      return new UnidadLexica(alex.fila(),ClaseLexica.PAP,"("); 
   }
   public UnidadLexica unidadPCierre() {
      return new UnidadLexica(alex.fila(),ClaseLexica.PCIERRE,")"); 
   } 
   public UnidadLexica unidadIgual() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.IGUAL, "="); 
   } 
   public UnidadLexica unidadComa() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.COMA, ","); 
   } 
   public UnidadLexica unidadPuntoComa() {
      return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.PCOMA, ";"); 
   } 
   public UnidadLexica unidadPunto() {
         return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.PUNTO, "."); 
   } 
   public UnidadLexica unidadPuntero() {
         return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.PUNTO, "¡"); 
   } 
   public void error() {
      GestionErroresTiny.errorLexico(alex.fila(), alex.columna(), alex.lexema());
    }
}
