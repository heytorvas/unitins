package br.unitins.cadastroaviao.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cadastroaviao.model.Avioes;
import br.unitins.cadastroaviao.model.Empresa;
import br.unitins.cadastroaviao.model.Marca;

@Named
@ViewScoped
public class EmpresaController implements Serializable{
	private static final long serialVersionUID = -8172260296010703261L;
	
	private Empresa empresa;
	private List<Empresa> listaEmpresa;
	
	public List<Empresa> getListaEmpresa() {
		if (listaEmpresa == null) {
			listaEmpresa = new ArrayList<Empresa>();
			listaEmpresa.add(new Empresa(1, "LATAM", 1927, "Guarulhos"));
			listaEmpresa.add(new Empresa(2, "GOL", 1928, "Congonhas"));
			listaEmpresa.add(new Empresa(3, "AZUL", 1929, "Viracopos"));
		}
		return listaEmpresa;
	}
	public Empresa getEmpresa() {
		if (empresa == null) {
			empresa = new Empresa();
		}
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public void editar(Empresa empresa) {
		setEmpresa((Empresa)empresa.getClone());
	}
	public void incluir() {
		getListaEmpresa().add(getEmpresa());
		limpar();
	}
	public void alterar() {
		int index = getListaEmpresa().indexOf(getEmpresa());
		if (index != -1) {
			getListaEmpresa().set(index, getEmpresa());
			limpar();
		}
	}
	public void excluir() {
		int index = getListaEmpresa().indexOf(getEmpresa());
		getListaEmpresa().remove(index);
		if (index != -1) {
			limpar();
		}
	}
	public void limpar() {
		empresa = null;
	}
}
