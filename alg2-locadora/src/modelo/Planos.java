package modelo;

public enum Planos
{
    BASICO(0, "Basico", "3 filmes por mes", 10.90),
    PLUS(1, "Plus", "5 filmes por mes", 15.90),
    VIP(2, "Vip", "10 filmes por mes", 20.90);

    // ATRIBUTOS
    private int id;
    private String nome;
    private String descricao;
    private double preco;

    // CONSTRUTORES
    private Planos()
    {
    }
    private Planos(int id, String nome, String descricao, double preco)
    {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    // GETTERS
    public int getId()
    {
        return id;
    }
    public String getNome()
    {
        return nome;
    }
    public String getDescricao()
    {
        return descricao;
    }
    public double getPreco()
    {
        return preco;
    }
}