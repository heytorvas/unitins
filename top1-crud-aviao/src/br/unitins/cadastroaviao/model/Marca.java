package br.unitins.cadastroaviao.model;

public enum Marca {
	AIRBUS(1, "Airbus"),
	BOEING(2, "Boeing"),
	EMBRAER(3, "Embraer"),
	BOMBARDIER(4, "Bombardier");
	
	private int value;
	private String label;
	
	public int getValue() {
		return value;
	}
	public String getLabel() {
		return label;
	}

	Marca(int value, String label) {
		this.value = value;
		this.label = label;
	}
}
