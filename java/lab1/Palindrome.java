/**
 * simple class checking is word a palindrome
 **/
public class Palindrome
{
    /** main method **/
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++)
        {
            String s = args[i];
            String answer = "";
            if (isPalindrome(s) != true)
            {
                answer = "n't";
            }
            System.out.println(s + " is" + answer + " palindrome");
        }
    }

    /** method to reverse string **/
    public static String reverseString(String s)
    {
        String r = "";
        for (int i = s.length() - 1; i >= 0; i--)
        {
            r += s.charAt(i);
        }
        return r;
    }

    /** method to check palindrome */
    public static boolean isPalindrome(String s)
    {
        return s.equals(reverseString(s));
    }
}