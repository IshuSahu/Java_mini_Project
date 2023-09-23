// package Random_Password_Generator;

import java.util.Random;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter desired length of the password: ");
        int passwordLength = scanner.nextInt();

        System.out.print("Include numbers (yes/no): ");
        boolean includeNumbers = scanner.next().equalsIgnoreCase("yes");

        System.out.print("Include lowercase letters (yes/no): ");
        boolean includeLowercase = scanner.next().equalsIgnoreCase("yes");

        System.out.print("Include uppercase letters (yes/no): ");
        boolean includeUppercase = scanner.next().equalsIgnoreCase("yes");

        System.out.print("Include special characters (yes/no): ");
        boolean includeSpecialChars = scanner.next().equalsIgnoreCase("yes");

        String numbers = "0123456789";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialChars = "!@#$%^&";

        String characters = "";
        if (includeNumbers) {
            characters += numbers;
        }
        if (includeLowercase) {
            characters += lowercase;
        }
        if (includeUppercase) {
            characters += uppercase;
        }
        if (includeSpecialChars) {
            characters += specialChars;
        }

        if (characters.isEmpty()) {
            System.out.println("No character types selected. Cannot generate password.");
        } else {
            String generatedPassword = generateRandomPassword(passwordLength, characters);
            System.out.println("Generated Password: " + generatedPassword);
        }

        scanner.close();
    }

    private static String generateRandomPassword(int length, String characters) {
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }
}
