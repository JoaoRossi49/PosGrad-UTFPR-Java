package modelo;


public class Motor {

    private int qldPist;
    private int potencia;

    public Motor() {
        this.qldPist = 0;
        this.potencia = 0;
    }

    public int getQtdPist() {
        return qldPist;
    }

    public final void setQtdPist(int qldPist) {
        this.qldPist = qldPist;
    }

    public int getPotencia() {
        return potencia;
    }

    public final void setPotencia(int potencia) {
        this.potencia = potencia;
    }
}
