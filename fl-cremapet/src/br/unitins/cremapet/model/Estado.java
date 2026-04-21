package br.unitins.cremapet.model;

public enum Estado {

	ACRE(1, "Acre", "AC"), ALAGOAS(2, "Alagoas", "AL"), AMAPA(3, "Amap�", "AP"), AMAZONAS(4, "Amazonas", "AM"),
	BAHIA(5, "Bahia", "BA"), CEARA(6, "Cear�", "CE"), DISTRITOFEDERAL(7, "Distrito Federal", "DF"),
	ESPIRITOSANTO(8, "Esp�rito Santo", "ES"), GOIAS(9, "Goi�s", "GO"), MARANHAO(10, "Maranh�o", "MA"),
	MATOGROSSO(11, "Mato Grosso", "MT"), MATOGROSSODOSUL(12, "Mato Grosso do Sul", "MS"),
	MINASGERAIS(13, "Minas Gerais", "MG"), PARA(14, "Par�", "PA"), PARAIBA(15, "Para�ba", "PB"),
	PARANA(16, "Paran�", "PR"), PERNAMBUCO(17, "Pernambuco", "PE"), PIAUI(18, "Piau�", "PI"),
	RIODEJANEIRO(19, "Rio de Janeiro", "RJ"), RIOGRANDEDONORTE(20, "Rio Grande do Norte", "RN"),
	RIOGRANDEDOSUL(21, "Rio Grande do Sul", "RS"), RONDONIA(22, "Rond�nia", "RO"), RORAIMA(23, "Roraima", "RR"),
	SANTACATARINA(24, "Santa Catarina", "SC"), SAOPAULO(25, "S�o Paulo", "SP"), SERGIPE(26, "Sergipe", "SE"),
	TOCANTINS(27, "Tocantins", "TO");

	private int value;
	private String nome;
	private String sigla;

	private Estado(int value, String nome, String sigla) {
		this.value = value;
		this.nome = nome;
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public String getSigla() {
		return sigla;
	}

	public int getValue() {
		return value;
	}

	public static Estado valueOf(int value) {

		for (Estado estados : Estado.values()) {
			if (estados.getValue() == value)
				return estados;
		}
		return null;
	}
}
