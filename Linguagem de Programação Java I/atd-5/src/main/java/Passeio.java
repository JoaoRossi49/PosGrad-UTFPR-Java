public final class Passeio extends Veiculo {
    private int qtdePassageiros;

    public Passeio() {
        super();
        this.qtdePassageiros = 0;
    }

    public int getQtdePassageiros() { return qtdePassageiros; }
    public final void setQtdePassageiros(int qtdePassageiros) { this.qtdePassageiros = qtdePassageiros; }

    @Override
    public float calcVel(float velocMax) {
        //calculo sugerido
        return velocMax * 1000;
    }
}