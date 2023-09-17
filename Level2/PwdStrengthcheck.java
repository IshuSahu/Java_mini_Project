import java.util.Scanner;

class PwdStrengthcheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        int length = password.length();
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialCharacter = false;

        // Check each character in the password
        for (int i = 0; i < length; i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (isSpecialCharacter(ch)) {
                hasSpecialCharacter = true;
            }
        }

        // Determine password strength
        int strength = 0;
        if (length >= 8) {
            strength++;
        }
        if (hasUppercase) {
            strength++;
        }
        if (hasLowercase) {
            strength++;
        }
        if (hasDigit) {
            strength++;
        }
        if (hasSpecialCharacter) {
            strength++;
        }

        // Provide feedback on password strength
        String feedback;
        System.out.println(strength);
        switch (strength) {
            case 1:
                feedback = "Weak";
                break;
            case 2:
                feedback = "Moderate";
                break;
            case 3:
                feedback = "Strong";
                break;
            case 4:
                feedback = "Very Strong";
                break;
            case 5:
                feedback = "Extremely Strong";
                break;
            default:
                feedback = "Very Weak";
        }

        System.out.println("Password Strength: " + feedback);

        scanner.close();
    }

    // Helper method to check if a character is a special character
    private static boolean isSpecialCharacter(char ch) {
        String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";
        return specialCharacters.contains(String.valueOf(ch));
    }
}
