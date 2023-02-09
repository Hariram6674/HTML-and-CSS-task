// import java.io.*;
// import java.net.*;

// public class ChatClient {
    
//     //Server Information
//     private static final int PORT = 1234;
//     private static final String HOST = "localhost";
    
//     public static void main(String[] args) {
//         try (Socket socket = new Socket(HOST, PORT);
//             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                
//             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

//             System.out.println("Connected to the server. Type 'exit' to quit.");
            
//             //Thread to keep listening to server message
//             Thread listener = new Thread(() -> {
//                 try {
//                     while (true) {
//                         String serverResponse = in.readLine();
//                         if (serverResponse == null) {
//                             System.out.println("Server closed the connection.");
//                             break;
//                         }
//                         System.out.println("Server: " + serverResponse);
//                     }
//                 } catch (IOException e) {
//                     System.out.println("Error: " + e.getMessage());
//                 }
//             });
//             listener.start();
            
//             //Main thread to send message
//             while (true) {
//                 System.out.print("You: ");
//                 String message = userInput.readLine();
//                 if (message.equalsIgnoreCase("exit")) {
//                     break;
//                 }
//                 out.println(message);
//             }
//             System.out.println("Disconnected from server.");
            
//         } catch (UnknownHostException e) {
//             System.out.println("Error: Unknown host.");
//         } catch (ConnectException e) {
//             System.out.println("Error: Could not connect to the server.");
//         } catch (IOException e) {
//             System.out.println("Error: " + e.getMessage());
//         }
//     }
// }
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static Socket socket;
    private static final String address = "127.0.0.1";
    private static final int port = 5000;

    public static void main(String[] args) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected to server on " + address + ":" + port);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter message to send to server: ");
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                output.writeUTF(message);
            }
            output.close();
            socket.close();
            System.out.println("Disconnected from server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
