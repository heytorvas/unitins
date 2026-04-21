package br.unitins.cremapet.model;

import java.time.LocalDate;
import java.util.List;

public class Venda {
	
	private Integer id;
	private LocalDate data;
	private Cliente cliente;
	private Usuario usuario;
	private Pet pet;
	private List<ItemVenda> listaItemVenda;
	
	private Double totalVenda;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Cliente getCliente() {
		if (cliente == null)
			cliente = new Cliente();
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<ItemVenda> getListaItemVenda() {
		return listaItemVenda;
	}
	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		this.listaItemVenda = listaItemVenda;
	}
	
	public Double getTotalVenda() {
		totalVenda = 0.0;
		if (listaItemVenda != null)
			for (ItemVenda itemVenda : listaItemVenda) 
				totalVenda += itemVenda.getValor();
		
		return totalVenda;
	}
	
	public Pet getPet() {
		if (pet == null) {
			pet = new Pet();
		}
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}
}
