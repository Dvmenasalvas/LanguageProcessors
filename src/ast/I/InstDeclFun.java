package ast.I;

import ast.E.E;
import ast.T.Tipo;
import ast.T.TipoArgumento;

import java.util.List;

public class InstDeclFun extends I{
    private Tipo tipo;
    private E nombre;
    private List<TipoArgumento> argumentos;
    private List<I> cuerpo;
    private E retorno;

    public InstDeclFun(Tipo tipo, E nombre, List<TipoArgumento> argumentos,
      List<I> cuerpo, E retorno, int fila, int columna) {
        super(fila, columna);
        this.tipo = tipo;
        this.nombre = nombre;
        this.argumentos = argumentos;
        this.cuerpo = cuerpo;
        this.retorno = retorno;
    }

    @Override
    public String toString() {
        String out;
        if(tipo != null) out = "{{_DeclF_}{" + tipo + "}" + nombre + "{{_Args__}";
        else out = "{{_DeclF_}" + "{Void}" + nombre + "{{_Args__}";

        int i = 0;
        for(TipoArgumento arg : argumentos) {
            out += "{{_Arg" + i + "__}{" + arg.getTipo() + "}";
            out += arg.getArgumento();
            out += "}";
            i++;
        }

        out += "}{{_Cuer__}";
        if(cuerpo != null){
            for(I ins : cuerpo) out += ins;
        }
        if(retorno != null) out += "{{__Ret__}" + retorno + "}";
        out += "}}";

        return out;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.DECLFUN;
    }
}
