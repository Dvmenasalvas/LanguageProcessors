package constructorast;

import alex.AnalizadorLexico;
import asem.AnalizadorSemantico;
import ast.I.I;
import errors.GestionErrores;
import codeGenerator.GeneradorCodigo;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static String FILE_NAME;
    public static List<String> splitThree(String three){
        List<String> children = new ArrayList<>();
        int begin_inst_index, end_inst_index, counter = 0, i = 0;

        begin_inst_index = i;
        while(i < three.length()) {
            if(three.charAt(i) == '{') counter++;
            else if(three.charAt(i) == '}') counter--;

            //Nueva instruccion
            if(counter == 0 && three.charAt(i) == '}') {
                end_inst_index = i;
                children.add(three.substring(begin_inst_index+1, end_inst_index));
                begin_inst_index = i+1;
            }
            else if(counter == 0) begin_inst_index++;
            i++;
        }
        return children;
    }

    public static String printTree(String raiz, List<String> hijos, String nivel_actual, boolean last_child) {
        int tam = hijos.size();
        List<String> hijosDelHijo;
        String total = "", nivel_siguiente = "";

        nivel_siguiente = nivel_actual + "\t|";

        //Raiz
        if(!last_child) total += nivel_actual + raiz + "\n";
        else total += nivel_actual + "|" + raiz + "\n";

        //Iteramos hijos
        for(int i = 0; i < tam; i++) {
            hijosDelHijo = splitThree(hijos.get(i));
            //Hoja
            if(hijosDelHijo.isEmpty()) {
                total += nivel_siguiente +  hijos.get(i) + "\n";
            }
            //Subarbol
            else {
                //Último arbol -> Quitamos el nivel (|)  actual para los siguientes prints
                if(i == tam-1) {
                    nivel_siguiente = nivel_siguiente.substring(0, nivel_siguiente.length()-1);
                    last_child = true;
                }
                //No último hijo
                else last_child = false;
                total += printTree(hijosDelHijo.get(0), hijosDelHijo.subList(1, hijosDelHijo.size()), nivel_siguiente, last_child);
            }
        }
        return total;
    }

   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(args[0]));
     FILE_NAME = args[0];
     System.out.println("Ejecutando análisis lexico/sintáctico.");
	 AnalizadorLexico alex = new AnalizadorLexico(input);
	 ConstructAST constructorast = new ConstructAST(alex);

	 List<I> programa = (List<I>) constructorast.parse().value;

	 if(GestionErrores.erroresSintacticos == 0){
	     System.out.println("Análisis lexico/sintáctico realizado sin errores.");
         System.out.println("Arbol sintático:");

         String tree = programa.toString().substring(1, programa.toString().length()-1);
         System.out.println(printTree("PROGRAMA", splitThree(tree), "", true));

         System.out.println("Realizando análisis semántico.");
         AnalizadorSemantico asem = new AnalizadorSemantico(programa);
         asem.analizar();

         if(GestionErrores.erroresSemanticos == 0) { //Si no hemos tenido errores en el alex, asint y asem, procedemos a generar el codigo
             System.out.println("Análisis semántico finalizado sin errores.\n");

             //3) Generacion de Codigo
             System.out.println("Se inicia la generación de código.\n");
             GeneradorCodigo codeGenerator = new GeneradorCodigo(programa, FILE_NAME);
             codeGenerator.generaCodigo();
             System.out.println("Codigo generado con exito.");

         } else System.out.println("Compilacion detenida: se han detectado errores durante el análisis semántico.");
     } else System.out.println("Compilacion detenida: se han detectado errores durante el análisis sintáctico.");

   }
}

