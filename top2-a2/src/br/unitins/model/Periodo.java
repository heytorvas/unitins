package br.unitins.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Periodo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String numero;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "periodo_disciplina", joinColumns = { @JoinColumn(name = "id_periodo") }, inverseJoinColumns = {
			@JoinColumn(name = "id_disciplina") })
	private List<Disciplina> disciplinas;

	public Periodo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
}
