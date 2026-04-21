package br.unitins.cremapet.model;

public class Pet implements Cloneable{
	
	private Integer id;
	private Sexo sexo;
	private String nome;
	private String animal;
	private String raca;
	
	public Pet() {
		
	}
	
	public Pet(Integer id, Sexo sexo, String nome, String animal, String raca) {
		super();
		this.id = id;
		this.sexo = sexo;
		this.nome = nome;
		this.animal = animal;
		this.raca = raca;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	@Override
	public Pet clone() {
		try {
			return (Pet) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			System.out.println("Erro ao clonar.");
		}
		return null;
	}
}
