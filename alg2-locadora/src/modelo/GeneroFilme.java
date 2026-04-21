package modelo;

public enum GeneroFilme
{
    ACAO(0, "Acao"), ANIMACAO(1, "Animacao"), COMEDIA(2, "Comedia"),
    DRAMA(3, "Drama"), DOCUMENTARIO(4, "Documentario"), FICCAO_CIENTIFICA(5, "Ficcao Cientifica"),
    RELIGIAO(6,"Religiao"), ROMANCE(7, "Romance"), SUSPENSE(8, "Suspense"), TERROR(9, "Terror");

    // ATRIBUTOS
    private int id;
    private String nome;

    // CONSTRUTOR
    private GeneroFilme(int id, String nome)
    {
        this.id = id;
        this.nome = nome;
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
}