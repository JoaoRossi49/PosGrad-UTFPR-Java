
public final class Passeio extends Veiculo implements Calcular {

    private int qtdePassageiros;

    public Passeio() {
        super();
        this.qtdePassageiros = 0;
    }

    public int getQtdePassageiros() {
        return qtdePassageiros;
    }

    public final void setQtdePassageiros(int qtdePassageiros) {
        this.qtdePassageiros = qtdePassageiros;
    }

    @Override
    public float calcVel(float velocMax) {
        return velocMax * 1000;
    }

    public int calcular() {
        return getPlaca().length() + getMarca().length() + getModelo().length() + getCor().length();
    }
}
