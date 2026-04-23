public abstract class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private float velocMax;
    private int qtdRodas;
    private Motor motor;

    public Veiculo() {
        this.placa = "";
        this.marca = "";
        this.modelo = "";
        this.cor = "";
        this.velocMax = 0;
        this.qtdRodas = 0;
        this.motor = new Motor();
    }

    public String getPlaca() { return placa; }
    public final void setPlaca(String placa) { this.placa = placa; }

    public String getMarca() { return marca; }
    public final void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public final void setModelo(String modelo) { this.modelo = modelo; }

    public String getCor() { return cor; }
    public final void setCor(String cor) { this.cor = cor; }

    public float getVelocMax() { return velocMax; }
    public final void setVelocMax(float velocMax) { this.velocMax = velocMax; }

    public int getQtdRodas() { return qtdRodas; }
    public final void setQtdRodas(int qtdRodas) { this.qtdRodas = qtdRodas; }

    public Motor getMotor() { return motor; }
    public final void setMotor(Motor motor) { this.motor = motor; }

    //metodo abstrato
    public abstract float calcVel(float velocMax);
}