package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import dao.TesteDAO;
import model.Teste;

@Named
@ViewScoped
public class TesteController implements Serializable{

	private static final long serialVersionUID = 6923288024865371500L;
	
	private Teste teste;
	
	private List<Teste> listaTeste = null;
	
	public List<Teste> getListaTeste(){
		if (listaTeste == null) {
			TesteDAO dao = new TesteDAO();
			listaTeste = dao.findAll();
			if (listaTeste == null)
				listaTeste = new ArrayList<Teste>();
			dao.closeConnection();
		}
		
		return listaTeste;
	}

	public Teste getTeste() {
		if (teste == null) {
			teste = new Teste();
		}
		return teste;
	}

	public void setTeste(Teste teste) {
		this.teste = teste;
	}
}
