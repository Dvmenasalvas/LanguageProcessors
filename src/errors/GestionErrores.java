package errors;

import alex.UnidadLexica;

public class GestionErrores {
    public static int erroresSintacticos = 0;
    public static int erroresSemanticos = 0;

    public static void errorLexico(int fila,int columna, String lexema) {
        System.err.println("ERROR lexico. Fila "+ fila + " columna " + columna + ": Caracter inesperado: " + lexema);
        System.out.println("Se aborta la ejecucion por la presencia de un caracter no reconocido.");
        System.exit(1);
    }
    public void errorSintactico(UnidadLexica unidadLexica) {
        erroresSintacticos++;
        System.err.println("ERROR sintactico " + erroresSintacticos + ". Fila " + unidadLexica.getFila()
                + " Columna " + unidadLexica.getColumna() + ": Elemento inesperado "+ unidadLexica.getLexema());

        // System.exit(1);
    }
    public static void errorSemantico(String mensaje,int fila,int columna) {
        erroresSemanticos++;
        System.err.println("ERROR semantico " + erroresSemanticos + ". Fila "+fila+ " Columna " +columna+ ": " + mensaje);
    }
}
