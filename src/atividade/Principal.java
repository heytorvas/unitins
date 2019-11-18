package atividade;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {

//		menu();



		





		CMD.executar("mkdir pasta1.txt");

//		cmd.executar("echo hello world > teste1.txt");

//		cmd.executar("del teste1.txt");

//		cmd.executar("rmdir pasta1");

//		cmd.executar("rename teste1.txt teste15.txt");

//		cmd.executar("rename pasta1 pasta15");

//		cmd.executar("move teste15.txt \"C:\\Users\\NIT5\\Desktop\"");
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
		System.out.println("\tCRIAR");
	}

	public static void listar() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\tLISTAR\n");
		System.out.println("\tDESEJA LISTAR O CONTEÚDO DESTA PASTA OU DE OUTRA? ");
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
		scanner.close();
	}

	public static void apagar() {
		System.out.println("\tAPAGAR");
	}

	public static void renomear() {
		System.out.println("\tRENOMEAR");
	}

	public static void mover() {
		System.out.println("\tMOVER");
	}
	public static void deletar() {
		System.out.println("\tDELETAR");
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
		System.out.println("\tLIMPA");
	}
	public static void sair() {
		System.out.println("\tSAIR");
	}


}
