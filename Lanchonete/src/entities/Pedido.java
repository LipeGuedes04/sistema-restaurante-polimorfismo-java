package entities;

public class Pedido {

	private int numeroPedido;
	private ItemPedido item;
	private StatusPedido status;

	public Pedido(int numeroPedido, ItemPedido item, StatusPedido status) {
		this.numeroPedido = numeroPedido;
		this.item = item;
		this.status = StatusPedido.PREPARANDO;
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public ItemPedido getItem() {
		return item;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public String toString() {
		return item + " | Valor total: " + item.calcularPrecoFinal();
	}
	
}
