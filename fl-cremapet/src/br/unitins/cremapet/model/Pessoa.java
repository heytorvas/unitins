package br.unitins.cremapet.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public abstract class Pessoa {
	
	private Integer id;

	@NotEmpty(message = "O campo nome n�o pode ser vazio")
	@Size(max = 60, message = "O campo nome deve conter no maximo 60 caracteres")
	private String nome;
	
	private String cpf;

	@Email
	private String login;

	@Size(min = 6, max = 30, message = "A senha deve conter entre 6 e 30 caracteres")
	private String senha;

	private Sexo sexo;
	
	private Endereco endereco;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	public Pessoa() {
		
	}

	public Pessoa(Integer id,
			@NotEmpty(message = "O campo nome n�o pode ser vazio") @Size(max = 60, message = "O campo nome deve conter no maximo 60 caracteres") String nome,
			String cpf, @Email String login,
			@Size(min = 6, max = 30, message = "A senha deve conter entre 6 e 30 caracteres") String senha, Sexo sexo,
			Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.login = login;
		this.senha = senha;
		this.sexo = sexo;
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
