package atividade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CMD {
	
	public void executar(String comando) {
		
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
			System.out.println("\nSaida com codigo de erro: " + saida);
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}
	}
}
