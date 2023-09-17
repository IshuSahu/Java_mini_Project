import java.io.*;
import java.util.Scanner;

public class FileEncrypDecrypt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter 'E' for Encryption or 'D' for Decryption: ");
        char choice = scanner.next().charAt(0);

        if (choice == 'E' || choice == 'e') {
            System.out.print("Enter the input file name or path: ");
            String inputFileName = scanner.next();
            System.out.print("Enter the output file name: ");
            String outputFileName = scanner.next();

            try {
                encryptFile(inputFileName, outputFileName);
                System.out.println("File encrypted successfully.");
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else if (choice == 'D' || choice == 'd') {
            System.out.print("Enter the input file name or path: ");
            String inputFileName = scanner.next();
            System.out.print("Enter the output file name: ");
            String outputFileName = scanner.next();

            try {
                decryptFile(inputFileName, outputFileName);
                System.out.println("File decrypted successfully.");
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            System.err.println("Invalid choice. Please enter 'E' or 'D' for Encryption or Decryption.");
        }

        scanner.close();
    }

    // Encryption method 
    private static void encryptFile(String inputFileName, String outputFileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            int key = 3; // You can change the encryption key as needed

            int currentChar;
            while ((currentChar = reader.read()) != -1) {
                char encryptedChar = (char) (currentChar + key);
                writer.write(encryptedChar);
            }
        }
    }

    // Decryption method (
    private static void decryptFile(String inputFileName, String outputFileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            int key = 3; // You should use the same encryption key used for encryption

            int currentChar;
            while ((currentChar = reader.read()) != -1) {
                char decryptedChar = (char) (currentChar - key);
                writer.write(decryptedChar);
            }
        }
    }
}

