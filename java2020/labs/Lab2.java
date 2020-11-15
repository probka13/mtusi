package labs;

import java.util.Arrays;
import java.util.Scanner;
import labs.Auxiliary;

public class Lab2
{
    public static String repeat(String word, int count)
    {
        String result = "";
        for (char c: word.toCharArray()) {
            result += new String(new char[count]).replace("\0", String.valueOf(c));
        }
        return result;
    }

    public static int differenceMaxMin(int[] numbers)
    {
        int max = numbers[0];
        int min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
            else if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return max - min;
    }

    public static boolean isAvgWhole(int[] numbers)
    {
        int sum = 0;
        for (int i: numbers) {
            sum += i;
        }
        return sum % numbers.length == 0;
    }

    public static int[] cumulativeSum(int[] numbers)
    {
        int[] result = new int[numbers.length];
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
            result[i] = sum;
        }
        return result;
    }

    public static int getDecimalPlaces(String number)
    {
        String[] parts = number.split("\\.");
        if (parts.length < 2) {
            return 0;
        }
        else {
            return parts[1].length();
        }
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("repeat function =>");
        System.out.print("Input a word: ");
        String word = in.next();
        System.out.print("Input a count: ");
        int count = in.nextInt();
        String repeat_result = repeat(word, count);
        System.out.printf("Your repeat: %s \n\n", repeat_result);

        System.out.println("differenceMaxMin function =>");
        int differenceMaxMin_result = differenceMaxMin(Auxiliary.askArray(in));
        System.out.printf("Your differenceMaxMin: %d \n\n", differenceMaxMin_result);

        System.out.println("isAvgWhole function =>");
        boolean isAvgWhole_result = isAvgWhole(Auxiliary.askArray(in));
        System.out.printf("Your isAvgWhole: %s \n\n", isAvgWhole_result);

        System.out.println("cumulativeSum function =>");
        int[] cumulativeSum_result = cumulativeSum(Auxiliary.askArray(in));
        System.out.printf("Your cumulativeSum: %s \n\n", Arrays.toString(cumulativeSum_result));

        System.out.println("getDecimalPlaces function =>");
        System.out.print("Input a number: ");
        String number = in.next();
        int getDecimalPlaces_result = getDecimalPlaces(number);
        System.out.printf("Your getDecimalPlaces: %d \n\n", getDecimalPlaces_result);

        in.close();
    }
}