package br.unitins.cremapet.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cremapet.application.Session;
import br.unitins.cremapet.application.Util;
import br.unitins.cremapet.dao.ClienteDAO;
import br.unitins.cremapet.dao.DAO;
import br.unitins.cremapet.dao.PetDAO;
import br.unitins.cremapet.dao.ServicoDAO;
import br.unitins.cremapet.dao.VendaDAO;
import br.unitins.cremapet.model.Cliente;
import br.unitins.cremapet.model.ItemVenda;
import br.unitins.cremapet.model.Pet;
import br.unitins.cremapet.model.Servico;
import br.unitins.cremapet.model.Usuario;
import br.unitins.cremapet.model.Venda;

@Named
@ViewScoped
public class CarrinhoController implements Serializable {

	private static final long serialVersionUID = -1229885960428245332L;

	private Venda venda;
	
	private List<Cliente> listaCliente;
	
	private List<Pet> listaPet;
	
	public List<Pet> getlistaPet() {
		if (listaPet == null) {
			DAO<Pet> dao = new PetDAO();
			listaPet = dao.findAll();
			if (listaPet == null)
				listaPet = new ArrayList<Pet>();
		}
		return listaPet;
	}
	
	public List<Cliente> getlistaCliente() {
		if (listaCliente == null) {
			DAO<Cliente> dao = new ClienteDAO();
			listaCliente = dao.findAll();
			if (listaCliente == null)
				listaCliente = new ArrayList<Cliente>();
		}
		return listaCliente;
	}

	public void adicionar(int id) {
		// pesquisa o servico selecionado
		ServicoDAO dao = new ServicoDAO();
		Servico servico = dao.findById(id);

		// verifica se existe o carrinho na sessao
		if (Session.getInstance().getAttribute("carrinho") == null) {
			// adiciona o carrinho na sessao
			Session.getInstance().setAttribute("carrinho", new ArrayList<ItemVenda>());
		}

		// busca o carrinho da sessao
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().getAttribute("carrinho");

		// cria um item de venda
		ItemVenda item = new ItemVenda();
		item.setServico(servico);
		item.setValor(servico.getValor());

		// adiciona o item no objeto de referencia do carrinho
		carrinho.add(item);

		// atualiza o carrinho
		Session.getInstance().setAttribute("carrinho", carrinho);

		Util.addMessageError("Adicionado com Sucesso! ");

	}

	public Venda getVenda() {
		if (venda == null)
			venda = new Venda();

		// obtendo o carrinho da sessao
		List<ItemVenda> carrinho = (ArrayList<ItemVenda>) Session.getInstance().getAttribute("carrinho");

		// adicionando os itens do carrinho na venda
		if (carrinho == null)
			carrinho = new ArrayList<ItemVenda>();

		venda.setListaItemVenda(carrinho);
		return venda;
	}

	public void remover(String descricao) {
		// obtendo o carrinho da sessao
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().getAttribute("carrinho");

		// removendo o item do carrinho
		for (ItemVenda itemVenda : carrinho) {

			if (itemVenda.getServico().getDescricao().equals(descricao)) {
				carrinho.remove(itemVenda);
				return;
			}

		}
	}

	public void finalizar() {
		// Definindo o usuario que está logado com o cliente
		Usuario user = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
		if (user == null) {
			Util.addMessageWarn("Eh preciso estar logado para finalizar a venda!");
			return;
		}
		getVenda().setUsuario(user);

		getVenda().setCliente(getVenda().getCliente());
		
		getVenda().setPet(getVenda().getPet());
		
		VendaDAO dao = new VendaDAO();
		try {
			dao.create(getVenda());
			dao.getConnection().commit();
			limpar();
		} catch (SQLException e) {
			dao.rollbackConnection();
			dao.closeConnection();
			e.printStackTrace();
		}

		// Finalizando venda e limpando o carrinho
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().getAttribute("carrinho");
		carrinho.clear();
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public void limpar() {
		venda = null;
	}

}
