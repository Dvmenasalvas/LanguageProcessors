package asem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import errors.GestionErrores;
import ast.Sentencia;
import ast.T.Tipo;
public class TablaSimbolos {
    private List<HashMap<String,Sentencia>> bloques = new ArrayList<>();
    private Map<String,Tipo> tiposVariables = new HashMap<>();
    public void abreBloque() {
        bloques.add(new HashMap<>());
    }
    public void cierraBloque() {
        bloques.remove(bloques.size()-1);
    }
    public void addTipoVariable(String identificador,Tipo tipoVariable) {
        tiposVariables.put(identificador,tipoVariable);
    }

    public Tipo getTipoVariable(String identificador) {
        if(tiposVariables.containsKey(identificador)) {
            return tiposVariables.get(identificador);
        }
        System.out.println("Warning! Estas pidiendo el tipo de un identificador que no tiene tipo guardado" + identificador);
        return null;
    }
    public void insertaId(String identificador, Sentencia sentencia) {
        HashMap<String,Sentencia> ultimoBloque = bloques.get(bloques.size()-1);
        if(ultimoBloque.containsKey(identificador)) {
            GestionErrores.errorSemantico("ERROR. La variable " + identificador + " ya ha sido declarada.",
                    sentencia.getFila(),sentencia.getColumna());
            //Lanzar excepción
        }else {
            ultimoBloque.put(identificador,sentencia);
        }
    }
    public Sentencia getSentenciaDeclaracion(String identificador) {
        for(int i = bloques.size()-1;i>-1;--i) {
            if(bloques.get(i).containsKey(identificador)) {
                return bloques.get(i).get(identificador);
            }
        }
        return null;
    }
}
