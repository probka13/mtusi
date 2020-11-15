package labs;

import java.util.ArrayList;
import java.util.Scanner;
import labs.Auxiliary;

public class Lab5
{
    public static ArrayList encrypt(String message)
    {
        ArrayList<Integer> result = new ArrayList<Integer>();
        char[] chars = message.toCharArray();
        int previous = (int) chars[0];
        result.add(previous);
        for (int i = 1; i < chars.length; i++) {
            result.add((int) chars[i] - previous);
            previous = (int) chars[i];
        }
        return result;
    }

    public static String decrypt(int[] encrypted)
    {
        String result = String.valueOf((char) encrypted[0]);
        int previous = encrypted[0];
        for (int i = 1; i < encrypted.length; i++) {
            result += String.valueOf((char) (encrypted[i] + previous));
            previous = encrypted[i] + previous;
        }
        return result;
    }

    public static boolean canMove(String figure, String firstPoint, String secondPoint)
    {
        class Point {
            private int literal;
            private int numeral;

            public Point(String point) {
                this.literal = (int) point.charAt(0);
                this.numeral = (int) point.charAt(1);
            }
        }

        Point current = new Point(firstPoint);
        Point target = new Point(secondPoint);

        switch (figure) {
            case ("pawn"):
                return current.literal == target.literal && target.numeral - current.numeral == 1;
            case ("king"):
                return Math.abs(current.literal - target.literal) < 2 && Math.abs(current.numeral - target.numeral) < 2;
            case ("rook"):
                return current.literal == target.literal || current.numeral == target.numeral;
            case ("bishop"):
                return Math.abs(target.literal - current.literal) == Math.abs(target.numeral - current.numeral);
            case ("queen"):
                return canMove("rook", firstPoint, secondPoint) || canMove("bishop", firstPoint, secondPoint);
            case ("horse"):
                return current.literal != target.literal && current.numeral != target.numeral &&
                    Math.abs(target.literal - current.literal) + Math.abs(target.numeral - current.numeral) == 3;
            default:
                throw new IllegalArgumentException(String.format("illegal figure for this game: %s", figure));
        }
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("encrypt function =>");
        System.out.print("Input a message: ");
        String message = in.next();
        ArrayList encrypt_result = encrypt(message);
        System.out.printf("Your encrypt: %s \n\n", encrypt_result);

        System.out.println("decrypt function =>");
        String decrypt_result = decrypt(Auxiliary.askArray(in));
        System.out.printf("Your decrypt: %s \n\n", decrypt_result);

        System.out.println("canMove function =>");
        System.out.print("Input a figure: ");
        String figure = in.next();
        System.out.print("Input a first point: ");
        String firstPoint = in.next();
        System.out.print("Input a second point: ");
        String secondPoint = in.next();
        boolean canMove_result = canMove(figure, firstPoint, secondPoint);
        System.out.printf("Your canMove: %s \n\n", canMove_result);

        in.close();
    }
}
