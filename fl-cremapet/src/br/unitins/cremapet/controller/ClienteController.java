package br.unitins.cremapet.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cremapet.application.Util;
import br.unitins.cremapet.dao.DAO;
import br.unitins.cremapet.dao.ClienteDAO;
import br.unitins.cremapet.model.Endereco;
import br.unitins.cremapet.model.Estado;
import br.unitins.cremapet.model.Sexo;
import br.unitins.cremapet.model.Cliente;

@Named
@ViewScoped
public class ClienteController implements Serializable{

	private static final long serialVersionUID = 7475079061536479147L;
	
	private Cliente cliente;
	
	private String nome;
	
	private List<Cliente> listaCliente;
	
	public List<Cliente> getListaCliente() {
		if (listaCliente == null) {
			DAO<Cliente> dao = new ClienteDAO();
			listaCliente = dao.findAll();
			if (listaCliente == null)
				listaCliente = new ArrayList<Cliente>();
		} 
		return listaCliente;
	}
	
	public List<Cliente> listaClientePesquisa() {
		if (listaCliente == null) {
			ClienteDAO dao = new ClienteDAO();
			listaCliente = dao.findByNome(getNome());
			if (listaCliente == null) {
				listaCliente = new ArrayList<Cliente>();
			}
			dao.closeConnection();
		}
		return listaCliente;
	}

	public void incluir() {
		if (validarDados()) {
			DAO<Cliente> dao = new ClienteDAO();
			// faz a inclusao no banco de dados
			try {
//				String hashSenha = Util.hashSHA256(getUsuario().getSenha());
//				getUsuario().setSenha(hashSenha);
				
				dao.create(getCliente());
				dao.getConnection().commit();
				Util.addMessageInfo("Inclus�o realizada com sucesso.");
				limpar();
				listaCliente = null;
			} catch (SQLException e) {
				dao.rollbackConnection();
				dao.closeConnection();
				Util.addMessageInfo("Erro ao incluir o Usu�rio no Banco de Dados.");
				e.printStackTrace();
			}
		}
		Util.redirect("cliente.xhtml");
	}
	
	public void alterar() {
		if (validarDados()) {
			DAO<Cliente> dao = new ClienteDAO();
			// faz a alteracao no banco de dados
			try {
//				// gerando um hash da senha
//				getCliente().setSenha(Util.hashSHA256(getCliente().getSenha()));
				dao.update(getCliente());
				dao.getConnection().commit();
				Util.addMessageInfo("Altera��o realizada com sucesso.");
				limpar();
				listaCliente = null;
			} catch (SQLException e) {
				dao.rollbackConnection();
				dao.closeConnection();
				Util.addMessageInfo("Erro ao alterar o Usu�rio no Banco de Dados.");
				e.printStackTrace();
			}
				
		}
		Util.redirect("cliente.xhtml");
	}
	
	public void excluir() {
		if (excluir(getCliente()))
			limpar();
		Util.redirect("cliente.xhtml");
	}
	
	public boolean excluir(Cliente cliente) {
		DAO<Cliente> dao = new ClienteDAO();
		// faz a exclusao no banco de dados
		try {
			dao.delete(getCliente().getId());
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

	private boolean validarDados() {
////		if (getUsuario().getSenha().isBlank()) {
////			Util.addMessageWarn("O campo senha deve ser informado.");
////			return false;
////		}
//		if (getCliente().getSenha() == null || 
//				getCliente().getSenha().trim().equals("") ) {
//			Util.addMessageError("O campo senha deve ser informado.");
//			return false;
//		}
		return true;
	}
	
	private int ultimoId() {
		int maior = 0;
		for (Cliente cliente : listaCliente) {
			if (cliente.getId() > maior)
				maior = cliente.getId();
		}
		return maior;
	}
	
	public void editar(Cliente cliente) {
		ClienteDAO dao = new ClienteDAO();
		// buscando um usuario pelo id
		Cliente cli = dao.findId(cliente.getId());
		setCliente(cli);
//		setUsuario(dao.findId(usuario.getId()));
	}
	
	public void pesquisar() {
		listaCliente = null;
	}

	public Cliente getCliente() {
		if (cliente == null) {
			cliente = new Cliente();
			cliente.setEndereco(new Endereco());
//			usuario.setNascimento(new Nascimento());
		}
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void limpar() {
		cliente = null;
	}
	
	public Sexo[] getListaSexo() {
		return Sexo.values();
	}
	
	public Estado[] getListaEstado() {
		return Estado.values();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
