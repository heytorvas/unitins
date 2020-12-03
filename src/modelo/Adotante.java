package modelo;

public class Adotante extends Pessoa implements Interface {
	private Endereco endereco;

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

	public Adotante(String nome, String cpf, Sexo sexo, String email, String senha, Telefone telefone, 
					Endereco endereco) {
		super(nome, cpf, sexo, email, senha, telefone);
		this.endereco = endereco;
	}
	
	
	@Override
	public String toString() {
		return "Adotante ["
				+ "nome=" + getNome() + ", cpf=" + getCpf() + ", sexo=" + getSexo() + ", email=" + getEmail() + 
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
