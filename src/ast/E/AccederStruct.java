package ast.E;

import ast.Sentencia;
import ast.T.Tipo;

public class AccederStruct extends E{
    private E struct;
    private E campo;
    private Sentencia declaracion;
    private Tipo tipoReferencia;

    public void setTipoReferencia(Tipo tipoReferencia) {
        this.tipoReferencia = tipoReferencia;
    }

    public Tipo getTipoReferencia() {
        return tipoReferencia;
    }

    public AccederStruct(Iden iden, Iden iden2, int fila, int columna) {
        super(fila, columna);
        this.struct = iden;
        this.campo = iden2;
    }

    public void setDeclaracion(Sentencia declaracion) {
        this.declaracion = declaracion;
    }

    public E getCampo() {
        return campo;
    }

    public E getStruct() {
        return struct;
    }

    @Override
    public TipoE tipoExpresion() {
        return TipoE.PUNTO;
    }

    @Override
    public String toString() {
        return "{{Acceder}" + struct + campo + "}";
    }
}
