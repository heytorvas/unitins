package modelo;

public class Funcionario extends Pessoa implements Interface{
	private int id;
	private double salario;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Funcionario() {
	}

	public Funcionario(int id, String nome, String cpf, Sexo sexo, String email, String senha, Telefone telefone, double salario) {
		super(nome, cpf, sexo, email, senha, telefone);
		this.id = id;
		this.salario = salario;
	}
	
	@Override
	public String toString() {
		return "Funcionario ["
				+ "id=" + id + ", nome=" + getNome() + ", cpf=" + getCpf() + ", sexo=" + getSexo() + ", email=" + getEmail() + 
				", senha=" + getSenha() + ", codigoArea=" + getTelefone().getCodigoArea() + 
				", numero=" + getTelefone().getNumero() + ", salario=" + salario + "]";
	}

	@Override
	public void imprimir() {
		System.out.println(this.toString());
	}
	
}
