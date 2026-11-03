package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Bebida;
import entities.Hamburguer;
import entities.ItemPedido;
import entities.Pedido;
import entities.StatusPedido;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Pedido> pedidos = new ArrayList<>();

		int menu = 0;

		while (menu != 5) {
			System.out.println("======= Menu de Pedidos ========");
			System.out.println("1 - Cadastrar Pedido");
			System.out.println("2 - Fechar Pedido");
			System.out.println("3 - Histórico de Pedidos");
			System.out.println("4 - Pesquisar pedido específico");
			System.out.println("5 - Sair do Progama");
			System.out.println("------------------------------------ \nQual opção você deseja?");
			menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				System.out.println("====== Cadastrar Pedido ========");
				System.out.println("1 - Hamburguer || 2 - Bebida");
				int escolhaPedido = sc.nextInt();
				sc.nextLine();
				System.out.print("Qual nome do combo: ");
				String nomeCombo = sc.nextLine();
				System.out.print("Insira o numero desse combo: ");
				int idPedido = sc.nextInt();
				sc.nextLine();
				System.out.print("Insira o valor do combo: ");
				double valorPedido = sc.nextDouble();
				sc.nextLine();

				ItemPedido CadastroPedidos = null;

				if (escolhaPedido == 1) {
					System.out.print("O combo é duplo? (S/N): ");
					String resposta = sc.nextLine();
					boolean ehDuplo = resposta.equalsIgnoreCase("S") ? true : false;

					CadastroPedidos = new Hamburguer(nomeCombo, valorPedido, ehDuplo);
				} else {

					System.out.print("Qual o tamanho da bebida? (G/M): ");
					String bebidaG = sc.nextLine();

					CadastroPedidos = new Bebida(nomeCombo, valorPedido, bebidaG);
				}
				
				
					
					Pedido pedidosCadastrados = new Pedido(idPedido, CadastroPedidos, StatusPedido.PREPARANDO);
					pedidos.add(pedidosCadastrados);
					System.out.println("Pedido Cadastrado");
					
				
				
				break;
			case 2:
				System.out.println("======= Fechar Pedido =======");
				if (pedidos.isEmpty()) {
					System.out.println("⚠️ Nenhum pedido cadastrado no sistema.");
				} else {
					for (Pedido p : pedidos) {
						double valorTotal = p.getItem().calcularPrecoFinal();
						
						System.out.println("Pedido Nº: " + p.getNumeroPedido() 
								+ " | Item: " + p.getItem().getNome()
								+ " | Valor total a pagar: R$ " + valorTotal);
					}
				}
				break;

			case 3:
				System.out.println("======= Histórico de Pedidos =======");
				if(pedidos.isEmpty()) {
					System.out.println("Nenhum pedido Cadastrado");
				}else {
					for(Pedido c: pedidos) {
						System.out.println(c);
					}
				}
				break;
			case 4:
				System.out.println("======= Pesquisar Pedido Específico =======");
				if (pedidos.isEmpty()) {
					System.out.println("Nenhum pedido cadastrado no sistema.");
				} else {
					System.out.print("Insira o número do pedido que deseja buscar: ");
					int buscarID = sc.nextInt();
					sc.nextLine();

					Pedido encontrado = null;
					for (Pedido p : pedidos) {
						if (p.getNumeroPedido() == buscarID) {
							encontrado = p;
							break; 
						}
					}

					if (encontrado == null) {
						System.out.println("Pedido com o número " + buscarID + " não foi encontrado.");
					} else {
						System.out.println("\n Pedido Encontrado:");
						System.out.println(encontrado); 
						System.out.printf("Valor total deste pedido: R$ %.2f%n", encontrado.getItem().calcularPrecoFinal());
					}
				}
				break;

			case 5:
				System.out.println("Programa Finalizado");
				break;
			}
		}

		sc.close();

	}

}
