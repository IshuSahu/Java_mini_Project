package Chatapp;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            // Connect to the server on a specific IP address and port
            Socket socket = new Socket("192.168.43.30", 5000);
            System.out.println("Connected to server.");

            // Create output stream for sending messages to the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Create input stream for receiving messages from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Create scanner for reading user input
            Scanner scanner = new Scanner(System.in);

            // Start a thread to handle incoming messages from the server
            Thread receiveThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Server: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();

            // Read and send user messages to the server
            String userInput;
            while (true) {
                userInput = scanner.nextLine();
                out.println(userInput); // Send user's message to the server
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
