package labs;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab4
{
    public static String processText(int n, int k, String text)
    {
        String[] words = text.split(" ");
        String result = words[0];
        int wordsCount = 1;
        int wordsLength = words[0].length();
        for (int i = 1; i < words.length; i++) {
            if (wordsLength + words[i].length() > k) {
                result += "\n" + words[i];
                wordsLength = words[i].length();
            }
            else {
                result += " " + words[i];
                wordsLength += words[i].length();
            }
            wordsCount += 1;
            if (wordsCount == n)
                break;
        }
        return result;
    }

    public static ArrayList split(String sentence)
    {
        int opened = 0;
        int closed = 0;
        String cluster = "";
        ArrayList<String> result = new ArrayList<String>();
        for (char c: sentence.toCharArray()) {
            if ((int) c == 40)
                opened += 1;
            if ((int) c == 41)
                closed += 1;
            cluster += String.valueOf(c);
            if (opened == closed) {
                result.add(cluster);
                cluster = "";
                opened = 0;
                closed = 0;
            }
        }
        return result;
    }

    public static String toCamelCase(String notCamelString)
    {
        String[] words = notCamelString.split("[_,\\s]+");
        String result = words[0];
        for (int i = 1; i < words.length; i++) {
            result += words[i].substring(0,1).toUpperCase() + words[i].substring(1);
        }
        return result;
    }

    public static String toSnakeCase(String notSnakeString)
    {
        String result = "";
        for (char c: notSnakeString.toCharArray()) {
            if ((int) c > 64 && (int) c < 91)
                result += "_" + String.valueOf(c).toLowerCase();
            else
                result += String.valueOf(c);
        }
        return result;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("processText function =>");
        System.out.print("Input a n: ");
        int n = in.nextInt();
        System.out.print("Input a k: ");
        int k = in.nextInt();
        in.nextLine();
        System.out.print("Input a sentence: ");
        String sentence = in.nextLine();
        String processText_result = processText(n, k, sentence);
        System.out.printf("Your processText: \n%s \n\n", processText_result);

        System.out.println("split function =>");
        System.out.print("Input brackets: ");
        String brackets = in.nextLine();
        ArrayList split_result = split(brackets);
        System.out.printf("Your split: %s \n\n", split_result);

        System.out.println("toCamelCase function =>");
        System.out.print("Input not camel string: ");
        String notCamelString = in.nextLine();
        String toCamelCase_result = toCamelCase(notCamelString);
        System.out.printf("Your toCamelCase: %s \n\n", toCamelCase_result);

        System.out.println("toSnakeCase function =>");
        System.out.print("Input not snake string: ");
        String notSnakeString = in.nextLine();
        String toSnakeCase_result = toSnakeCase(notSnakeString);
        System.out.printf("Your toSnakeCase: %s \n\n", toSnakeCase_result);

        in.close();
    }
}