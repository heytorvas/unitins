package ex03;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class AddPile
{
    public static void push(Stack<Integer> pile, int value)
    {
        String temp = Integer.toString(value);
        for (int i = 0; i < temp.length(); i++)
        {
            pile.push(temp.charAt(i) - '0');
        }
    }
    public static void pop(Stack<Integer> pile)
    {
        pile.pop();
    }

    public static List<Integer> addValues(Stack<Integer> pileA, Stack<Integer> pileB)
    {
        int remainder = 0, value = 0, goOne = 0, x = 0, y = 0;

        if (!pileA.isEmpty())
            x = pileA.pop();

        if (!pileB.isEmpty())
            y = pileB.pop();

        remainder = x + y + goOne;
        value = remainder % 10;

        if (remainder > 9)
            goOne = 1;
        else
            goOne = 0;

        System.out.println("Valor: " + value + " | Vai um: " + goOne);
        return Arrays.asList(value, goOne);
    }

    public static void main(String[] args)
    {
        Scanner scanenr = new Scanner(System.in);
        Stack<Integer> pileA = new Stack<Integer>();
        Stack<Integer> pileB = new Stack<Integer>();
        Stack<Integer> pileC = new Stack<Integer>();
        List<Integer> aux = null;
        int result = 0;
        int remainder = 0;

        System.out.print("Digite o primeiro valor: ");
        int a = scanenr.nextInt();
        System.out.print("Digite o segundo valor: ");
        int b = scanenr.nextInt();

        push(pileA, a);
        push(pileB, b);

        System.out.println("\nPilha A: " + pileA);
        System.out.println("Pilha B: " + pileB + "\n");

        while (!pileA.isEmpty() || !pileB.isEmpty())
        {
            aux = addValues(pileA, pileB);
            result = aux.get(0) + remainder;
            remainder = aux.get(1);
            pileC.add(result);
        }

        String out = "";
        if (remainder == 1)
            out += 1;

        while (!pileC.isEmpty())
        {
            out += pileC.pop();
        }

        System.out.println("\nResultado: " + out);
    }
}