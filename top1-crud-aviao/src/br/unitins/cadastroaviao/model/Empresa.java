package br.unitins.cadastroaviao.model;

import java.util.ArrayList;
import java.util.List;

public class Empresa extends DefaultEntity {
	private String nome;
	private int anoFundacao;
	private Avioes avioes;
	private String sede;
	private List<Avioes> listaAvioes;
	
	public Empresa() {
	}
	
	public Empresa(Integer id, String nome, int anoFundacao, String sede) {
		super();
		setId(id);
		this.nome = nome;
		this.anoFundacao = anoFundacao;
		this.sede = sede;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getAnoFundacao() {
		return anoFundacao;
	}
	public void setAnoFundacao(int anoFundacao) {
		this.anoFundacao = anoFundacao;
	}
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}

	public List<Avioes> getListaAvioes() {
		if (listaAvioes == null) {
			listaAvioes = new ArrayList<Avioes>();
		}
		return listaAvioes;
	}
	public void setListaAvioes(List<Avioes> listaAvioes) {
		this.listaAvioes = listaAvioes;
	}
	public Avioes getAvioes(){
		if (avioes == null) {
			avioes = new Avioes();
		}
		return avioes;
	}
	public void setAvioes(Avioes avioes) {
		this.avioes = avioes;
	}
	public void incluirAviao() {
		getListaAvioes().add(getAvioes());
		limparAviao();
	}
	public void editarAviao(Avioes avioes) {
		setAvioes((Avioes)avioes.getClone());
	}
	public void alterarAviao() {
		int index = getListaAvioes().indexOf(getAvioes());
		if (index != -1) {
			getListaAvioes().set(index, getAvioes());
			limparAviao();
		}
	}
	public void excluirAviao() {
		int index = getListaAvioes().indexOf(getAvioes());
		getListaAvioes().remove(index);
		if (index != -1) {
			limparAviao();
		}
	}
	public void limparAviao() {
		avioes = null;
	}
}