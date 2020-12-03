package aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Adotante;
import modelo.Cachorro;
import modelo.Funcionario;
import modelo.Sexo;

public class Principal {
	
	static Cachorro cadastrarCachorro() {
		Scanner sc = new Scanner(System.in);
		Cachorro c = new Cachorro();
		
		System.out.println("=========== CADASTRO CACHORRO ==========");
        System.out.println("INFORME A IDADE: ");
        c.setIdade(sc.nextInt());
        sc.nextLine();
        System.out.println("INFORME A RACA: ");
        c.setRaca(sc.nextLine());
        System.out.println("INFORME SE EXISTE CASTRACAO: ");
        System.out.println("\t(1) - CASTRADO;");
        System.out.println("\t(2) - NAO CASTRADO;");
        int castrado = sc.nextInt();
        sc.nextLine();
        if (castrado == 1) {
        	c.setEhCastrado(true);
        }
        else {
        	c.setEhCastrado(false);
        }
        System.out.println("INFORME A PERSONALIDADE: ");
        c.setPersonalidade(sc.nextLine());
        System.out.println("INFORME O SEXO: ");
        System.out.println("\t(1) - MACHO;");
        System.out.println("\t(2) - FEMEA;");
        int sexo = sc.nextInt();
        sc.nextLine();
        if (sexo == 1) {
        	c.setSexo(Sexo.MASCULINO);
        }
        else {
        	c.setSexo(Sexo.FEMININO);
        }
		sc.close();
		return c;
	}
	
	static void menuFuncionario() {
		Scanner sc = new Scanner(System.in);
		boolean menuFuncionario = true;
		
		while (menuFuncionario) {
	        System.out.println("\n===== ADOTA PALMAS =====");
	        System.out.println("=========== MENU FUNCIONARIO ==========");
	        System.out.println(" (1) - CADASTRAR CACHORRO;");
	        System.out.println(" (2) - EXCLUIR CACHORRO;");
	        System.out.println(" (3) - CONSULTAR CACHORRO;");
	        System.out.println(" (4) - ALTERAR CACHORRO;");
	        System.out.println(" (5) - SAIR;");
	        int opcao = sc.nextInt();
	        
	        switch (opcao) {
	        	case 1:
	        		cadastrarCachorro();
	        		menuFuncionario = false;
	        		break;
	        	case 2:
	        		menuFuncionario = false;
	        		break;
	        	case 3:
	        		menuFuncionario = false;
	        		break;
	        	case 4:
	        		menuFuncionario = false;
	        		break;
	        	case 5:
	        		menuFuncionario = false;
	        		break;
	        	default:
	        		System.out.println("OPCAO ERRADA, TENTE NOVAMENTE!");
	        }
		
		}
		sc.close();
	}
	
	public static void main(String[] args) {
		
		List<Adotante> listaAdotantes = carregarListaAdotantes();
		List<Funcionario> listaFuncionarios = carregarListaFuncionarios();
		List<Cachorro> listaCachorros = carregarListaCachorros();
		boolean condicao = true;
	
        while (condicao) {
    		Scanner sc = new Scanner(System.in);
            System.out.println("======== ADOTA PALMAS ========");
            System.out.println("ESCOLHA A OPCAO: ");
            System.out.println("\t(1) - ADOTANTE;");
            System.out.println("\t(2) - FUNCIONARIO;");
            int menu = sc.nextInt();
            sc.nextLine();
            
            if (menu == 1) {
            	boolean loginAdotante = true;
            	
            	while (loginAdotante) {
            	
	            	System.out.println("=========== LOGIN ADOTANTE ==========");
	                System.out.println("INFORME SEU EMAIL: ");
	                String email = sc.nextLine();
	                System.out.println("INFORME SUA SENHA: ");
	                String senha = sc.nextLine();
	                
	                for (int j = 0; j < listaAdotantes.size(); j++) {
	                	Adotante adotante = listaAdotantes.get(j);
	                	if (adotante.getEmail().equals(email) && adotante.getSenha().equals(senha)){
	                		System.out.println("LOGIN ADOTANTE REALIZADO");
	                		condicao = false;
	                		loginAdotante = false;
	                	}
	                	else {
	                		System.out.println("LOGIN ADOTANTE INCORRETO, TENTE NOVAMENTE");
	                		loginAdotante = true;
	                	}
	                }
            	}
            }
            else if(menu == 2) {
            	boolean loginFuncionario = true;
           
            	while (loginFuncionario) {
            		System.out.println("=========== LOGIN FUNCIONARIO ==========");
                    System.out.println("INFORME SEU EMAIL: ");
                    String email = sc.nextLine();
                    System.out.println("INFORME SUA SENHA: ");
                    String senha = sc.nextLine();
                    
                    for (int j = 0; j < listaFuncionarios.size(); j++) {
                    	Funcionario funcionario = listaFuncionarios.get(j);
                    	if (funcionario.getEmail().equals(email) && funcionario.getSenha().equals(senha)){
                    		System.out.println("LOGIN FUNCIONARIO REALIZADO");
                
                    		menuFuncionario();
                    		
                    		condicao = false;
                    		loginFuncionario = false;
                    	}
                    	else {
	                		System.out.println("LOGIN ADOTANTE INCORRETO, TENTE NOVAMENTE");
	                		loginFuncionario = true;
	                	}
                    }
            	}
            	
            }
            
            else {
            	System.out.println("\tOPCAO ERRADA, TENTE NOVAMENTE! \n");
            }
            
            sc.close();
        }

	}
	
	static List<Adotante> carregarListaAdotantes(){
		List<Adotante> listaAdotantes = new ArrayList<Adotante>();
		Adotante a1 = new Adotante();
		a1.setNome("Gabriela");
		a1.setCpf("123");
		a1.setEmail("gabriela@gmail.com");
		a1.setSenha("unitins");
		a1.setSexo(Sexo.FEMININO);
		a1.getTelefone().setCodigoArea("63");
		a1.getTelefone().setNumero("999990000");
		a1.getEndereco().setRua("Quadra 606 Sul Alameda 15");
		a1.getEndereco().setNumero("Lote 06");
		a1.getEndereco().setBairro("Taquaralto");
		a1.getEndereco().setCidade("Palmas");
		
		listaAdotantes.add(a1);
		return listaAdotantes;
	}
	
	static List<Funcionario> carregarListaFuncionarios(){
		List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
		Funcionario f1 = new Funcionario();
		f1.setNome("Davi");
		f1.setCpf("456");
		f1.setEmail("davi@gmail.com");
		f1.setSenha("unitins");
		f1.setSexo(Sexo.MASCULINO);
		f1.setSalario(1000);
		f1.getTelefone().setCodigoArea("63");
		f1.getTelefone().setNumero("000009999");
		listaFuncionarios.add(f1);
		
		return listaFuncionarios;
	}
	
	static List<Cachorro> carregarListaCachorros(){
		List<Cachorro> listaCachorros = new ArrayList<Cachorro>();
		
		Cachorro c1 = new Cachorro(2, "buldogue", true, "calmo", Sexo.MASCULINO);
		Cachorro c2 = new Cachorro(1, "pastor alemao", false, "raiva", Sexo.FEMININO);
		
		listaCachorros.add(c1);
		listaCachorros.add(c2);
		
		return listaCachorros;
	}
}
