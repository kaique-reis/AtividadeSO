package view;

import java.util.Scanner;
import controller.RedesController;
public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		RedesController redes = new RedesController();

		int opc = 0;

		while (opc != 9) {

			System.out.println(
					"Escolha a opção desejada: \n 1 - Configurações do Sistema Operacional \n 2 - Média de PING" + "\n"
							+ " 9 - Finalizar Programa");
			opc = sc.nextInt();
			
			switch (opc) {
			case 1:
				System.out.println(redes.ip(redes.identificaSO()));
				break;
			case 2:
				redes.ping(redes.identificaSO());
				break;
			case 9:
				System.out.println("Processo Finalizado! ");
				break;				
			default:
				System.out.println("Opção Inválida!");
				break;
			}
		}
		sc.close();
	}
}