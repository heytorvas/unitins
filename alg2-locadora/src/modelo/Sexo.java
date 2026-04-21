package modelo;

public enum Sexo {
    MASCULINO(0, "Masculino"), FEMININO(1, "Feminino");

    // atributos
    private int id;
    private String label;

    // construtor
    private Sexo(int id, String label)
    {
        this.id = id;
        this.label = label;
    }

    // getters
    public int getId()
    {
        return id;
    }
    public String getLabel()
    {
        return label;
    }
}
