package br.unitins.cremapet.model;

public class Endereco {
	
	private Integer id;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private Estado estados;
	
	public Endereco() {
		
	}
	
	public Endereco(Integer id, String rua, String numero, String bairro, String cidade, Estado estados) {
		super();
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estados = estados;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Estado getEstados() {
		return estados;
	}
	public void setEstados(Estado estados) {
		this.estados = estados;
	}

}
