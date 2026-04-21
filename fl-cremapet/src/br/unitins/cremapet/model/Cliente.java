package br.unitins.cremapet.model;

public class Cliente extends Pessoa implements Cloneable {
	
	@Override
	public Cliente clone() {
		try {
			return (Cliente) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.out.println("Erro ao clonar.");
		}
		return null;
	}

}
