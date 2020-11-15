package labs;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import labs.Auxiliary;

public class Lab6
{
    public static int bell(int number)
    {
        ArrayList<Integer> first = new ArrayList<>();
        first.add(1);
        ArrayList<Integer> second = new ArrayList<>();
        for (int i = 1; i < number; i++) {
            second.add(first.get(first.size() - 1));
            for (int j = 0; j < first.size(); j++) {
                second.add(first.get(j) + second.get(j));
            }
            first = second;
            second = new ArrayList<>();
        }
        return first.get(first.size() - 1);
    }

    public static String translateWord(String word)
    {
        char[] temp = word.toCharArray();
        boolean upperCase = false;
        if (temp.length == 0)
            return word;
        else if (Auxiliary.isVowel(temp[0]))
            return word + "yay";
        else {
            int vowelIndex = 0;
            if ((int) temp[0] > 65 && (int) temp[0] < 91)
                upperCase = true;
            for (char c: temp) {
                if (Auxiliary.isVowel(c))
                    break;
                else
                    vowelIndex++;
            }
            if (upperCase) {
                word = word.replace(word.substring(0,1), word.substring(0,1).toLowerCase());
                word = word.replace(word.substring(vowelIndex, vowelIndex + 1),
                            word.substring(vowelIndex, vowelIndex + 1).toUpperCase());
            }
            return word.substring(vowelIndex) + word.substring(0,vowelIndex) + "ay";
        }
    }

    public static String translateSentence(String sentence)
    {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(sentence);
        String result = "";
        String translated;
        int currentPoint = 0;
        while (matcher.find()) {
            translated = translateWord(sentence.substring(matcher.start(), matcher.end()));
            if (matcher.start() == currentPoint)
                result += translated;
            else
                result += sentence.substring(currentPoint, matcher.start()) + translated;
            currentPoint = matcher.end();
        }
        result += sentence.substring(currentPoint);
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("bell function =>");
        System.out.print("Input a number: ");
        int number = in.nextInt();
        in.nextLine();
        int bell_result = bell(number);
        System.out.printf("Your bell: %d \n\n", bell_result);

        System.out.println("translateWord function =>");
        System.out.print("Input a word: ");
        String word = in.nextLine();
        String translateWord_result = translateWord(word);
        System.out.printf("Your translateWord: %s \n\n", translateWord_result);

        System.out.println("translateSentence function =>");
        System.out.print("Input a sentence: ");
        String sentence = in.nextLine();
        String translateSentence_result = translateSentence(sentence);
        System.out.printf("Your translateSentence: %s \n\n", translateSentence_result);

        in.close();
    }
}