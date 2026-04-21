package modelo;

public class Funcionario extends Pessoa implements Interface
{
    // ATRIBUTOS
    private int idFuncionario;
    private double salarioFuncionario;

    // GETTERS E SETTERS
    public int getIdFuncionario()
    {
        return idFuncionario;
    }
    public void setIdFuncionario(int idFuncionario)
    {
        this.idFuncionario = idFuncionario;
    }
    public double getSalarioFuncionario()
    {
        return salarioFuncionario;
    }
    public void setSalarioFuncionario(double salarioFuncionario)
    {
        this.salarioFuncionario = salarioFuncionario;
    }

    // CONSTRUTORES
    public Funcionario()
    {
    }
    public Funcionario(String nome, String cpf, String email, Sexo sexo, int idFuncionario,
                       double salarioFuncionario, String username, String password) {
        super(nome, cpf, email, sexo, username, password);
        this.idFuncionario = idFuncionario;
        this.salarioFuncionario = salarioFuncionario;
    }

    // METODOS SOBRESCRITOS
    @Override
    public String toString()
    {
        return "FUNCIONARIO: " +
                "ID: " + idFuncionario +
                ", NOME: " + getNome() +
                ", EMAIL: " + getEmail() +
                ", SALARIO: " + salarioFuncionario;
    }
    @Override
    public void imprimir()
    {
        System.out.println(this.toString());
    }
}