package ast.E;

import ast.EnumeradoSentencia;
import ast.Sentencia;

public abstract class E extends Sentencia{
   private boolean asignacion;

   public void setAsignacion(boolean asignacion) {
      this.asignacion = asignacion;
   }

   public boolean isAsignacion() {
      return asignacion;
   }

   public E (int fila, int columna){
      super(fila,columna);
   }
   public abstract TipoE tipoExpresion();
   public E getOpnd1() {throw new UnsupportedOperationException("opnd1");}
   public E getOpnd2() {throw new UnsupportedOperationException("opnd2");}
   public EnumeradoSentencia tipoSentencia() {
      return EnumeradoSentencia.EXPRESION;
   }
}
