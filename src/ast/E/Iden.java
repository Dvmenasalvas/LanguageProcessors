package ast.E;

import ast.Sentencia;
import ast.T.Tipo;

import java.util.List;

public class Iden extends EFinal {
	private String nombre;
	private List<E> dimShape;
	private List<Tipo> tiposLista;
	private Tipo tipoVariable;
	private Sentencia referencia;
	private Boolean constante = false;

	public Iden(String e1, List<E> dimShape, int fila,int columna) {
        super(fila,columna);
        this.nombre = e1;
        this.dimShape = dimShape;
   }

    public Sentencia getReferencia() {
        return referencia;
    }

    public void setReferencia(Sentencia referencia) {
        this.referencia = referencia;
    }

    public Tipo getTipoVariable() {
        return tipoVariable;
    }

    public void setTipoVariable(Tipo tipoVariable) {
        this.tipoVariable = tipoVariable;
    }

    public String getNombre() {
        return nombre;
    }

    public Boolean getConstante() {
        return constante;
    }

    public void setConstante(Boolean constante) {
        this.constante = constante;
    }

    public TipoE tipoExpresion() {return TipoE.IDEN;}

    public List<E> getDimShape() {
        return dimShape;
    }

    public List<Tipo> getTiposLista() { return tiposLista; }

    public void setTiposLista(List<Tipo> tiposLista) {this.tiposLista = tiposLista;}

    @Override
    public String toString() {
        return "{"+ "Iden: " + nombre + dimShapePrint(dimShape) + '}';
    }


    public String dimShapePrint(List<E> dimShape){
	    String aux = "";

	    if (dimShape != null) {
            for (E dim : dimShape) {
                //EFinal eFinal = (EFinal) dim;
                aux += "[" + dim + "]";
            }
        }

	    return aux;
    }

    @Override
    public String valor() {
	    return nombre;
    }

    public boolean igual(Iden i){
	    boolean out = this.nombre.equals(i.nombre)  && tipoVariable.tipoTipos() == i.tipoVariable.tipoTipos() &&
                this.constante == i.constante && dimShape.size() == i.dimShape.size();
	    if (out){
	        for (int j = 0; j < dimShape.size(); j++){
	            out = out && ((Ent)dimShape.get(j)).valor() == ((Ent)i.dimShape.get(j)).valor();
            }
        }
	    return out;
    }

}
