package atividade;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {

		menu();

//		cmd.executar("ver");

//		cmd.executar("dir");

//		cmd.executar("date /t");

//		cmd.executar("time /t");

//		cmd.executar("mkdir pasta1");

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
			System.out.println("\t2- INSERIR");
			System.out.println("\t3- LISTAR");
			System.out.println("\t4- APAGAR");
			System.out.println("\t5- RENOMEAR");
			System.out.println("\t6- MOVER");
			System.out.println("\t7- DELETAR");
			System.out.println("\t8- AJUDA");
			System.out.println("\t9- VER");
			System.out.println("\t10- DATA");
			System.out.println("\t11- HORA");
			System.out.println("\t12- LIMPA");
			System.out.println("\t13- SAIR");
			System.out.println("==============================================");

			entrada = scanner.nextInt();
			scanner.nextLine();

			if (entrada == 1) {
				criar();
			}
			if (entrada == 2) {
				inserir();
			}
			if (entrada == 3) {
				listar();
			}
			if (entrada == 4) {
				apagar();
			}
			if (entrada == 5) {
				renomear();
			}
			if (entrada == 6) {
				mover();
			}
			if (entrada == 7) {
				deletar();
			}
			if (entrada == 8) {
				ajuda();
			}
			if (entrada == 9) {
				ver();
			}
			if (entrada == 10) {
				data();
			}
			if (entrada == 11) {
				hora();
			}
			if (entrada == 12) {
				limpa();
			}
			if (entrada == 13) {
				sair();
			}
			if (entrada == 13) {
				System.out.println("caiu aqui");
				menu = false;
				break;
			}
		}

	}

	public static void criar() {
		CMD cmd = new CMD();
		System.out.println("\tCRIAR");
	}

	public static void inserir() {
		System.out.println("\tINSERIR");
	}
	public static void listar() {
		System.out.println("\tLISTAR");
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
		System.out.println("\tVER");
	}

	public static void data() {
		System.out.println("\tDATA");
	}
	public static void hora() {
		System.out.println("\tHORA");
	}
	public static void limpa() {
		System.out.println("\tLIMPA");
	}
	public static void sair() {
		System.out.println("\tSAIR");
	}
	

	

}
