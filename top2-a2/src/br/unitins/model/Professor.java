package br.unitins.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Professor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String nome;

	@Column
	private String email;

	@Column
	private String registro;

	public Professor() {
		super();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

}
