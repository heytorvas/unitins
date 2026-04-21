package br.unitins.cremapet.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cremapet.application.Session;
import br.unitins.cremapet.application.Util;
import br.unitins.cremapet.model.ItemVenda;
import br.unitins.cremapet.model.Usuario;

@Named
@ViewScoped
public class TemplateController implements Serializable{
	
	private static final long serialVersionUID = -8903731526145538323L;
	
	private Usuario usuarioLogado;
	
	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null) {
			// buscando o usuario da sessao
			usuarioLogado = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
			if (usuarioLogado == null)
				usuarioLogado = new Usuario();
		}
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public String encerrarSessao() {
		Session.getInstance().invalidateSession();
		return "login.xhtml?faces-redirect=true";
	}
	
	public void redirectUsuario() {
		Util.redirect("usuario.xhtml");
	}
	
	public void redirectCliente() {
		Util.redirect("cliente.xhtml");
	}
	
	public void redirectPet() {
		Util.redirect("pet.xhtml");
	}
	
	public void redirectServico() {
		Util.redirect("servico.xhtml");
	}
	
	public void redirectVenda() {
		Util.redirect("vendaservico.xhtml");
	}
	
	public void redirectCarrinho() {
		Util.redirect("carrinho.xhtml");
	}
	
	public void redirectHistorico() {
		Util.redirect("historico.xhtml");
	}
}
