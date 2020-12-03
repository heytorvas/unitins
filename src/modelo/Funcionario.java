package modelo;

public class Funcionario extends Pessoa implements Interface{
	private double salario;

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Funcionario() {
	}

	public Funcionario(String nome, String cpf, Sexo sexo, String email, String senha, Telefone telefone, double salario) {
		super(nome, cpf, sexo, email, senha, telefone);
		this.salario = salario;
	}
	
	@Override
	public String toString() {
		return "Funcionario ["
				+ "nome=" + getNome() + ", cpf=" + getCpf() + ", sexo=" + getSexo() + ", email=" + getEmail() + 
				", senha=" + getSenha() + ", codigoArea=" + getTelefone().getCodigoArea() + 
				", numero=" + getTelefone().getNumero() + ", salario=" + salario + "]";
	}

	@Override
	public void imprimir() {
		System.out.println(this.toString());
	}
	
}
