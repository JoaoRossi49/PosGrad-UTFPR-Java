
public class Main {
	public void main(String[] args) {
		Teste.IniciarTeste();	
	}
}

class Teste {
	public static void IniciarTeste(){
		//Veiculo sem atributos, para testar o ex 2.A
		Veiculo v0 = new Veiculo();
		apresentarVeiculo(v0);
		
		Veiculo v1 = new Veiculo("QUB0253", "FIAT", "UNO", "AMARELO", 289.45f, 5, 4, 400);
		apresentarVeiculo(v1);

		//Teste de utilização dos setters
		Veiculo v2 = new Veiculo();
		v2.setPlaca("ARG0093");
		v2.setMarca("FIAT");
		v2.setModelo("ARGO");
		v2.setCor("VERMELHO");
		v2.setVeloMax(182.65f);
		v2.setQtdRodas(5);
		v2.setMotor(4, 400);
		apresentarVeiculo(v2);

		//Teste usando settes e deixando de incluir algum atributo
		Veiculo v3 = new Veiculo();
		v3.setPlaca("TVA0127");
		//v3.setMarca("BMW");
		v3.setModelo("M5");
		//v3.setCor("PRETO");
		v3.setVeloMax(382.15f);
		v3.setQtdRodas(5);
		v3.setMotor(8, 700);

		//Teste deixando de atribuir o motor (Carro elétrico)
		Veiculo v4 = new Veiculo();
		v4.setPlaca("JUS0832");
		v4.setMarca("BYD");
		v4.setModelo("DOLPHIN");
		v4.setCor("ROSA");
		v4.setVeloMax(98.40f);
		v4.setQtdRodas(4);
		//v4.setMotor(4, 169);
		apresentarVeiculo(v4);
		
		//Teste utilizando o construtor
		Veiculo v5 = new Veiculo("KLM4821", "TOYOTA", "COROLLA", "PRATA", 40.00f, 5, 3, 10);
		apresentarVeiculo(v5);
	}

	public static void apresentarVeiculo(Veiculo veiculo){
		System.out.println(
		"Placa: " + veiculo.getPlaca() + 
		"\nMarca: " + veiculo.getMarca() +
		"\nModelo: " + veiculo.getModelo() +
		"\nCor: " + veiculo.getCor() +
		"\nVelocidade: " + veiculo.getVelocMax() + "Km/Hr" +
		"\nRodas: " + veiculo.getQtdRodas() +
		"\n ##### MOTOR #####" +
		"\nPotencia: " + veiculo.getMotor().getPotencia() +
		"\nQuantidade de pistões: " + veiculo.getMotor().getQtdPist() +
		"\n\n"
		);
	}
}

class Motor {
	//Definição de atributos com valor padrão 0
	private int qtdPist = 0;
	private int potencia = 0;

	public Motor(int qtdPist, int potencia) {
		this.qtdPist = qtdPist;
		this.potencia = potencia;
	}

	public Motor() {
	}

	//#region Getters e setters
	public int getQtdPist(){
		return this.qtdPist;
	}

	public void setQtdPist(int qtdPist){
		this.qtdPist = qtdPist;
	}

	public int getPotencia(){
		return this.potencia;
	}

	public void setPotencia(int potencia){
		this.potencia = potencia;
	}
	//#endregion

}

class Veiculo {

	//Definição dos atributos com valor padrão "" ou 0
	private String placa = " ";
	private String marca = " ";
	private String modelo = " ";
	private String cor = " ";
	private float velocMax = 0.0f;
	private int qtdRodas = 0;
	private Motor motor = new Motor();

	public Veiculo(String placa, String marca, String modelo, String cor, float velocMax, int qtdRodas, int qtdPist, int potencia) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.velocMax = velocMax;
		this.qtdRodas = qtdRodas;
		this.motor = new Motor(qtdPist, potencia);
	}

	public Veiculo(){

	}

	//#region getters e setters
	public String getPlaca(){
		return this.placa;
	}

	public void setPlaca(String placa){
		this.placa = placa;
	}

	public String getMarca(){
		return this.marca;
	}

	public void setMarca(String marca){
		this.marca = marca;
	}

	public String getModelo(){
		return this.modelo;
	}

	public void setModelo(String modelo){
		this.modelo = modelo;
	}

	public String getCor(){
		return this.cor;
	}

	public void setCor(String cor){
		this.cor = cor;
	}

	public Float getVelocMax(){
		return this.velocMax;
	}

	public void setVeloMax(Float velocMax){
		this.velocMax = velocMax;
	}

	public int getQtdRodas(){
		return this.qtdRodas;
	}

	public void setQtdRodas(int qtdRodas){
		this.qtdRodas = qtdRodas;
	}

	public Motor getMotor(){
		return this.motor;
	}

	public void setMotor(int qtdPist, int potencia){
		this.motor = new Motor(qtdPist, potencia);
	}
	//#endregion
}