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
import br.unitins.cremapet.dao.UsuarioDAO;
import br.unitins.cremapet.model.Cliente;
import br.unitins.cremapet.model.Endereco;
import br.unitins.cremapet.model.Estado;
import br.unitins.cremapet.model.Perfil;
import br.unitins.cremapet.model.Sexo;
import br.unitins.cremapet.model.Usuario;

@Named
@ViewScoped
public class UsuarioController implements Serializable {
	
	private static final long serialVersionUID = -6998638931332554108L;

	private Usuario usuario;
	
	private String nome;
	
	private List<Usuario> listaUsuario;
	
	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) {
			DAO<Usuario> dao = new UsuarioDAO();
			listaUsuario = dao.findAll();
			if (listaUsuario == null)
				listaUsuario = new ArrayList<Usuario>();
		} 
		return listaUsuario;
	}
	
	public List<Usuario> listaUsuarioPesquisa() {
		if (listaUsuario == null) {
			UsuarioDAO dao = new UsuarioDAO();
			listaUsuario = dao.findByNome(getNome());
			if (listaUsuario == null) {
				listaUsuario = new ArrayList<Usuario>();
			}
			dao.closeConnection();
		}
		return listaUsuario;
	}

	public void incluir() {
		if (validarDados()) {
			DAO<Usuario> dao = new UsuarioDAO();
			// faz a inclusao no banco de dados
			try {
//				String hashSenha = Util.hashSHA256(getUsuario().getSenha());
//				getUsuario().setSenha(hashSenha);
				
				getUsuario().setSenha(Util.hashSHA256(getUsuario().getSenha()));
				
				dao.create(getUsuario());
				dao.getConnection().commit();
				Util.addMessageInfo("Inclus�o realizada com sucesso.");
				limpar();
				listaUsuario = null;
			} catch (SQLException e) {
				dao.rollbackConnection();
				dao.closeConnection();
				Util.addMessageInfo("Erro ao incluir o Usu�rio no Banco de Dados.");
				e.printStackTrace();
			}
		}
		Util.redirect("usuario.xhtml");
	}
	
	public void alterar() {
		if (validarDados()) {
			DAO<Usuario> dao = new UsuarioDAO();
			// faz a alteracao no banco de dados
			try {
				// gerando um hash da senha
				getUsuario().setSenha(Util.hashSHA256(getUsuario().getSenha()));
				dao.update(getUsuario());
				dao.getConnection().commit();
				Util.addMessageInfo("Altera��o realizada com sucesso.");
				limpar();
				listaUsuario = null;
			} catch (SQLException e) {
				dao.rollbackConnection();
				dao.closeConnection();
				Util.addMessageInfo("Erro ao alterar o Usu�rio no Banco de Dados.");
				e.printStackTrace();
			}
				
		}
		Util.redirect("usuario.xhtml");
	}
	
	public void excluir() {
		if (excluir(getUsuario()))
			limpar();
		Util.redirect("usuario.xhtml");
	}
	
	public boolean excluir(Usuario usuario) {
		DAO<Usuario> dao = new UsuarioDAO();
		// faz a exclusao no banco de dados
		try {
			dao.delete(getUsuario().getId());
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
//		if (getUsuario().getSenha().isBlank()) {
//			Util.addMessageWarn("O campo senha deve ser informado.");
//			return false;
//		}
		if (getUsuario().getSenha() == null || 
				getUsuario().getSenha().trim().equals("") ) {
			Util.addMessageError("O campo senha deve ser informado.");
			return false;
		}
		return true;
	}
	
	private int ultimoId() {
		int maior = 0;
		for (Usuario usuario : listaUsuario) {
			if (usuario.getId() > maior)
				maior = usuario.getId();
		}
		return maior;
	}
	
	public void editar(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO();
		// buscando um usuario pelo id
		Usuario usu = dao.findId(usuario.getId());
		setUsuario(usu);
//		setUsuario(dao.findId(usuario.getId()));
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setEndereco(new Endereco());
//			usuario.setNascimento(new Nascimento());
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void limpar() {
		usuario = null;
	}
	
	public void pesquisar() {
		listaUsuario = null;
	}
	
	public Perfil[] getListaPerfil() {
		return Perfil.values();
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
