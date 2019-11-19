package atividade;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
		boolean i = true;
		while(i == true) {
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("comando: ");
			String comando = scanner.nextLine();

			List<String> lista = new ArrayList<String>();
			String[] vetor = comando.split(" ");

			for (String string : vetor) {
				lista.add(string);
				//System.out.println(string);
			}

			//CRIAR
			if (lista.get(0).equalsIgnoreCase("criar")) {
				
				if (lista.get(1).equalsIgnoreCase("-d")) {
					System.out.println("DIRETORIO");
				}
				
				if (lista.get(1).equalsIgnoreCase("-a")) {
					System.out.println("ARQUIVO");
				}
				
			}
			
			//INSERIR
			if (lista.get(0).equalsIgnoreCase("inserir")) {
				
				System.out.println("INSERIR");
				
			}
			
			//LISTAR
			if (lista.get(0).equalsIgnoreCase("listar")) {
				
				if (lista.size() > 1) {
					System.out.println("CAIU AQUI");
				}
				else {
					System.out.println("DEBUG");
				}
				
			}
			
			//APAGAR
			if (lista.get(0).equalsIgnoreCase("apagar")) {
				
			}
			
			//RENOMEAR
			if (lista.get(0).equalsIgnoreCase("renomear")) {
				
			}
			
			//MOVER
			if (lista.get(0).equalsIgnoreCase("mover")) {
				
			}
			
			//DELETAR
			if (lista.get(0).equalsIgnoreCase("deletar")) {
				
			}
			
			//AJUDA
			if (lista.get(0).equalsIgnoreCase("ajuda")) {
				System.out.println("\tAJUDA\n");
				System.out.println("CRIAR -> Cria um arquivo ou diretorio");
				System.out.println("INSERIR -> Insere um texto em um arquivo");
				System.out.println("LISTAR -> Lista arquivos e diretorios da pasta");
				System.out.println("APAGAR -> Apaga um arquivo ou diretorio");
				System.out.println("MOVER -> Move um arquivo ou diretorio");
				System.out.println("DELETAR -> Deleta um arquivo ou diretorio");
				System.out.println("AJUDA -> Lista todos os comandos desta tabela juntamente com descricoes");
				System.out.println("VER -> Imprime a versão do sistema operacional");
				System.out.println("DATA -> Imprime a data do sistema operacional");
				System.out.println("HORA -> Imprime a hora do sistema operacional");
				System.out.println("LIMPA -> Limpa o conteudo atual da tela");
				System.out.println("SAIR -> Finaliza o programa");
				System.out.println();
			}
			
			//VER
			if (lista.get(0).equalsIgnoreCase("ver")) {
				System.out.println("\tVER\n");
				CMD.executar("ver");
				System.out.println();
			}
			
			//DATA
			if (lista.get(0).equalsIgnoreCase("data")) {
				System.out.println("\tDATA\n");
				CMD.executar("date /t");
				System.out.println();
			}
			
			//HORA
			if (lista.get(0).equalsIgnoreCase("hora")) {
				System.out.println("\tHORA\n");
				CMD.executar("time /t");
				System.out.println();
			}
			
			//LIMPAR
			if (lista.get(0).equalsIgnoreCase("limpar")) {
				for (int j = 0; j < 1000; j++) {
					System.out.println("");
				}
			}
			
			// SAIR
			if (lista.get(0).equalsIgnoreCase("sair")) {
				System.out.println("LOGOUT");
				i = false;
				break;
			}
			
		}

	}

	public static void menu() {
		boolean menu = true;
		int entrada = 0;
		Scanner scanner = new Scanner(System.in);

		while (menu == true) {
			System.out.println("==============================================");
			System.out.println("=======================CMD====================");
			System.out.println("==============================================");
			System.out.println();
			System.out.println("Digite o comando: ");
			System.out.println("\t1- CRIAR");
			System.out.println("\t2- LISTAR");
			System.out.println("\t3- APAGAR");
			System.out.println("\t4- RENOMEAR");
			System.out.println("\t5- MOVER");
			System.out.println("\t6- DELETAR");
			System.out.println("\t7- AJUDA");
			System.out.println("\t8- VER");
			System.out.println("\t9- DATA");
			System.out.println("\t10- HORA");
			System.out.println("\t11- LIMPA");
			System.out.println("\t12- SAIR");
			System.out.println("==============================================");

			entrada = scanner.nextInt();
			scanner.nextLine();

			if (entrada == 1) {
				criar();
			}
			if (entrada == 2) {
				listar();
			}
			if (entrada == 3) {
				apagar();
			}
			if (entrada == 4) {
				renomear();
			}
			if (entrada == 5) {
				mover();
			}
			if (entrada == 6) {
				deletar();
			}
			if (entrada == 7) {
				ajuda();
			}
			if (entrada == 8) {
				ver();
			}
			if (entrada == 9) {
				data();
			}
			if (entrada == 10) {
				hora();
			}
			if (entrada == 11) {
				limpa();
			}
			if (entrada == 12) {
				sair();
				System.out.println("\nLOGOUT REALIZADO COM SUCESSO!");
				menu = false;
				break;
			}
		}
	}

	public static void criar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\tCRIAR\n");
		System.out.println("\tDESEJA CRIAR UMA PASTA OU ARQUIVO?");
		System.out.println("\t1- PASTA\n\t2- ARQUIVO");
		int i = scanner.nextInt();
		scanner.nextLine();
		if (i == 1) {
			System.out.println("\tDIGITE O NOME DA PASTA: ");
			String pasta = scanner.nextLine();
			CMD.executar("mkdir " + pasta);
		}
		if (i == 2) {
			System.out.println("\tDIGITE O NOME DO ARQUIVO: ");
			String arquivo = scanner.nextLine();
			System.out.println("\tDIGITE O CONTEUDO DO ARQUIVO: ");
			String conteudo = scanner.nextLine();
			CMD.executar("echo " + conteudo + " > " + arquivo);
		}
	}

	public static void listar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\tLISTAR\n");
		System.out.println("\tDESEJA LISTAR O CONTEUDO DESTA PASTA OU DE OUTRA? ");
		System.out.println("\t1- DESTA PASTA\n\t2- OUTRA PASTA");
		int i = scanner.nextInt();
		scanner.nextLine();
		if (i == 1) {
			CMD.executar("dir");
			System.out.println();
		}
		if (i == 2) {
			System.out.println("\tINFORME O NOME DA PASTA: ");
			String pasta = scanner.nextLine();
			CMD.executar("dir " + pasta);
			System.out.println();
		}
	}

	public static void apagar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\tAPAGAR\n");
		System.out.println("\tDESEJA APAGAR UMA PASTA OU ARQUIVO? ");
		System.out.println("\t1- PASTA\n\t2- ARQUIVO");
		int i = scanner.nextInt();
		scanner.nextLine();
		if (i == 1) {
			System.out.println("\tDIGITE O NOME DA PASTA: ");
			String pasta = scanner.nextLine();
			CMD.executar("rmdir " + pasta);
			System.out.println();
		}
		if (i == 2) {
			System.out.println("\tDIGITE O NOME DO ARQUIVO: ");
			String arquivo = scanner.nextLine();
			CMD.executar("del " + arquivo);
			System.out.println();
		}
	}

	public static void renomear() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\tRENOMEAR\n");
		System.out.println("\tDIGITE O NOME DO ARQUIVO/PASTA A SER RENOMEADO: ");
		String original = scanner.nextLine();
		System.out.println("\tDIGITE O NOVO NOME DO ARQUIVO/PASTA: ");
		String fim = scanner.nextLine();
		CMD.executar("rename " + original + " " + fim);
		System.out.println();
	}

	public static void mover() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\tMOVER");
		System.out.println("\tDIGITE O NOME DO ARQUIVO/PASTA A SER MOVIDO: ");
		String arquivo = scanner.nextLine();
		System.out.println("\tDIGITE O CAMINHO DO DIRETÓRIO: ");
		String diretorio = scanner.nextLine();
		CMD.executar("move " + arquivo + " \"" + diretorio + "\"");
		System.out.println();
	}

	public static void deletar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\tDELETAR\n");
		System.out.println("\tDESEJA DELETAR PASTA OU ARQUIVO? ");
		System.out.println("\t1- PASTA\n\t2- ARQUIVO");
		int i = scanner.nextInt();
		scanner.nextLine();
		if (i == 1) {
			System.out.println("\tDIGITE O NOME DA PASTA A SER DELETADA: ");
			String pasta = scanner.nextLine();
			CMD.executar("rmdir " + pasta);
			System.out.println();
		}
		if (i == 2) {
			System.out.println("\tDIGITE O NOME DO ARQUIVO A SER DELETADO: ");
			String arquivo = scanner.nextLine();
			CMD.executar("del " + arquivo);
			System.out.println();
		}

	}

	public static void ajuda() {
		System.out.println("\tAJUDA");
	}

	public static void ver() {
		System.out.println("\tVER\n");
		CMD.executar("ver");
		System.out.println();
	}

	public static void data() {
		System.out.println("\tDATA\n");
		CMD.executar("date /t");
		System.out.println();
	}

	public static void hora() {
		System.out.println("\tHORA\n");
		CMD.executar("time /t");
		System.out.println();
	}

	public static void limpa() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("");
		}
	}

	public static void sair() {
		System.out.println("\tSAIR");
	}

}
