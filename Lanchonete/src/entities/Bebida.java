package entities;

public class Bebida extends ItemPedido {

	private String tamanho;

	public Bebida(String nome, double precoBase, String tamanho) {
		super(nome, precoBase);
		this.tamanho = tamanho;

	}

	public String getTamanho() {
		return tamanho;
	}

	@Override
	public double calcularPrecoFinal() {
		if (tamanho.equalsIgnoreCase("G")) {
			return precoBase + 4.0; 
		}
		return precoBase;
	}

	public String toString() {
		return super.toString() + " | Tamanho: " + tamanho;
	}
}
