public class Palindrome {
    // цикл для получения строки в обратном порядке
    public static String reverseString(String str) {
        int i, len = str.length();
        String dest = "";

        for (i = (len - 1); i >= 0; i--) {
            dest += str.charAt(i);
        }

        return dest;
    }

    public static boolean isPalindrome(String str) {
        return str.equals(reverseString(str));
    }

    // входная точка приложения
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (isPalindrome(s)) {
                System.out.printf("%s - палиндром\n", s);
            } else {
                System.out.printf("%s - не палиндром\n", s);
            }
        }
    }
}