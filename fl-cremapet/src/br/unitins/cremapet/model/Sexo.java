package br.unitins.cremapet.model;

public enum Sexo {
	MASCULINO(1, "Masculino"), 
	FEMININO(2, "Feminino");
	
	private int value;
	private String label;
	
	Sexo(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	public static Sexo valueOf(int value) {
		
		for (Sexo sexo : Sexo.values()) {
			if (sexo.getValue() == value) 
				return sexo;
		}
		return null;
	}
}
