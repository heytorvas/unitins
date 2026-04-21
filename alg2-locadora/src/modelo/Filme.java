package modelo;

// IMPORTACEOES
import java.util.List;
import java.util.Scanner;

public class Filme implements Interface
{
    // ATRIBUTOS
    private Integer idFilme;
    private int duracaoFilme;
    private int anoLancamento;
    private String nomeFilme;
    private String diretorFilme;
    private GeneroFilme generoFilme;

    // CONSTRUTORES
    public Filme()
    {
    }
    public Filme(int idFilme, String nomeFilme, GeneroFilme generoFilme, String diretorFilme, int anoLancamento,
                 int duracaoFilme)
    {
        this.idFilme = idFilme;
        this.duracaoFilme = duracaoFilme;
        this.anoLancamento = anoLancamento;
        this.nomeFilme = nomeFilme;
        this.diretorFilme = diretorFilme;
        this.generoFilme = generoFilme;
    }

    // GETTERS E SETTERS
    public Integer getIdFilme()
    {
        return idFilme;
    }
    public void setIdFilme(Integer idFilme)
    {
        this.idFilme = idFilme;
    }
    public int getDuracaoFilme()
    {
        return duracaoFilme;
    }
    public void setDuracaoFilme(int duracaoFilme)
    {
        this.duracaoFilme = duracaoFilme;
    }
    public int getAnoLancamento()
    {
        return anoLancamento;
    }
    public void setAnoLancamento(int anoLancamento)
    {
        this.anoLancamento = anoLancamento;
    }
    public String getNomeFilme()
    {
        return nomeFilme;
    }
    public void setNomeFilme(String nomeFilme)
    {
        this.nomeFilme = nomeFilme;
    }
    public String getDiretorFilme()
    {
        return diretorFilme;
    }
    public void setDiretorFilme(String diretorFilme)
    {
        this.diretorFilme = diretorFilme;
    }
    public GeneroFilme getGeneroFilme()
    {
        return generoFilme;
    }
    public void setGeneroFilme(GeneroFilme generoFilme)
    {
        this.generoFilme = generoFilme;
    }

    // METODOS SOBRESCRITO
    @Override
    public String toString()
    {
        return "ID: " + idFilme + ", NOME: " + nomeFilme + ", GENERO: " + generoFilme
                + ", DIRETOR: " + diretorFilme + ", ANO DE LANCAMENTO: " + anoLancamento + ", DURACAO: "
                + duracaoFilme;
    }
    @Override
    public void imprimir()
    {
        System.out.println(this.toString());
    }

    // METODOS
    public static Filme cadastrarFilme()
    {
        Filme filme = new Filme();
        Scanner sc = new Scanner(System.in);
        System.out.println("INFORME O ID DO FILME: ");
        filme.setIdFilme(sc.nextInt());
        sc.nextLine(); //gambiarra
        System.out.println("INFORME O NOME DO FILME: ");
        filme.setNomeFilme(sc.nextLine());
        System.out.println("INFORME O GENERO DO FILME: ");
        System.out.println("\t(0) - ACAO;");
        System.out.println("\t(1) - ANIMACAO;");
        System.out.println("\t(2) - COMEDIA;");
        System.out.println("\t(3) - DRAMA;");
        System.out.println("\t(4) - DOCUMENTARIO;");
        System.out.println("\t(5) - FICCAO CIENTIFICA;");
        System.out.println("\t(6) - RELIGIAO;");
        System.out.println("\t(7) - ROMANCE;");
        System.out.println("\t(8) - SUSPENSE;");
        System.out.println("\t(9) - TERROR;");
        int escolha = sc.nextInt();
        sc.nextLine(); // gambiarra
        switch (escolha)
        {
            case 0:
                filme.setGeneroFilme(GeneroFilme.ACAO);
                break;
            case 1:
                filme.setGeneroFilme(GeneroFilme.ANIMACAO);
                break;
            case 2:
                filme.setGeneroFilme(GeneroFilme.COMEDIA);
                break;
            case 3:
                filme.setGeneroFilme(GeneroFilme.DRAMA);
                break;
            case 4:
                filme.setGeneroFilme(GeneroFilme.DOCUMENTARIO);
                break;
            case 5:
                filme.setGeneroFilme(GeneroFilme.FICCAO_CIENTIFICA);
                break;
            case 6:
                filme.setGeneroFilme(GeneroFilme.RELIGIAO);
                break;
            case 7:
                filme.setGeneroFilme(GeneroFilme.ROMANCE);
                break;
            case 8:
                filme.setGeneroFilme(GeneroFilme.SUSPENSE);
                break;
            case 9:
                filme.setGeneroFilme(GeneroFilme.TERROR);
                break;
            default:
                System.out.println("USER BURRO");
                break;
        }
        System.out.println("INFORME O DIRETOR DO FILME: ");
        filme.setDiretorFilme(sc.nextLine());
        System.out.println("INFORME O ANO DE LANCAMENTO DO FILME: ");
        filme.setAnoLancamento(sc.nextInt());
        sc.nextLine(); //gambiarra
        System.out.println("INFORME A DURACAO DO FILME: ");
        filme.setDuracaoFilme(sc.nextInt());
        sc.nextLine();
        return filme;
    }
    public static void consultarFilme(List<Filme> listaFilme)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("ESCOLHA O TIPO DE CONSULTA: ");
        System.out.println("\t(1) - LISTAR TODOS OS FILMES POR ID;");
        System.out.println("\t(2) - LISTAR OS FILMES POR ORDEM ALFABETICA;");
        System.out.println("\t(3) - LISTAR OS FILMES POR GENERO ESCOLHIDO;");
        System.out.println("\t(4) - LISTAR OS FILMES POR DIRETOR;");
        System.out.println("\t(5) - LISTAR OS FILMES POR ANO DE LANCAMENTO;");
        int choice = sc.nextInt();
        sc.nextLine(); //gambiarra
        switch (choice)
        {
            case 1:
                imprimirLista(listaFilme);
                System.out.println("\nCONSULTA REALIZADA COM SUCESSO!");
                break;
            case 2:
                imprimirListaOrdenadaNomeFilme(listaFilme);
                System.out.println("\nCONSULTA REALIZADA COM SUCESSO!");
                break;
            case 3:
                System.out.println("ESCOLHA O GENERO DO FILME: ");
                System.out.println("\t(0) - ACAO;");
                System.out.println("\t(1) - ANIMACAO;");
                System.out.println("\t(2) - COMEDIA;");
                System.out.println("\t(3) - DRAMA;");
                System.out.println("\t(4) - DOCUMENTARIO;");
                System.out.println("\t(5) - FICCAO CIENTIFICA;");
                System.out.println("\t(6) - RELIGIAO;");
                System.out.println("\t(7) - ROMANCE;");
                System.out.println("\t(8) - SUSPENSE;");
                System.out.println("\t(9) - TERROR;");
                int escolha = sc.nextInt();
                sc.nextLine(); // gambiarra
                switch (escolha)
                {
                    case 0:
                        imprimirListaGeneroFilme(listaFilme, GeneroFilme.ACAO);
                        System.out.println("\nCONSULTA REALIZADA COM SUCESSO!");
                        break;
                    case 1:
                        imprimirListaGeneroFilme(listaFilme, GeneroFilme.ANIMACAO);
                        System.out.println("\nCONSULTA REALIZADA COM SUCESSO!");
                        break;
                    case 2:
                        imprimirListaGeneroFilme(listaFilme, GeneroFilme.COMEDIA);
                        System.out.println("\nCONSULTA REALIZADA COM SUCESSO!");
                        break;
                    case 3:
                        imprimirListaGeneroFilme(listaFilme, GeneroFilme.DRAMA);
                        System.out.println("\nCONSULTA REALIZADA COM SUCESSO!");
                        break;
                    case 4:
                        imprimirListaGeneroFilme(listaFilme, GeneroFilme.DOCUMENTARIO);
                        System.out.println("\nCONSULTA REALIZADA COM SUCESSO!");
                        break;
                    case 5:
                        imprimirListaGeneroFilme(listaFilme, GeneroFilme.FICCAO_CIENTIFICA);
                        System.out.println("\nCONSULTA REALIZADA COM SUCESSO!");
                        break;
                    case 6:
                        imprimirListaGeneroFilme(listaFilme, GeneroFilme.RELIGIAO);
                        System.out.println("\nCONSULTA REALIZADA COM SUCESSO!");
                        break;
                    case 7:
                        imprimirListaGeneroFilme(listaFilme, GeneroFilme.ROMANCE);
                        System.out.println("\nCONSULTA REALIZADA COM SUCESSO!");
                        break;
                    case 8:
                        imprimirListaGeneroFilme(listaFilme, GeneroFilme.SUSPENSE);
                        System.out.println("\nCONSULTA REALIZADA COM SUCESSO!");
                        break;
                    case 9:
                        imprimirListaGeneroFilme(listaFilme, GeneroFilme.TERROR);
                        System.out.println("\nCONSULTA REALIZADA COM SUCESSO!");
                        break;
                    default:
                        System.out.println("USER BURRO, ESCOLHEU A OPCAO ERRADA!");
                        System.out.println("\nCONSULTA REALIZADA COM SUCESSO!");
                        break;
                }
                break;
            case 4:
                imprimirListaOrdenadaDiretores(listaFilme);
                System.out.println("\nCONSULTA REALIZADA COM SUCESSO!");
                break;
            case 5:
                imprimirListaAnoLancamento(listaFilme);
                System.out.println("\nCONSULTA REALIZADA COM SUCESSO!");
                break;
            default:
                System.out.println("USER BURRO, ESCOLHEU A OPCAO ERRADA!");
        }
    }
    /**
     *
     * CONSULTAS DOS FILMES
     *
     **/
    // IMPRESSAO DA LISTA
    public static void imprimirLista(List<Filme> lista)
    {
        lista.forEach(p -> System.out.println(p));
    }
    // ORDENANDO POR NOMES DOS FILMES
    public static void imprimirListaOrdenadaNomeFilme(List<Filme> lista)
    {
        lista.stream().sorted((filme1, filme2) -> filme1.getNomeFilme().compareTo(filme2.getNomeFilme()))
                .forEach(p -> p.imprimir());
    }
    // IMPRIMINDO FILME PELO GENERO DESEJADO
    public static void imprimirListaGeneroFilme(List<Filme> lista, GeneroFilme generoFilme)
    {
        lista.stream().filter(p -> p.getGeneroFilme().equals(generoFilme)).forEach(p -> p.imprimir());
    }
    // ORDENANDO POR NOMES DOS DIRETORES
    public static void imprimirListaOrdenadaDiretores(List<Filme> lista)
    {
        lista.stream().sorted((filme1, filme2) -> filme1.getDiretorFilme().compareTo(filme2.getDiretorFilme()))
                .forEach(p -> p.imprimir());
    }
    // ORDENANDO PELO ANO DE LANCAMENTO MAIS ANTIGO
    public static void imprimirListaAnoLancamento(List<Filme> lista)
    {
        lista.sort((filme1, filme2) ->
        {
            Integer aux = filme1.getAnoLancamento();
            return aux.compareTo(filme2.getAnoLancamento());
        });
        lista.forEach(p -> p.imprimir());
    }
}