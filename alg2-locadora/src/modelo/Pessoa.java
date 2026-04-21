package modelo;

abstract public class Pessoa implements Interface
{
	// ATRIBUTOS
	private String nome;
	private String cpf;
	private String email;
	private Sexo sexo;
	private String username;
	private String password;

	// CONSTRUTORES
	public Pessoa()
	{
	}
	public Pessoa(String nome, String cpf, String email, Sexo sexo, String username, String passowrd)
	{
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.sexo = sexo;
		this.username = username;
		this.password = passowrd;
	}

	// GETTERS E SETTERS
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public String getCpf()
	{
		return cpf;
	}
	public void setCpf(String cpf)
	{
		this.cpf = cpf;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public Sexo getSexo()
	{
		return sexo;
	}
	public void setSexo(Sexo sexo)
	{
		this.sexo = sexo;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

	// METODOS SOBRESCRITOS
	@Override
	public String toString()
	{
		return "Pessoa{" +
				", nome='" + nome + '\'' +
				", cpf='" + cpf + '\'' +
				", email='" + email + '\'' +
				", sexo=" + sexo +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
	@Override
	public void imprimir()
	{
		System.out.println(this.toString());
	}
}