package errors;

import alex.UnidadLexica;

public class GestionErrores {
   public static void errorLexico(int fila, int columna,  String lexema) {
     System.out.println("ERROR fila "+fila+": Caracter inexperado: "+lexema); 
     System.exit(1);
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     System.out.print("ERROR fila "+unidadLexica.getFila()+": Elemento inexperado "+unidadLexica.value);
     System.exit(1);
   }
}
