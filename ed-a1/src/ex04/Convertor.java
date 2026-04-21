package ex04;

import java.util.Scanner;
import java.util.Stack;

public class Convertor
{
    public String decToBin(int number)
    {
        Stack<Integer> pileDecToBin = new Stack<>();
        String binaryNumber = "";
        int remainder;

        while (number > 0)
        {
            remainder = number % 2;
            pileDecToBin.push(remainder);
            number /= 2;
        }
        while (!pileDecToBin.isEmpty())
        {
            binaryNumber += pileDecToBin.pop();
        }
        return binaryNumber;
    }
    public void printDecToBin(int number)
    {
        System.out.println("\n" + number + " em binário é: " + decToBin(number) + "\n");
    }

    public String decToHex(int number)
    {
        Stack<Integer> pile = new Stack<>();
        String binaryNumber = "";
        String bases = "0123456789ABCDEF";
        int remainder;

        while (number > 0)
        {
            remainder = number % 16;
            pile.push(remainder);
            number /= 16;
        }
        while (!pile.isEmpty())
        {
            binaryNumber += bases.charAt(pile.pop());
        }
        return binaryNumber;
    }
    public void printDecToHex(int number)
    {
        System.out.println("\n" + number + " em hexadecimal é: " + decToHex(number) + "\n");
    }

    public String binToHex(String binaryNumber)
    {
        int iterator = 0;
        int num = 0;
        String hexNumber = "";
        String bases = "0123456789ABCDEF";

        for (int i = binaryNumber.length() - 1; i >= 0; i--)
        {
            char c = binaryNumber.charAt(i);
            if (c == '1')
            {
                num += Math.pow(2, iterator);
            }
            iterator++;
            if (iterator > 3 || i == 0)
            {
                iterator = 0;
                hexNumber = bases.charAt(num) + hexNumber;
                num = 0;
            }
        }
        return hexNumber;
    }
    public void printBinToHex(String numeroBinario)
    {
        System.out.println("\n" + numeroBinario + " em hexadecimal é: " + binToHex(numeroBinario) + "\n");
    }

    public void menu()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("|                 MENU DE SELEÇÃO                |");
        System.out.println("==================================================");
        System.out.println("| Opções:                                        |");
        System.out.println("|        1. Converter DECIMAL para BINÁRIO.      |");
        System.out.println("|        2. Converter DECIMAL para HEXADECIMAL.  |");
        System.out.println("|        3. Converter BINÁRIO para HEXADECIMAL.  |");
        System.out.println("|        4. Sair.                                |");
        System.out.println("==================================================");
        System.out.print("Opção: ");
        int choice = scanner.nextInt();

        switch (choice)
        {
            case 1:
                System.out.print("\nInforme o número decimal: ");
                int num1 = scanner.nextInt();
                decToBin(num1);
                printDecToBin(num1);

                this.menu();
                break;
            case 2:
                System.out.print("\nInforme o número decimal: ");
                int num2 = scanner.nextInt();
                decToHex(num2);
                printDecToHex(num2);

                this.menu();
                break;
            case 3:
                scanner.nextLine();

                System.out.print("Informe um número binário: ");
                String binaryNumber = scanner.nextLine();
                binToHex(binaryNumber);

                printBinToHex(binaryNumber);

                this.menu();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.err.println("\nOpção inválida!");

                this.menu();
                break;
        }
    }
    public static void main(String[] args)
    {
        Convertor view = new Convertor();
        view.menu();
    }
}