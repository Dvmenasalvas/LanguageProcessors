package ast.I;

import ast.Sentencia;
import ast.EnumeradoSentencia;

public abstract class I extends Sentencia{
   public I(int fila, int columna) {
        super(fila, columna);
    }
    public abstract EnumeradoInstrucciones tipoInstruccion();
   public I opnd1() {throw new UnsupportedOperationException("opnd1");} 
   public I opnd2() {throw new UnsupportedOperationException("opnd2");}
   public EnumeradoSentencia tipoSentencia() {
	   return EnumeradoSentencia.INSTRUCCION;
   }
}
