// package Pallindrome_checker;
import java.util.*;

class PalindromeChecker {
    
    boolean isPalindrome(String input) {
        String cleanedInput = input.replaceAll("[\\s\\p{Punct}]", "").toLowerCase();
        int length = cleanedInput.length();

        for (int i = 0; i < length / 2; i++) {
            if (cleanedInput.charAt(i) != cleanedInput.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}

class Main {
    public static void main(String[] args) {
        PalindromeChecker checker = new PalindromeChecker();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word or phrase: ");
        String input = scanner.nextLine();

        if (checker.isPalindrome(input)) {
            System.out.println("The input is a palindrome.");
        } else {
            System.out.println("The input is not a palindrome.");
        }
        
        scanner.close();
    }
}
// A man, a plan, a canal: Puma
// A man, a plan, a canal: Panama