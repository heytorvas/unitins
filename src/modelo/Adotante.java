package modelo;

public class Adotante extends Pessoa implements Interface {
	private int id;
	private Endereco endereco;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Endereco getEndereco() {
		if (endereco == null)
			endereco = new Endereco();
		
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Adotante() {
	}

	public Adotante(int id, String nome, String cpf, Sexo sexo, String email, String senha, Telefone telefone, 
					Endereco endereco) {
		super(nome, cpf, sexo, email, senha, telefone);
		this.id = id;
		this.endereco = endereco;
	}
	
	
	@Override
	public String toString() {
		return "Adotante ["
				+ "id=" + id + ", nome=" + getNome() + ", cpf=" + getCpf() + ", sexo=" + getSexo() + ", email=" + getEmail() + 
				", senha=" + getSenha() + ", codigoArea=" + getTelefone().getCodigoArea() + 
				", numero=" + getTelefone().getNumero() + ", rua=" + endereco.getRua() +
				", numero=" + endereco.getNumero() + ", bairro=" + endereco.getBairro() +
				", cidade=" + endereco.getCidade() + "]";
	}

	@Override
	public void imprimir() {
		System.out.println(this.toString());
	}

}
