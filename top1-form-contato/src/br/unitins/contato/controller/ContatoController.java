package br.unitins.contato.controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

import br.unitins.contato.application.Util;
import br.unitins.contato.model.Contato;

@Named
@RequestScoped
public class ContatoController 
{
	private Contato contato;
	
	public void enviar() 
	{
		System.out.println("==================================");
		System.out.println("Nome: " + getContato().getNome());
		System.out.println("Email: " + getContato().getEmail());
		System.out.println("Mensagem: " + getContato().getMensagem());
		System.out.println("==================================");
		Util.redirect("mensagem.xhtml");
	}

	public Contato getContato()
	{
		if (contato == null) 
		{
			contato = new Contato();
		}
		return contato;
	}

	public void setContato(Contato contato) 
	{
		this.contato = contato;
	}
}