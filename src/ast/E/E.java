package ast.E;

import ast.EnumeradoSentencia;
import ast.Sentencia;

public abstract class E extends Sentencia{
   public E (int fila, int columna){
      super(fila,columna);
   }
   public abstract TipoE tipo(); 
   public E opnd1() {throw new UnsupportedOperationException("opnd1");} 
   public E opnd2() {throw new UnsupportedOperationException("opnd2");}
   public EnumeradoSentencia tipoSentencia() {
      return EnumeradoSentencia.EXPRESION;
   }
}
