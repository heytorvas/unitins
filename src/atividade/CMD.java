package atividade;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

public class CMD {

	public static boolean verificarCriar(String diretorio, String nome) {
		File arquivo = new File(diretorio);
		File[] file = arquivo.listFiles();

		if (file != null) {

			for (int i = 0; i < file.length; ++i) {
				File f = file[i];

				if (f.isFile()) {
					if (f.getName().equals(nome))
						return false;
				}

				if (f.isDirectory()) {
					if (f.getName().equals(nome))
						return false;
				}
			}
		}
		return true;
	}

	public static boolean verificar(String diretorio, String nome) {
		File arquivo = new File(diretorio);
		File[] file = arquivo.listFiles();

		if (file != null) {

			for (int i = 0; i < file.length; ++i) {
				File f = file[i];

				if (f.isFile()) {
					if (f.getName().equals(nome)) {
						return true;
					}
				}

				if (f.isDirectory()) {
					if (f.getName().equals(nome)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void executar(String comando) {

		ProcessBuilder pb = new ProcessBuilder();

		pb.command("cmd.exe", "/c", comando);

		try {

			Process p = pb.start();

			BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String entrada;
			while ((entrada = bf.readLine()) != null) {
				System.out.println(entrada);
			}

			int saida = p.waitFor();
			if (saida == 0)
				System.out.println("\n\tCOMANDO EXECUTADO COM SUCESSO!");
			else
				System.out.println("\tERRO NO COMANDO!");

		} catch (IOException e) {
			e.printStackTrace();

		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}
}
