package modelo;

public class Cachorro implements Interface {
	private int idade;
	private String raca;
	private boolean ehCastrado;
	private String personalidade;
	private Sexo sexo;
	
	public Cachorro() {}
	
	public Cachorro(int idade, String raca, boolean ehCastrado, String personalidade, Sexo sexo) {
		super();
		this.idade = idade;
		this.raca = raca;
		this.ehCastrado = ehCastrado;
		this.personalidade = personalidade;
		this.sexo = sexo;
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
		return "Cachorro [idade=" + idade + ", raca=" + raca + ", ehCastrado=" + ehCastrado + ", personalidade="
				+ personalidade + ", sexo=" + sexo + "]";
	}

	@Override
	public void imprimir() {
		System.out.println(this.toString());
		
	}
	
	
}
