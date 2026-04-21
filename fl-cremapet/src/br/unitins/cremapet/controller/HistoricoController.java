package br.unitins.cremapet.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cremapet.application.Session;
import br.unitins.cremapet.application.Util;
import br.unitins.cremapet.dao.VendaDAO;
import br.unitins.cremapet.model.Cliente;
import br.unitins.cremapet.model.Usuario;
import br.unitins.cremapet.model.Venda;

@Named
@ViewScoped
public class HistoricoController implements Serializable {

	private static final long serialVersionUID = -5445190076521270523L;

	private List<Venda> listaVenda;

	private Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Venda> getListaVenda() {
		if (listaVenda == null) {
			Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
			if (usuario == null) {
				listaVenda = new ArrayList<Venda>();
				return listaVenda;
			}
			VendaDAO dao = new VendaDAO();
			// buscando todas as vendas do usuario logado
			listaVenda = dao.findHistorico(usuario);
			// se o retorno da consulta for nula ... inicializar a lista para evitar erro de
			// nullpointer
			if (listaVenda == null)
				listaVenda = new ArrayList<Venda>();
		}
		return listaVenda;
	}

	public void detalhes(Venda venda) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("vendaFlash", venda);
		Util.redirect("detalhesvenda.xhtml");
	}

}
