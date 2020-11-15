package labs;

import java.util.Scanner;

public class Auxiliary
{
    public static int[] askArray(Scanner in) {
        System.out.print("Input an array length: ");
        int arrayLength = in.nextInt();
        int numbers[] = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++ ) {
            System.out.print("Input a array number: ");
            numbers[i] = in.nextInt();
        }
        return numbers;
    }

    public static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }
}

