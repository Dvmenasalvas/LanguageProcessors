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
        return "InstDeclFun{" +
                "tipo=" + tipo +
                ", nombre=" + nombre +
                ", argumentos=" + argumentos +
                ", cuerpo=" + cuerpo +
                ", retorno=" + retorno +
                '}';
    }

    @Override
    public Instrucciones tipoInstruccion() {
        return Instrucciones.DECLFUN;
    }
}
