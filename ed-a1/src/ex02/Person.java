package ex02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Person
{
    Scanner scanner = new Scanner(System.in);
    List<Person> list = new ArrayList<Person>();

    private String name;
    private int age;
    private double height;

    public Person()
    {
    }
    public Person(String name, int age, double height)
    {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public double getHeight()
    {
        return height;
    }
    public void setHeight(double height)
    {
        this.height = height;
    }

    @Override
    public String toString()
    {
        return "NOME: " + getName() + " - IDADE: " + getAge() + " - ALTURA: " + getHeight();
    }
    public void printList()
    {
        System.out.println(this.toString());
    }
    public void adding()
    {
        Person person = new Person();
        System.out.println("DIGITE O NOME: ");
        person.setName(scanner.next());
        scanner.nextLine();
        System.out.println("DIGITE A IDADE: ");
        person.setAge(scanner.nextInt());
        System.out.println("DIGITE A ALTURA: ");
        person.setHeight(scanner.nextDouble());
        list.add(person);
    }
    public void printNameSortList(List<Person> list)
    {
        System.out.println("\nLISTA POR ORDEM ALFABETICA");
        list.stream().sorted((person1, person2) -> person1.getName().compareTo(person2.getName()))
                .forEach(p -> p.printList());
    }
    public void printAgeSortList(List<Person> list)
    {
        System.out.println("\nLISTA POR IDADE");
        list.sort((person1, person2) ->
        {
            Integer aux = person1.getAge();
            return aux.compareTo(person2.getAge());
        });
        list.forEach(p -> p.printList());
    }
    public void printHeightSortList(List<Person> list)
    {
        System.out.println("\nLISTA POR ALTURA");
        list.sort((person1, person2) ->
        {
            Double aux = person1.getHeight();
            return aux.compareTo(person2.getHeight());
        });
        list.forEach(p -> p.printList());
    }

    public void menu()
    {
        int choice = 0;
        while (choice != -1)
        {
            System.out.println("=============================");
            System.out.println("\t\tCADASTRO\n");
            System.out.println("1- ADICIONAR PESSOA;");
            System.out.println("2- LISTAR POR ORDEM ALFABETICA;");
            System.out.println("3- LISTAR POR IDADE;");
            System.out.println("4- LISTAR POR ALTURA;");
            System.out.println("5- SAIR;");
            System.out.println("=============================");
            choice = scanner.nextInt();

            //adding
            if (choice == 1)
            {
                adding();
            }
            //list by alphabetical order
            if (choice == 2)
            {
                printNameSortList(list);
            }
            //list by age
            if (choice == 3)
            {
                printAgeSortList(list);
            }
            //list by height
            if (choice == 4)
            {
                printHeightSortList(list);
            }
            //logout
            if (choice == 5)
            {
                System.out.println("LOGOUT COMPLETO!");
                choice = -1;
            }
        }
    }

    public static void main(String[] args)
    {
        Person p = new Person();
        p.menu();
    }
}
