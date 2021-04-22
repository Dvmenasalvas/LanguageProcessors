package constructorast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import alex.AnalizadorLexico;
import ast.I.I;
import errors.GestionErrores;

public class Main {
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
     System.out.println("Ejecutando análisis lexico/sintáctico.");
	 AnalizadorLexico alex = new AnalizadorLexico(input);
	 ConstructAST constructorast = new ConstructAST(alex);

	 List<I> programa = (List<I>) constructorast.parse().value;

	 if(GestionErrores.numeroErroresSintacticos == 0){
	     System.out.println("Análisis lexico/sintáctico realizado sin errores.");
         System.out.println("Arbol sintático:");
         System.out.println(programa);

         String tree = programa.toString().substring(1, programa.toString().length()-1);
         System.out.println(printTree("PROGRAMA", splitThree(tree), "", true));

     }
 }
}   
   
