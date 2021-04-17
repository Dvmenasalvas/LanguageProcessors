package errors;

import alex.UnidadLexica;

public class GestionErrores {
    public static int numeroErroresSintacticos = 0;
    public static void errorLexico(int fila,int columna, String lexema) {
        System.err.println("ERROR lexico. Fila "+ fila + " columna " + columna + ": Caracter inesperado: " + lexema);
        System.out.println("Se aborta la ejecucion por la presencia de un caracter no reconocido.");
        System.exit(1);
    }
    public void errorSintactico(UnidadLexica unidadLexica) { //pasar fila y columna
        numeroErroresSintacticos++;
        System.err.println("ERROR sintactico " + numeroErroresSintacticos + ". Fila " + unidadLexica.getFila()
                + " Columna " + unidadLexica.getColumna() + ": Elemento inesperado "+ unidadLexica.getLexema());

        // System.exit(1);
    }
}
