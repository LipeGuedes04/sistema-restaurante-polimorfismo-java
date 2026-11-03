package entities;

public class Hamburguer extends ItemPedido {

	boolean duplo;

	public Hamburguer(String nome, double precoBase, boolean duplo) {
		super(nome, precoBase);
		this.duplo = duplo;
	}

	public boolean isDuplo() {
		return duplo;
	}

	public void setDuplo(boolean duplo) {
		this.duplo = duplo;
	}

	@Override
	public double calcularPrecoFinal() {
		if(duplo == true) {
			return precoBase + 8.0;
		}
		return precoBase;
	}
	
	public String toString() {
		return super.toString() + " | Duplo: " + ((duplo)? "Sim" : "Não");
	}

}
