package Chatapp;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket clientSocket = null   ;
        PrintWriter output = null;
        BufferedReader userInput=null;

        try{
            serverSocket = new ServerSocket(5000);
            System.out.println("server started, waiting for client");

            /*Accept the client*/
            clientSocket = serverSocket.accept();
            System.out.println("Client Connect: "+clientSocket);

//            Creating input output stream;
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream(), true);

            Thread readThread = new Thread(()->{
               String message;
               try {
                   while((message = input.readLine())!=null){
                       System.out.println("User: " +message);

                   }

               }catch(Exception e){
                    e.printStackTrace();
               }finally {
                   try {
                       input.close();
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
               }
            });
            readThread.start();

            userInput = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while((message = userInput.readLine())!=null){
                output.println(message);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
            output.close();
            userInput.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }
    }
}
