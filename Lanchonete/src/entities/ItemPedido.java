package entities;

public abstract class ItemPedido {

	private String nome;
	protected double precoBase;
	
	public ItemPedido(String nome, double precoBase) {
		super();
		this.nome = nome;
		this.precoBase = precoBase;
	}
	
	public String getNome() {
		return nome;
	}
	public double getPrecoBase() {
		return precoBase;
	}
	public void setPrecoBase(double precoBase) {
		this.precoBase = precoBase;
	}
	
	public abstract double calcularPrecoFinal();
	
	public String toString() {
		return "Nome: " + nome 
				+ "| Preço Base: R$ " + precoBase;
	}
	
}
