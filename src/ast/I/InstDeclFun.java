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

    public List<TipoArgumento> getArgumentos() {
        return argumentos;
    }

    @Override
    public String toString() {
        String out;
        if(tipo != null) out = "{{I: DeclaracionFuncion}{" + tipo + "}" + nombre + "{{Argumentos}";
        else out = "{{I: DeclaracionFuncion}" + "{Void}" + nombre + "{{Argumentos}";

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

    public E getNombre() {
        return nombre;
    }

    public List<I> getCuerpo() {
        return cuerpo;
    }

    public E getRetorno() {
        return retorno;
    }
}
