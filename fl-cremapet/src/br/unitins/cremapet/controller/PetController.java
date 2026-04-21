package br.unitins.cremapet.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cremapet.application.Util;
import br.unitins.cremapet.dao.ClienteDAO;
import br.unitins.cremapet.dao.DAO;
import br.unitins.cremapet.dao.PetDAO;
import br.unitins.cremapet.dao.PetDAO;
import br.unitins.cremapet.model.Sexo;
import br.unitins.cremapet.model.Pet;
import br.unitins.cremapet.model.Cliente;
import br.unitins.cremapet.model.Pet;

@Named
@ViewScoped
public class PetController implements Serializable {

	private static final long serialVersionUID = -1856585121449134148L;

	private Pet pet;
	
	private String nome;

	private List<Pet> listaPet;

	public List<Pet> getListaPet() {
		if (listaPet == null) {
			DAO<Pet> dao = new PetDAO();
			listaPet = dao.findAll();
			if (listaPet == null)
				listaPet = new ArrayList<Pet>();
		}
		return listaPet;
	}

	public void incluir() {
		DAO<Pet> dao = new PetDAO();
		// faz a inclusao no banco de dados
		try {
//				String hashSenha = Util.hashSHA256(getUsuario().getSenha());
//				getUsuario().setSenha(hashSenha);

			dao.create(getPet());
			dao.getConnection().commit();
			Util.addMessageInfo("Inclus�o realizada com sucesso.");
			limpar();
			listaPet = null;
		} catch (SQLException e) {
			dao.rollbackConnection();
			dao.closeConnection();
			Util.addMessageInfo("Erro ao incluir o Usu�rio no Banco de Dados.");
			e.printStackTrace();
		}
		Util.redirect("pet.xhtml");

	}

	public void alterar() {
		DAO<Pet> dao = new PetDAO();
		// faz a alteracao no banco de dados
		try {
			dao.update(getPet());
			dao.getConnection().commit();
			Util.addMessageInfo("Altera��o realizada com sucesso.");
			limpar();
			listaPet = null;
		} catch (SQLException e) {
			dao.rollbackConnection();
			dao.closeConnection();
			Util.addMessageInfo("Erro ao alterar o Usu�rio no Banco de Dados.");
			e.printStackTrace();
		}
		Util.redirect("pet.xhtml");
	}

	public void excluir() {
		if (excluir(getPet()))
			limpar();
		Util.redirect("pet.xhtml");
	}

	public boolean excluir(Pet usuario) {
		DAO<Pet> dao = new PetDAO();
		// faz a exclusao no banco de dados
		try {
			dao.delete(getPet().getId());
			dao.getConnection().commit();
			Util.addMessageInfo("Exclusão realizada com sucesso.");
			return true;
		} catch (SQLException e) {
			dao.rollbackConnection();
			Util.addMessageInfo("Erro ao excluir o Produto no Banco de Dados.");
			e.printStackTrace();
			return false;
		} finally {
			dao.closeConnection();
		}
	}

	private int ultimoId() {
		int maior = 0;
		for (Pet usuario : listaPet) {
			if (usuario.getId() > maior)
				maior = usuario.getId();
		}
		return maior;
	}

	public void editar(Pet pet) {
		PetDAO dao = new PetDAO();
		// buscando um usuario pelo id
		Pet p = dao.findId(pet.getId());
		setPet(p);
//		setUsuario(dao.findId(usuario.getId()));
	}
	
	public List<Pet> listaPetPesquisa() {
		if (listaPet == null) {
			PetDAO dao = new PetDAO();
			listaPet = dao.findByNome(getNome());
			if (listaPet == null) {
				listaPet = new ArrayList<Pet>();
			}
			dao.closeConnection();
		}
		return listaPet;
	}

	public Pet getPet() {
		if (pet == null) {
			pet = new Pet();
//			usuario.setNascimento(new Nascimento());
		}
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public void limpar() {
		pet = null;
	}
	
	public void pesquisar() {
		listaPet = null;
	}

	public Sexo[] getListaSexo() {
		return Sexo.values();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
