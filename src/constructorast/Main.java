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
    //Funcion auxiliar encargada de separar obtener los hijos de un nodo del arbol
    public static List<String> splitFromParent(String parent){
        List<String> children = new ArrayList<String>();
        int begin_inst_index, end_inst_index, counter = 0, i = 0;

        begin_inst_index = i;
        while(i < parent.length()) {
            char aux = parent.charAt(i);
            if(aux == '{') counter++;
            else if(aux == '}') counter--;

            if(counter == 0 && aux == '}') { //new instruction
                end_inst_index = i;
                children.add(parent.substring(begin_inst_index+1, end_inst_index));
                begin_inst_index = i+1;
            }
            else if(counter == 0) begin_inst_index++;
            i++;
        }
        return children;
    }

    //Funcion encargada de dibujar el AST
    public static String printTree(String raiz, List<String> hijos, String nivel_actual, boolean last_child) {
        int tam = hijos.size();
        List<String> aux;
        String total = "", nivel_siguiente = "";

        nivel_siguiente = nivel_actual + "\t|";

        //Dibujamos la raiz
        if(!last_child) total += nivel_actual + raiz + "\n";
        else total += nivel_actual + "|" + raiz + "\n";
        for(int i = 0; i < tam; i++) { //Para cada hijo
            aux = splitFromParent(hijos.get(i)); //Obtenemos los hijos del hijo
            if(aux.isEmpty()) { //Si no tiene -> es una hoja -> la imprimimos
                total += nivel_siguiente +  hijos.get(i) + "\n";
            }
            else { //Si tiene -> es un subarbol -> recursion
                if(i == tam-1) {
                    nivel_siguiente = nivel_siguiente.substring(0, nivel_siguiente.length()-1);
                    last_child = true;
                }
                else last_child = false;
                total += printTree(aux.get(0), aux.subList(1, aux.size()), nivel_siguiente, last_child);
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
	     System.out.println(programa);
         System.out.println("Arbol sintático:");
         String tree = programa.toString().substring(1, programa.toString().length()-1);
         System.out.println(printTree("PROGRAMA", splitFromParent(tree), "", true));

     }
 }
}   
   
