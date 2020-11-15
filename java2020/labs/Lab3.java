package labs;

import java.util.Scanner;

public class Lab3
{
    public static int solutions(int a, int b, int c)
    {
        int discriminant = b * b - 4 * a * c;
        if (discriminant > 0)
            return 2;
        else if (discriminant == 0)
            return 1;
        else
            return 0;
    }

    public static int findZip(String sentence)
    {
        int firstIndex = sentence.indexOf("zip");
        return sentence.indexOf("zip", firstIndex + 1);
    }

    public static boolean checkPerfect(int isPerfect)
    {
        int sum = 1;
        for (int i = 2; i < isPerfect; i++) {
            if (isPerfect % i == 0) {
                sum += i;
            }
        }
        return sum == isPerfect;
    }

    public static String flipEndChars(String sentence)
    {
        if (sentence.length() < 2)
            return "Incompatible";
        else if (sentence.charAt(0) == sentence.charAt(sentence.length() - 1))
            return "Two's a pair";
        else
            return sentence.substring(sentence.length() - 1) + sentence.substring(1, sentence.length() - 1)
                        + sentence.substring(0, 1);
    }

    public static boolean isValidHexCode(String code)
    {
        boolean result = true;
        if ((int) code.charAt(0) != 35) {
            result = false;
        }
        else if (code.length() != 7) {
            result = false;
        }
        else {
            for (char c: code.substring(1).toCharArray()) {
                int ascii_code = (int) c;
                if ((ascii_code < 48) || ((57 < ascii_code) && (ascii_code < 65)) || ((70 < ascii_code) && (ascii_code < 97)) || (ascii_code > 102))
                    result = false;
            }
        }
        return result;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("solutions function =>");
        System.out.print("Input an a: ");
        int a = in.nextInt();
        System.out.print("Input a b: ");
        int b = in.nextInt();
        System.out.print("Input a c: ");
        int c = in.nextInt();
        in.nextLine();
        int solutions_result = solutions(a, b, c);
        System.out.printf("Your solutions: %d \n\n", solutions_result);

        System.out.println("findZip function =>");
        System.out.print("Input a sentence: ");
        String sentence = in.nextLine();
        int findZip_result = findZip(sentence);
        System.out.printf("Your findZip: %d \n\n", findZip_result);

        System.out.println("checkPerfect function =>");
        System.out.print("Input an isPerfect: ");
        int isPerfect = in.nextInt();
        in.nextLine();
        boolean checkPerfect_result = checkPerfect(isPerfect);
        System.out.printf("Your checkPerfect: %s \n\n", checkPerfect_result);

        System.out.println("flipEndChars function =>");
        System.out.print("Input a sentence: ");
        String sentence2 = in.nextLine();
        String flipEndChars_result = flipEndChars(sentence2);
        System.out.printf("Your flipEndChars: %s \n\n", flipEndChars_result);

        System.out.println("isValidHexCode function =>");
        System.out.print("Input a code: ");
        String code = in.next();
        boolean isValidHexCode_result = isValidHexCode(code);
        System.out.printf("Your isValidHexCode: %s \n\n", isValidHexCode_result);

        in.close();
    }
}