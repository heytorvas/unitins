package modelo;

import java.util.List;
import java.util.Scanner;

public class Cachorro implements Interface {
	
	static Scanner sc = new Scanner(System.in);
	
	private int id;
	private int idade;
	private String raca;
	private boolean ehCastrado;
	private String personalidade;
	private Sexo sexo;
	
	public Cachorro() {}
	
	public Cachorro(int id, int idade, String raca, boolean ehCastrado, String personalidade, Sexo sexo) {
		super();
		this.id = id;
		this.idade = idade;
		this.raca = raca;
		this.ehCastrado = ehCastrado;
		this.personalidade = personalidade;
		this.sexo = sexo;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public boolean isEhCastrado() {
		return ehCastrado;
	}
	public void setEhCastrado(boolean ehCastrado) {
		this.ehCastrado = ehCastrado;
	}
	public String getPersonalidade() {
		return personalidade;
	}
	public void setPersonalidade(String personalidade) {
		this.personalidade = personalidade;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Cachorro [id=" + id + ", idade=" + idade + ", raca=" + raca + ", ehCastrado=" + ehCastrado + ", personalidade="
				+ personalidade + ", sexo=" + sexo + "]";
	}

	@Override
	public void imprimir() {
		System.out.println(this.toString());
		
	}
	
	public static Cachorro cadastrarCachorro() {
		Cachorro c = new Cachorro();
		
		System.out.println("=========== CADASTRO CACHORRO ==========");
        System.out.println("INFORME O ID: ");
        c.setId(sc.nextInt());
        sc.nextLine();
        System.out.println("INFORME A IDADE: ");
        c.setIdade(sc.nextInt());
        sc.nextLine();
        System.out.println("INFORME A RACA: ");
        c.setRaca(sc.nextLine());
        
        System.out.println("INFORME SE EXISTE CASTRACAO: ");
        System.out.println("\t1 - CASTRADO;");
        System.out.println("\t2 - NAO CASTRADO;");
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
        System.out.println("\t1 - MACHO;");
        System.out.println("\t2 - FEMEA;");
        int sexo = sc.nextInt();
        if (sexo == 1) {
        	c.setSexo(Sexo.MASCULINO);
        }
        else {
        	c.setSexo(Sexo.FEMININO);
        }
        sc.nextLine();
		return c;
	}

    public static void imprimirLista(List<Cachorro> lista){
        lista.forEach(p -> p.imprimir());
    }
    
    public static void imprimirListaOrdenadaRaca(List<Cachorro> lista){
        lista.stream().sorted((c1, c2) -> c1.getRaca().compareTo(c2.getRaca()))
                .forEach(p -> p.imprimir());
    }
    
    public static void imprimirListaSexo(List<Cachorro> lista, Sexo sexo){
        lista.stream().filter(p -> p.getSexo().equals(sexo)).forEach(p -> p.imprimir());
    }
    
    public static void imprimirListaIdade(List<Cachorro> lista){
        lista.sort((c1, c2) -> {
            Integer aux = c1.getIdade();
            return aux.compareTo(c2.getIdade());
        });
        lista.forEach(p -> p.imprimir());
    }
	
    public static void consultarCachorro(List<Cachorro> lista){
        boolean consultaCachorro = true;
        
        while (consultaCachorro) {
            System.out.println("ESCOLHA O TIPO DE CONSULTA: ");
            System.out.println("\t1 - LISTAR TODOS OS CACHORROS");
            System.out.println("\t2 - LISTAR TODOS OS CACHORROS ORDENADOS POR RACA");
            System.out.println("\t3 - LISTAR TODOS OS CACHORROS ORDENADOS POR IDADE");
            System.out.println("\t4 - LISTAR OS CACHORROS POR SEXO");
            int opcao = sc.nextInt();
            sc.nextLine();
            
            if (opcao == 1) {
            	imprimirLista(lista);
            	consultaCachorro = false;
            }
            else if (opcao == 2) {
            	imprimirListaOrdenadaRaca(lista);
            	consultaCachorro = false;
            	
            }
            else if (opcao == 3) {
            	imprimirListaIdade(lista);
            	consultaCachorro = false;
            }
            else if (opcao == 4) {
            	System.out.println("SELECIONE O SEXO DO CACHORRO: ");
                System.out.println("\t1 - MACHO");
                System.out.println("\t2 - FEMEA");
                int sexo = sc.nextInt();
                sc.nextLine();
                if (sexo == 1) {
                	imprimirListaSexo(lista, Sexo.MASCULINO);
                }
                else if (sexo == 2) {
                	imprimirListaSexo(lista, Sexo.FEMININO);
                }
                else {
                	System.out.println("OPCAO INVALIDA, TENTE NOVAMENTE");
                }
            	consultaCachorro = false;
            }
            else {
            	System.out.println("OPCAO ERRADA, TENTE NOVAMENTE!");
            }
        }
    	
    }
}
