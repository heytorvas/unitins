package modelo;

public abstract class Pessoa implements Interface {
	private String nome;
	private String cpf;
	private Sexo sexo;
	private String email;
	private String senha;
	private Telefone telefone;
	
	public Pessoa(){ }
	
	public Pessoa(String nome, String cpf, Sexo sexo, String email, String senha,  Telefone telefone){
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Telefone getTelefone() {
		if (telefone == null)
			telefone = new Telefone();
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		return "Pessoa ["
				+ "nome=" + nome + ", cpf=" + cpf + ", sexo=" + sexo + ", email=" + email + 
				", senha=" + senha + ", codigoArea=" + telefone.getCodigoArea() + 
				", numero=" + telefone.getNumero() + "]";
	}

	@Override
	public void imprimir(){
		System.out.println(this.toString());
	}

}
