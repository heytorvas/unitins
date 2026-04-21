package br.unitins.cadastroaviao.model;

public class Avioes extends DefaultEntity{
	private Marca marca;
	private String modelo;
	
	public Avioes() {
	}
	public Avioes(Marca marca, String modelo) {
		super();
		this.marca = marca;
		this.modelo = modelo;
	}
	public Marca[] getListaMarca() {
		return Marca.values();
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
}
