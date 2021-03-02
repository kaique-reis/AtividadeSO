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
					"Escolha a op��o desejada: \n 1 - Configura��es do Sistema Operacional \n 2 - M�dia de PING" + "\n"
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
				System.out.println("Op��o Inv�lida!");
				break;
			}
		}
		sc.close();
	}
}