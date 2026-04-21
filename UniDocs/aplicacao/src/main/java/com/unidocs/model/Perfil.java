package com.unidocs.model;

public enum Perfil {
	
	ADMINISTRADOR(1, "Administrador"), 
	ALUNO(2, "Aluno"), 
	PROFESSOR(3, "Professor");
	
	private int value;
	private String label;
	
	Perfil(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	public static Perfil valueOf(int value) {
		
		for (Perfil perfil : Perfil.values()) {
			
			if (perfil.getValue() == value) 
				return perfil;
		}
		
		return null;
	}
}