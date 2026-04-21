package aplicacao;

// IMPORTACOES

import modelo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal
{
    public static void main(String[] args)
    {
        // INSTANCIAS DA LISTA
        List<Cliente> cliente = new ArrayList<Cliente>();
        List<Funcionario> funcionario = new ArrayList<Funcionario>();
        List<Filme> listaFilme = new ArrayList<Filme>();

        // COLOCANDO CARGA NO SISTEMA
        Cliente cliente1 = new Cliente("adebaldo", "123", "adebaldo@uol.com", Sexo.MASCULINO, 1, Planos.BASICO,
                "melhorlinguagem", "java");
        Funcionario funcionario1 = new Funcionario("peter parkerson", "012", "eunaosouhomemaranha@gmail.com",
                Sexo.MASCULINO, 1, 50.00, "janio", "melhorprofessor");
        Filme f1 = new Filme(1, "Jurassic Park", GeneroFilme.FICCAO_CIENTIFICA, "Steven Spielberg", 1995, 90);
        Filme f2 = new Filme(2, "Interestelar", GeneroFilme.FICCAO_CIENTIFICA, "Christopher Nolan", 2014, 130);
        Filme f3 = new Filme(3, "A Cabana", GeneroFilme.RELIGIAO, "William P. Young", 2017, 120);
        Filme f4 = new Filme(4, "Deus Nao Esta Morto", GeneroFilme.RELIGIAO, "Harold Cronk", 2014, 70);
        Filme f5 = new Filme(5, "Vingadores: Guerra Infinita", GeneroFilme.ACAO, "Stan Lee", 2018, 130);
        Filme f6 = new Filme(6, "Velozes e Furiosos 8", GeneroFilme.ACAO, "Rob Cohen", 2017, 80);
        Filme f7 = new Filme(7, "IT: A Coisa", GeneroFilme.TERROR, "Stephen King", 2017, 70);
        Filme f8 = new Filme(8, "Atividade Paranormal", GeneroFilme.TERROR, "Oren Peli", 2009, 80);
        Filme f9 = new Filme(9, "Fragmentado", GeneroFilme.SUSPENSE, "M. Night Shyamalan", 2016, 80);
        Filme f10 = new Filme(10, "Corpo Fechado", GeneroFilme.SUSPENSE, "M. Night Shyamalan", 2000, 80);
        Filme f11 = new Filme(11, "Gente Grande", GeneroFilme.COMEDIA, "Dennis Dugan", 2010, 80);
        Filme f12 = new Filme(12, "As Branquelas", GeneroFilme.COMEDIA, "Keenen Ivory Wayans", 2004, 80);
        Filme f13 = new Filme(13, "O Rei Leao", GeneroFilme.ANIMACAO, "George Scribner", 1998, 65);
        Filme f14 = new Filme(14, "Toy Story", GeneroFilme.ANIMACAO, "John Lasseter", 2000, 65);
        Filme f15 = new Filme(15, "A Reforma", GeneroFilme.DOCUMENTARIO, "Martinho Lutero", 1517, 75);
        Filme f16 = new Filme(16, "Jobs", GeneroFilme.DOCUMENTARIO, "Apple", 2011, 85);
        Filme f17 = new Filme(17, "Julimeu e Romieta", GeneroFilme.ROMANCE, "Por mim mesmo", 1750, 75);
        Filme f18 = new Filme(18, "Calypso", GeneroFilme.ROMANCE, "Joelma e Chimbinha", 2016, 95);
        Filme f19 = new Filme(19, "Titanic", GeneroFilme.DRAMA, "James Cameron", 1997, 75);
        Filme f20 = new Filme(20, "O Menino do Pijama Listrado", GeneroFilme.DRAMA, "Mark Herman", 2008, 105);

        // ATRIBUINDO OS OBJETOS NAS LISTAS
        listaFilme.add(f1);
        listaFilme.add(f2);
        listaFilme.add(f3);
        listaFilme.add(f4);
        listaFilme.add(f5);
        listaFilme.add(f6);
        listaFilme.add(f7);
        listaFilme.add(f8);
        listaFilme.add(f9);
        listaFilme.add(f10);
        listaFilme.add(f11);
        listaFilme.add(f12);
        listaFilme.add(f13);
        listaFilme.add(f14);
        listaFilme.add(f15);
        listaFilme.add(f16);
        listaFilme.add(f17);
        listaFilme.add(f18);
        listaFilme.add(f19);
        listaFilme.add(f20);
        cliente.add(cliente1);
        funcionario.add(funcionario1);

        // MENU PRINCIPAL
        Scanner sc = new Scanner(System.in);
        System.out.println("========BEM-VINDO A LOCADORA LABAXURIAS SIRICANTAS========");
        System.out.println("ESCOLHA A OPCAO: ");
        System.out.println("\t(1) - CLIENTE;");
        System.out.println("\t(2) - FUNCIONARIO;");
        int i = sc.nextInt();
        sc.nextLine(); // GAMBIARRA
        int chances = 3;
        int tentativas = 0;
        switch (i)
        {
            // LOGIN CLIENTE
            case 1:
                while (tentativas <= chances)
                {
                    System.out.println("=========== LOGIN CLIENTE ==========");
                    System.out.println("INFORME SEU USUARIO: ");
                    String username = sc.nextLine();
                    System.out.println("INFORME SUA SENHA: ");
                    String password = sc.nextLine();
                    for (int j = 0; j < cliente.size(); j++)
                    {
                        Cliente c = cliente.get(j);
                        if (c.getUsername().equals(username) && c.getPassword().equals(password))
                        {
                            // MENU CLIENTE
                            System.out.println("\n===== LOCADORA LABAXURIAS SIRICANTAS =====");
                            System.out.println("=========== MENU CLIENTE ==========");
                            Filme.consultarFilme(listaFilme);
                            tentativas = 4;
                            break;
                        } else {
                            System.out.println("\nUSUARIO E/OU SENHA INCORRETOS!");
                            tentativas++;
                        }
                        break;
                    }
                    if (tentativas == chances)
                    {
                        System.out.println("\nUSER BURRO, " + tentativas + " TENTATIVAS INVALIDAS!");
                        break;
                    }
                }
                break;
            // LOGIN FUNCIONARIO
            case 2:
                while (tentativas <= chances)
                {
                    System.out.println("=========== LOGIN FUNCIONARIO ==========");
                    System.out.println("INFORME SEU USUARIO: ");
                    String u = sc.nextLine();
                    System.out.println("INFORME SUA SENHA: ");
                    String p = sc.nextLine();
                    for (int j = 0; j < funcionario.size(); j++)
                    {
                        Funcionario func = funcionario.get(j);
                        if (func.getUsername().equals(u) && func.getPassword().equals(p))
                        {
                            // MENU FUNCIONARIO
                            System.out.println("\n===== LOCADORA LABAXURIAS SIRICANTAS =====");
                            System.out.println("=========== MENU FUNCIONARIO ==========");
                            System.out.println(" (1) - CADASTRAR FILME;");
                            System.out.println(" (2) - EXCLUIR FILME;");
                            System.out.println(" (3) - CONSULTAR FILME;");
                            System.out.println(" (4) - ALTERAR FILME;");
                            System.out.println(" (5) - SAIR;");
                            int opcao = sc.nextInt();
                            switch (opcao)
                            {
                                // CADASTRAR FILME
                                case 1:
                                    listaFilme.add(Filme.cadastrarFilme());
                                    Filme.imprimirLista(listaFilme);
                                    System.out.println("\nFILME CADASTRADO COM SUCESSO!");
                                    tentativas = 4;
                                    break;
                                // EXCLUIR FILME
                                case 2:
                                    System.out.println("\nESCOLHA O ID DO FILME QUE DESEJA EXCLUIR: \n");
                                    Filme.imprimirLista(listaFilme);
                                    Integer id = sc.nextInt();
                                    sc.nextLine(); // GAMBIARRA
                                    // VERIFICACAO SE A ESCOLHA DO USUARIO EH POSSIVEL SER REALIZADA
                                    if (id <= listaFilme.size() && id > 0)
                                    {
                                        // LACO PARA ENCONTRAR O ID ESCOLHIDO
                                        for (int k = 0; k < listaFilme.size(); k++)
                                        {
                                            Filme f = listaFilme.get(k);
                                            if (f.getIdFilme().equals(id))
                                            {
                                                listaFilme.remove(f);
                                                break;
                                            }
                                        }
                                        System.out.println();
                                        Filme.imprimirLista(listaFilme);
                                        System.out.println("\nFILME REMOVIDO COM SUCESSO!");
                                        tentativas = 4;
                                        break;
                                    }
                                    else
                                    {
                                        System.out.println("\nUSER BURRO, NAO EXISTE ESSE ID!");
                                        break;
                                    }
                                    // CONSULTAR FILME
                                case 3:
                                    Filme.consultarFilme(listaFilme);
                                    tentativas = 4;
                                    break;
                                // ALTERAR FILME
                                case 4:
                                    System.out.println("\nESCOLHA O ID DO FILME QUE DESEJA ALTERAR: \n");
                                    Filme.imprimirLista(listaFilme);
                                    Integer integer = sc.nextInt();
                                    sc.nextLine(); // GAMBIARRA
                                    if (integer <= listaFilme.size() && integer > 0)
                                    {
                                        for (int l = 0; l < listaFilme.size(); l++)
                                        {
                                            if (listaFilme.get(l).getIdFilme().equals(integer))
                                            {
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
                                                sc.nextLine(); // GAMBIARRA
                                                // VERIFICACAO SE A ESCOLHA DO USUARIO EH POSSIVEL SER REALIZADA
                                                if (escolha <= 9 && escolha > -1)
                                                {
                                                    switch (escolha)
                                                    {
                                                        case 0:
                                                            listaFilme.get(l).setGeneroFilme(GeneroFilme.ACAO);
                                                            tentativas = 4;
                                                            break;
                                                        case 1:
                                                            listaFilme.get(l).setGeneroFilme(GeneroFilme.ANIMACAO);
                                                            tentativas = 4;
                                                            break;
                                                        case 2:
                                                            listaFilme.get(l).setGeneroFilme(GeneroFilme.COMEDIA);
                                                            tentativas = 4;
                                                            break;
                                                        case 3:
                                                            listaFilme.get(l).setGeneroFilme(GeneroFilme.DRAMA);
                                                            tentativas = 4;
                                                            break;
                                                        case 4:
                                                            listaFilme.get(l).setGeneroFilme(GeneroFilme.DOCUMENTARIO);
                                                            tentativas = 4;
                                                            break;
                                                        case 5:
                                                            listaFilme.get(l).setGeneroFilme(GeneroFilme.FICCAO_CIENTIFICA);
                                                            tentativas = 4;
                                                            break;
                                                        case 6:
                                                            listaFilme.get(l).setGeneroFilme(GeneroFilme.RELIGIAO);
                                                            tentativas = 4;
                                                            break;
                                                        case 7:
                                                            listaFilme.get(l).setGeneroFilme(GeneroFilme.ROMANCE);
                                                            tentativas = 4;
                                                            break;
                                                        case 8:
                                                            listaFilme.get(l).setGeneroFilme(GeneroFilme.SUSPENSE);
                                                            tentativas = 4;
                                                            break;
                                                        case 9:
                                                            listaFilme.get(l).setGeneroFilme(GeneroFilme.TERROR);
                                                            tentativas = 4;
                                                            break;
                                                        default:
                                                            tentativas = 4;
                                                            break;
                                                    }
                                                    System.out.println();
                                                    Filme.imprimirLista(listaFilme);
                                                    System.out.println("\nFILME ALTERADO COM SUCESSO!");
                                                    tentativas = 4;
                                                    break;
                                                }
                                                else
                                                {
                                                    System.out.println("\nUSER BURRO, NAO EXISTE ESSE GENERO AI!");
                                                    tentativas = 4;
                                                    break;
                                                }
                                            }
                                            tentativas = 4;
                                        }
                                        tentativas = 4;
                                        break;
                                    }
                                    else
                                    {
                                        System.out.println("\nUSER BURRO, NAO EXISTE ESSE FILME AI!");
                                        tentativas = 4;
                                        break;
                                    }
                                case 5:
                                    System.out.println("\nLOGOUT REALIZADO COM SUCESSO!");
                                    tentativas = 4;
                                    break;
                                default:
                                    System.out.println("\nUSER BURRO!");
                                    tentativas = 4;
                                    break;
                            }
                        }
                        else
                        {
                            System.out.println("\nUSUARIO E/OU SENHA INCORRETOS!");
                            tentativas++;
                        }
                        break;
                    }
                    if (tentativas == chances) {
                        System.out.println("\nUSER BURRO, " + tentativas + " TENTATIVAS INVALIDAS!");
                        break;
                    }
                }
                break;
                default:
                    System.out.println("\nUSER BURRO! NAO SABE ESCOLHER DUAS OPCOES, IMAGINE USAR UM SISTEMA INTEIRO!");
                    break;
        }
    }
}