package codeGenerator;

public class InstruccionMaquina {
    private InstruccionesMaquinaEnumerado tipoInstruccionMaquina;
    private int cambioPila = 0;
    private String primerArgumento;
    private String segundoArgumento;
    private boolean tieneSalto;
    private boolean haSaltado;
    public InstruccionMaquina() {
        tieneSalto = false;
        haSaltado = false;
    }

    public InstruccionMaquina(InstruccionesMaquinaEnumerado tipoInstruccionMaquina) {
        this.tipoInstruccionMaquina = tipoInstruccionMaquina;
    }

    public InstruccionMaquina(InstruccionesMaquinaEnumerado tipoInstruccionMaquina,int cambioPila) {
        this.tipoInstruccionMaquina = tipoInstruccionMaquina;
        this.cambioPila = cambioPila;
    }

    public InstruccionMaquina(InstruccionesMaquinaEnumerado tipoInstruccionMaquina,int cambioPila,String primerArgumento) {
        this.tipoInstruccionMaquina = tipoInstruccionMaquina;
        this.cambioPila = cambioPila;
        this.primerArgumento = primerArgumento;
    }
    public InstruccionMaquina(InstruccionesMaquinaEnumerado tipoInstruccionMaquina,int cambioPila,String primerArgumento,String segundoArgumento) {
        this.tipoInstruccionMaquina = tipoInstruccionMaquina;
        this.cambioPila = cambioPila;
        this.primerArgumento = primerArgumento;
        this.segundoArgumento = segundoArgumento;
    }

    public InstruccionMaquina (InstruccionesMaquinaEnumerado tipoInstruccionMaquina, String primerArgumento) {
        this.tipoInstruccionMaquina = tipoInstruccionMaquina;
        this.primerArgumento = primerArgumento;
    }

    public InstruccionMaquina (InstruccionesMaquinaEnumerado tipoInstruccion, String primerArgumento,String segundoArgumento) {
        this.tipoInstruccionMaquina = tipoInstruccionMaquina;
        this.primerArgumento = primerArgumento;
        this.segundoArgumento = segundoArgumento;
    }
    public void setArgumento1(String argumento1) {
        primerArgumento=argumento1;
    }
    public void setArgumento2(String argumento2) {
        primerArgumento=argumento2;
    }
    public InstruccionesMaquinaEnumerado getTipoInstruccion() {
        return tipoInstruccionMaquina;
    }
    public int getCambioPila() {
        return cambioPila;
    }
    @Override
    public String toString() {
        String aux = tipoInstruccionMaquina.toString() + " ";

        if(primerArgumento != null) {
            if(segundoArgumento != null) { //Dos argumentos
                aux += primerArgumento + " " + segundoArgumento;
            } else { //Un argumento
                aux += primerArgumento;
            }
        }
		else { //Sin argumentos -> Imprimimos cambioPila
			aux += Integer.toString(cambioPila);
		}
        aux += ";\n";
        return aux;
    }

}
