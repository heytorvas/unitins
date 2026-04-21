package ex01;

import java.util.*;

public class Dictionary
{
    Scanner scanner = new Scanner(System.in);
    List<Dictionary> list = new ArrayList<Dictionary>();

    private String word;
    private String definition;

    public Dictionary()
    {
    }
    public Dictionary(String word, String definition)
    {
        this.word = word;
        this.definition = definition;
    }

    public String getWord()
    {
        return word;
    }
    public void setWord(String word)
    {
        this.word = word;
    }
    public String getDefinition()
    {
        return definition;
    }
    public void setDefinition(String definition)
    {
        this.definition = definition;
    }

    public void adding()
    {
        Dictionary dictionary = new Dictionary();
        System.out.println("DIGITE O TERMO: ");
        dictionary.setWord(scanner.next());
        scanner.nextLine();
        System.out.println("DIGITE A DEFINICAO DESSE TERMO: ");
        dictionary.setDefinition(scanner.nextLine());
        list.add(dictionary);
    }
    public void searching(List<Dictionary> list)
    {
        System.out.println("DIGITE O TERMO A SER PROCURADO: ");
        String w = scanner.next();
        int index = 0;
        for (Dictionary dictionary : list)
        {
            if (dictionary.getWord().contains(w))
            {
                System.out.println("TERMO: " + list.get(index).getWord() + " - DEFINICAO: " + list.get(index).getDefinition());
                return;
            }
            index++;
        }
    }
    public void printList(List<Dictionary> list)
    {
        list.forEach(p -> System.out.println(p.toString()));
    }
    public void printSortList(List<Dictionary> list)
    {
        System.out.println("\nLISTA EM ORDEM ALFABETICA");
        Collections.sort(list, new Comparator<Dictionary>()
        {
            @Override
            public int compare(Dictionary o1, Dictionary o2)
            {
                return o1.getWord().compareTo(o2.getWord());
            }
        });
        printList(list);
    }

    public void menu()
    {
        int choice = 0;
        while (choice != -1)
        {
            System.out.println("=============================");
            System.out.println("\t\tDICIONARIO\n");
            System.out.println("1- ADICIONAR TERMO;");
            System.out.println("2- PROCURAR TERMO;");
            System.out.println("3- LISTAR EM ORDEM ALFABETICA;");
            System.out.println("4- SAIR;");
            System.out.println("=============================");
            choice = scanner.nextInt();

            //adding
            if (choice == 1)
            {
                adding();
            }
            //searching
            if (choice == 2)
            {
                searching(list);
            }
            //list by alphabetical order
            if (choice == 3)
            {
                printSortList(list);
            }
            //logout
            if (choice == 4)
            {
                System.out.println("LOGOUT COMPLETO!");
                choice = -1;
            }
        }
    }

    @Override
    public String toString()
    {
        return "TERMO: " + getWord() + " - DEFINICAO: " + getDefinition();
    }

    public static void main(String[] args)
    {
        Dictionary d = new Dictionary();
        d.menu();
    }
}