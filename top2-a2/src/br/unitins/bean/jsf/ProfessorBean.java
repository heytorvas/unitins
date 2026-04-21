package br.unitins.bean.jsf;

import br.unitins.bean.ejb.ProfessorEJB;
import br.unitins.model.Professor;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProfessorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ProfessorEJB professorEJB;

	private Professor professor;

	private List<Professor> professores;

	private Integer idPesquisa;

	@PostConstruct
	public void init() {
		findAll();
	}

	public void insert() {
		professorEJB.insert(professor);
		clean();
		findAll();
	}

	private void findAll() {
		professores = professorEJB.findAll();
	}

	public void update() {
		professorEJB.update(professor);
		clean();
		findAll();
	}

	public void delete() {
		professorEJB.delete(professorEJB.load(idPesquisa));
		clean();
		findAll();
	}

	public void pesquisar() {
		professor = professorEJB.load(idPesquisa);
	}

	public void clean() {
		professor = new Professor();
	}

	public Professor getProfessor() {
		if (professor == null) {
			professor = new Professor();
		}
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Integer getIdPesquisa() {
		return idPesquisa;
	}

	public void setIdPesquisa(Integer idPesquisa) {
		this.idPesquisa = idPesquisa;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

}
