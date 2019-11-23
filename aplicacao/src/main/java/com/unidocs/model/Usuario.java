package com.unidocs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@NotBlank(message = "O campo Nome não pode ser vazio...")
	private String nome;

	@NotBlank(message = "O campo Matricula não pode ser vazio...")
	private String matricula;

	@NotBlank(message = "O campo CPF não pode ser vazio...")
	private String cpf;

	@NotBlank(message = "O campo Email não pode ser vazio...")
	private String email;

	@NotBlank(message = "O campo Senha não pode ser vazio...")
	private String senha;

//	@NotBlank(message = "O campo Perfil não pode ser vazio...")
//	private Perfil perfil;

	public Usuario() {
		super();
	}

//	public Usuario(Integer id, String nome, String matricula, String cpf, String email, String senha, Perfil perfil) {
//		super();
//		this.id = id;
//		this.nome = nome;
//		this.matricula = matricula;
//		this.cpf = cpf;
//		this.email = email;
//		this.senha = senha;
//		this.perfil = perfil;
//	}
	
	public Usuario(Integer id, String nome, String matricula, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.matricula = matricula;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
	}

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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

//	public Perfil getPerfil() {
//		return perfil;
//	}
//
//	public void setPerfil(Perfil perfil) {
//		this.perfil = perfil;
//	}

//	@Override
//	public String toString() {
//		return "Usuario [id=" + id + ", nome=" + nome + ", matricula=" + matricula + ", cpf=" + cpf + ", email=" + email
//				+ ", senha=" + senha + ", perfil=" + perfil + "]";
//	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", matricula=" + matricula + ", cpf=" + cpf + ", email=" + email
				+ ", senha=" + senha + "]";
	}

}
