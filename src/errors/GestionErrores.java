package errors;

import alex.UnidadLexica;

public class GestionErrores {
   public static void errorLexico(int fila, int columna,  String lexema) {
     System.err.println("ERROR léxico fila "+fila+" columna " + columna + ": Caracter inexperado: "+lexema);
     System.exit(1);
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     System.out.println("ERROR sintáctico fila "+unidadLexica.getFila()+": Elemento inexperado "+unidadLexica.value);
     //System.exit(1);
   }
}
