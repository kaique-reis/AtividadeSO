package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}
	
public String identificaSO() {
		String os = System.getProperty("os.name");
		return os;
	}

	public String ip(String sO) {
		String ipconfig = " ";

		if (sO.contains("Windows")) {
			try {
				Process processo = Runtime.getRuntime().exec("ipconfig");

				InputStream fluxo = processo.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				System.out.println(linha);

				while (linha != null) {
					if (linha.contains("Ethernet:") || linha.contains("VirtualBox") || linha.contains("Windows")) {
						ipconfig += linha + "\n";
					}

					if (linha.contains("IPv4")) {
						ipconfig += linha + "\n";
					}
					linha = buffer.readLine();
				}

				buffer.close();
				leitor.close();
				fluxo.close();

			} catch (IOException e) {
				if (e.getMessage().contains("error=2")) {
					ipconfig = "Erro = 2 - Arquivo ou diretório não encontrado! \n"
							+ "Possível causa: Chamada de processo Windows em Linux. \n";
				}
				ipconfig = e.getMessage();
			}

		} else if ((sO.contains("Linux"))) {

			try {
				Process processo = Runtime.getRuntime().exec("ifconfig");
				InputStream fluxo = processo.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				System.out.println(linha);

				while (linha != null) {
					if (linha.contains("flags:")) {
						ipconfig += linha + "\n";
					}
					if (linha.contains("netmask")) {
						ipconfig += linha + "\n";
					}
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();

			} catch (IOException e) {
				if (e.getMessage().contains("error=2")) {
					ipconfig = "Erro = 2 - Arquivo ou diretório não encontrado! \n"
							+ "Possível causa: Chamada de processo Linux em Windows. \n";
				} else {
					ipconfig = e.getMessage();
				}
			}

		} else {
			ipconfig = "Configurações do sistema não encontradas!" + "\n";
		}
		return ipconfig;
	}

	public void ping(String sO) {

		double media = 0f;
		double soma = 0;
		String mensagem = " ";

		if ((sO.contains("Windows"))) {
			try {

				Process processo = Runtime.getRuntime().exec("PING -n 10 www.uol.com.br");
				InputStream fluxo = processo.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();

				System.out.println(linha);
				System.out.println("Aguarde, o sistema está processando as informações...");
				System.out.println();
				System.out.println("Verificando iterações do seguinte endereço: www.uol.com.br");
				System.out.println();

				while (linha != null) {

					if (linha.contains("tempo")) {
						int inicioSub = linha.indexOf("o="); 
						int finalSub = linha.indexOf("ms"); 
						soma += Double.parseDouble(linha.substring(inicioSub + 2, finalSub));
					}
					linha = buffer.readLine();
				}
				media = soma / 10;
				System.out.printf("O Tempo médio de PING das iterações solicitadas foi de: %.2fms%n", media);
				System.out.println();

				buffer.close();
				leitor.close();
				fluxo.close();

			} catch (IOException e) {
				mensagem = e.getMessage();
				if (mensagem.contains("error=2")) {
					mensagem = "Erro = 2 - Arquivo ou diretório não encontrado! \n"
							+ "Possível causa: Chamada de processo Linux em Windows. \n";
					System.out.println(mensagem);
				} else {
					System.out.println(mensagem);
				}

			}
		} else if ((sO.contains("Linux"))) {
			try {
				Process processo = Runtime.getRuntime().exec("ping -c 10 www.uol.com.br");
				InputStream fluxo = processo.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();

				System.out.println();
				System.out.println("Aguarde, o sistema está processando as informações...");
				System.out.println();
				System.out.println("Verificando iterações do seguinte endereço: www.uol.com.br");
				System.out.println();

				while (linha != null) {

					if (linha.contains("seq")) {
						int inicioSub = linha.indexOf("e=");
						int finalSub = linha.indexOf(" ms");
						soma += Double.parseDouble(linha.substring(inicioSub + 2, finalSub));
					}
					linha = buffer.readLine();
				}
				media = soma / 10;
				System.out.printf("O Tempo médio de PING das iterações solicitadas foi de: %.2fms%n", media);
				System.out.println();

				buffer.close();
				leitor.close();
				fluxo.close();

			} catch (Exception e) {
				mensagem = e.getMessage();
				if (mensagem.contains("error=2")) {
					mensagem = "Erro = 2 - Arquivo ou diretório não encontrado! \n"
							+ "Possível causa: Chamada de processo Windows em Linux. \n";
					System.out.println(mensagem);
				} else {
					System.out.println(mensagem);
				}
			}

		} else {
			System.out.println("Configurações do sistema não encontradas!" + "\n");
		}

	}
}
