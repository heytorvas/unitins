package aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Adotante;
import modelo.Cachorro;
import modelo.Funcionario;
import modelo.Sexo;

public class Principal {
	
	static List<Adotante> listaAdotantes = carregarListaAdotantes();
	static List<Funcionario> listaFuncionarios = carregarListaFuncionarios();
	static List<Cachorro> listaCachorros = carregarListaCachorros();
	static Scanner sc = new Scanner(System.in);
	
	static boolean verificarCachorroId(List<Cachorro> lista, int id) {
		List<Integer> aux = new ArrayList<Integer>();
		for (int i = 0; i < lista.size(); i++) {
			aux.add(lista.get(i).getId());
		}
		
		for (int i = 0; i < aux.size(); i++) {
			if (aux.get(i).equals(id)) {
				return true;
			}
		}
		
		return false;
	}
	
	static void menuAdotante() {
		
		boolean menuAdotante = true;
		
		while (menuAdotante) {
	        System.out.println("\n========= ADOTA PALMAS ===========");
	        System.out.println("=========== MENU ADOTANTE ==========");
	        System.out.println(" 1 - CONSULTAR CACHORRO;");
	        System.out.println(" 2 - SAIR;");
	        int opcao = sc.nextInt();
	        sc.nextLine();
	        if (opcao == 1) {
	        	Cachorro.consultarCachorro(listaCachorros);
	        	System.out.println("\nINSERCAO REALIZADA COM SUCESSO!\n");
	        }
	        else if (opcao == 2) {
	        	menuAdotante = false;
	        }
	        else {
	        	System.out.println("OPCAO ERRADA, TENTE NOVAMENTE!");
	        }
		
		}
	}

	static void menuFuncionario() {
		boolean menuFuncionario = true;
		
		while (menuFuncionario) {
			System.out.println("\n========= ADOTA PALMAS ===========");
	        System.out.println("=========== MENU FUNCIONARIO ==========");
	        System.out.println(" 1 - CADASTRAR CACHORRO;");
	        System.out.println(" 2 - EXCLUIR CACHORRO;");
	        System.out.println(" 3 - CONSULTAR CACHORRO;");
	        System.out.println(" 4 - ALTERAR CACHORRO;");
	        System.out.println(" 5 - SAIR;");
	        int opcao = sc.nextInt();
	        sc.nextLine();
	        if (opcao == 1) {
	        	Cachorro cachorro = Cachorro.cadastrarCachorro();
	        	if (verificarCachorroId(listaCachorros, cachorro.getId())) {
	        		System.out.println("\nNAO FOI POSSIVEL REALIZAR INSERCAO! ERRO: ID EXISTENTE\n");
	        	}
	        	else {
	        		listaCachorros.add(cachorro);
	        		System.out.println("\nINSERCAO REALIZADA COM SUCESSO!\n");
	        	}
	        }
	        else if (opcao == 2) {
	        	System.out.println("SELECIONE O ID QUE DESEJA EXCLUIR: \n");
	        	Cachorro.imprimirLista(listaCachorros);
	        	int exclusao = sc.nextInt();
	        	sc.nextLine();
	        	
	        	if (verificarCachorroId(listaCachorros, exclusao)) {
	        		for (int i = 0; i < listaCachorros.size(); i++) {
						if (listaCachorros.get(i).getId() == exclusao) {
							listaCachorros.remove(i);
							System.out.println("\nEXCLUSAO REALIZADA COM SUCESSO!\n");
						}
					}
	        	}
	        	else {
	        		System.out.println("ID INVALIDO, TENTE NOVAMENTE!");
	        	}
	        }
	        else if (opcao == 3) {
	        	Cachorro.consultarCachorro(listaCachorros);
	        }
	        else if (opcao == 4) {
	        	System.out.println("SELECIONE O ID QUE DESEJA ALTERAR: \n");
	        	Cachorro.imprimirLista(listaCachorros);
	        	int alteracao = sc.nextInt();
	        	sc.nextLine();
	        	
	        	if (verificarCachorroId(listaCachorros, alteracao)) {
	        		for (int i = 0; i < listaCachorros.size(); i++) {
						if (listaCachorros.get(i).getId() == alteracao) {
							System.out.println("INFORME A IDADE: ");
							listaCachorros.get(i).setIdade(sc.nextInt());
					        sc.nextLine();
					        System.out.println("INFORME A RACA: ");
					        listaCachorros.get(i).setRaca(sc.nextLine());
					        
					        System.out.println("INFORME SE EXISTE CASTRACAO: ");
					        System.out.println("\t1 - CASTRADO;");
					        System.out.println("\t2 - NAO CASTRADO;");
					        int castrado = sc.nextInt();
					        sc.nextLine();
					        if (castrado == 1) {
					        	listaCachorros.get(i).setEhCastrado(true);
					        }
					        else {
					        	listaCachorros.get(i).setEhCastrado(false);
					        }
					        
					        System.out.println("INFORME A PERSONALIDADE: ");
					        listaCachorros.get(i).setPersonalidade(sc.nextLine());
					        System.out.println("INFORME O SEXO: ");
					        System.out.println("\t1 - MACHO;");
					        System.out.println("\t2 - FEMEA;");
					        int sexo = sc.nextInt();
					        if (sexo == 1) {
					        	listaCachorros.get(i).setSexo(Sexo.MASCULINO);
					        }
					        else {
					        	listaCachorros.get(i).setSexo(Sexo.FEMININO);
					        }
					        sc.nextLine();
							
							System.out.println("\nALTERACAO REALIZADA COM SUCESSO!\n");
						}
					}
	        	}
	        	
	        	else {
	        		System.out.println("ID INVALIDO, TENTE NOVAMENTE!");
	        	}
	 
	        }
	        else if (opcao == 5) {
	        	menuFuncionario = false;
	        }
	        else {
	        	System.out.println("OPCAO ERRADA, TENTE NOVAMENTE!");
	        }
		
		}
	}
	
	public static void main(String[] args) {
		
		boolean condicao = true;
	
        while (condicao) {
            System.out.println("======== ADOTA PALMAS ========");
            System.out.println("ESCOLHA A OPCAO: ");
            System.out.println("\t1 - ADOTANTE;");
            System.out.println("\t2 - FUNCIONARIO;");
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
	                		
	                		menuAdotante();
	                		
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
            
        }

	}
	
	static List<Adotante> carregarListaAdotantes(){
		List<Adotante> listaAdotantes = new ArrayList<Adotante>();
		Adotante a1 = new Adotante();
		a1.setId(1);
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
		f1.setId(1);
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
		
		Cachorro c1 = new Cachorro(1, 2, "buldogue", true, "calmo", Sexo.MASCULINO);
		Cachorro c2 = new Cachorro(2, 1, "pastor alemao", false, "raiva", Sexo.FEMININO);
		
		listaCachorros.add(c1);
		listaCachorros.add(c2);
		
		return listaCachorros;
	}
}
