package labs;

import java.util.Scanner;
import labs.Auxiliary;

public class Lab1
{
    public static int remainder(int numerator, int denominator)
    {
        return numerator % denominator;
    }

    public static float triArea(float basic, float height)
    {
        return basic * height / 2;
    }

    public static int animals(int chickens, int cows, int pigs)
    {
        return chickens * 2 + (cows + pigs) * 4;
    }

    public static boolean profitableGamble(float prob, float prize, float pay)
    {
        return prob * prize > pay;
    }

    public static String operation(int number, int a, int b)
    {
        if (a + b == number)
            return "added";
        else if (a - b == number)
            return "subtracted";
        else if (a * b == number)
            return "multiplied";
        else if (a / b == number)
            return "divided";
        else
            return "none";
    }

    public static int ctoa(char character)
    {
        return (int) character;
    }

    public static int addUpTo(int top)
    {
        int sum = 0;
        for (int i = 1; i <= top; i++) {
            sum += i;
        }
        return sum;
    }

    public static int nextEdge(int firstEdge, int secondEdge)
    {
        return firstEdge + secondEdge - 1;
    }

    public static int sumOfCubes(int numbers[])
    {
        int sum = 0;
        for (int i: numbers) {
            sum += Math.pow(i, 3);
        }
        return sum;
    }

    public static boolean abcmatch(int a, int b, int c)
    {
        return (a + b) % c == 0;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("remainder function =>");
        System.out.print("Input a numerator: ");
        int numerator = in.nextInt();
        System.out.print("Input a denominator: ");
        int denominator = in.nextInt();
        int remainder_result = remainder(numerator, denominator);
        System.out.printf("Your remainder: %d \n\n", remainder_result);

        System.out.println("triArea function =>");
        System.out.print("Input a basic: ");
        float basic = in.nextFloat();
        System.out.print("Input a height: ");
        float height = in.nextFloat();
        float area_result = triArea(basic, height);
        System.out.printf("Your area: %.2f \n\n", area_result);

        System.out.println("animals function =>");
        System.out.print("Input chickens amount: ");
        int chickens = in.nextInt();
        System.out.print("Input cows amount: ");
        int cows = in.nextInt();
        System.out.print("Input pigs amount: ");
        int pigs = in.nextInt();
        int animals_result = animals(chickens, cows, pigs);
        System.out.printf("Your animals legs: %d \n\n", animals_result);

        System.out.println("profitableGamble function =>");
        System.out.print("Input a prob: ");
        float prob = in.nextFloat();
        System.out.print("Input a prize: ");
        float prize = in.nextFloat();
        System.out.print("Input a pay: ");
        float pay = in.nextFloat();
        boolean gamble_result = profitableGamble(prob, prize, pay);
        System.out.printf("Your profitableGamble: %s \n\n", gamble_result);

        System.out.println("operation function =>");
        System.out.print("Input a number: ");
        int number = in.nextInt();
        System.out.print("Input a: ");
        int a = in.nextInt();
        System.out.print("Input b: ");
        int b = in.nextInt();
        String operation_result = operation(number, a, b);
        System.out.printf("Your operation: %s \n\n", operation_result);

        System.out.println("ctoa function =>");
        System.out.print("Input a character: ");
        char character = in.next().charAt(0);
        int ctoa_result = ctoa(character);
        System.out.printf("Your ctoa: %d \n\n", ctoa_result);

        System.out.println("addUpTo function =>");
        System.out.print("Input a top: ");
        int top = in.nextInt();
        int addUpTo_result = addUpTo(top);
        System.out.printf("Your addUpTo: %d \n\n", addUpTo_result);

        System.out.println("nextEdge function =>");
        System.out.print("Input a firstEdge: ");
        int firstEdge = in.nextInt();
        System.out.print("Input a secondEdge: ");
        int secondEdge = in.nextInt();
        int nextEdge_result = nextEdge(firstEdge, secondEdge);
        System.out.printf("Your nextEdge: %d \n\n", nextEdge_result);

        System.out.println("sumOfCubes function =>");
        int sumOfCubes_result = sumOfCubes(Auxiliary.askArray(in));
        System.out.printf("Your sumOfCubes: %d \n\n", sumOfCubes_result);

        System.out.println("abcmatch function =>");
        System.out.print("Input an a: ");
        int A = in.nextInt();
        System.out.print("Input an b: ");
        int B = in.nextInt();
        System.out.print("Input an c: ");
        int C = in.nextInt();
        boolean abcmatch_result = abcmatch(A, B, C);
        System.out.printf("Your abcmatch: is %sdivided \n\n", abcmatch_result ? "": "not ");

        in.close();
    }
}