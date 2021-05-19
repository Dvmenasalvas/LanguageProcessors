package ast.I;

import ast.T.Tipo;
import ast.T.TipoArgumento;
import ast.E.*;

import java.util.List;

public class InstDeclFun extends I{
    private Tipo tipo;
    private E identificador;
    private List<TipoArgumento> argumentos;
    private List<I> cuerpo;
    private E retorno;
    private int profundidadAnidamiento = 0;

    public InstDeclFun(Tipo tipo, E identificador, List<TipoArgumento> argumentos,
                       List<I> cuerpo, E retorno, int fila, int columna) {
        super(fila, columna);
        this.tipo = tipo;
        this.identificador = identificador;
        this.argumentos = argumentos;
        this.cuerpo = cuerpo;
        this.retorno = retorno;
    }

    public List<TipoArgumento> getArgumentos() {
        return argumentos;
    }

    @Override
    public String toString() {
        String out;
        if(tipo != null) out = "{{I: DeclaracionFuncion}{" + tipo + "}" + identificador + "{{Argumentos}";
        else out = "{{I: DeclaracionFuncion}" + "{Void}" + identificador + "{{Argumentos}";

        int i = 0;
        for(TipoArgumento arg : argumentos) {
            out += "{{Argumento" + i + "}{" + arg.getTipo() + "}";
            out += arg.getArgumento();
            out += "}";
            i++;
        }

        out += "}{{Cuerpo}";
        if(cuerpo != null){
            for(I ins : cuerpo) out += ins;
        }
        if(retorno != null) out += "{{Return}" + retorno + "}";
        out += "}}";

        return out;
    }

    @Override
    public EnumeradoInstrucciones tipoInstruccion() {
        return EnumeradoInstrucciones.DECLFUN;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public E getIdentificador() {
        return identificador;
    }

    public List<I> getCuerpo() {
        return cuerpo;
    }

    public E getRetorno() {
        return retorno;
    }

    public int getProfundidadAnidamiento(){
        return profundidadAnidamiento;
    }

    public void setProfundidadAnidamiento(int profundidadAnidamiento){
        this.profundidadAnidamiento = profundidadAnidamiento;
    }
}
