package tau;

public class Palindrome {


    public static boolean isPalindrome(String text) {
        String clean = text.replaceAll("\\s+", "").toLowerCase();
        int length = clean.length();
        int forward = 0;
        int backward = length - 1;
        while (backward > forward) {
            char forwardChar = clean.charAt(forward++);
            char backwardChar = clean.charAt(backward--);
            if (forwardChar != backwardChar)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        var palindrome1 = palindrome.isPalindrome("tsest");
        System.out.println(palindrome1);
    }
}
