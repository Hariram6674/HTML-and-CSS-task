// import java.io.*;
// import java.net.*;
// import java.util.*;

// public class ChatServer {
//     private static final int PORT = 1234;
//     private static List<PrintWriter> writers = new ArrayList<>();

//     public static void main(String[] args) throws IOException {

//         ServerSocket server = new ServerSocket(PORT);
//         System.out.println("Server started. Waiting for clients...");

//         while (true) {
//             Socket socket = server.accept();
//             System.out.println("Client connected: " + socket);
//             new Thread(new ClientHandler(socket)).start();
//         }
//     }

//     private static class ClientHandler implements Runnable {
//         private final Socket socket;
//         private final BufferedReader in;
//         private final PrintWriter out;

//         public ClientHandler(Socket socket) throws IOException {
//             this.socket = socket;
//             this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//             this.out = new PrintWriter(socket.getOutputStream(), true);
//             writers.add(out);
//         }

//         public void run() {
//             try {
//                 while (true) {
//                     String message = in.readLine();
//                     if (message == null) {
//                         break;
//                     }
//                     System.out.println("Received from " + socket + ": " + message);

//                     // broadcast the message to all clients
//                     for (PrintWriter writer : writers) {
//                         writer.println("Client " + socket + ": " + message);
//                     }
//                 }
//             } catch (IOException e) {
//                 System.out.println("Error: " + e.getMessage());
//             } finally {
//                 try {
//                     socket.close();
//                 } catch (IOException e) {
//                     System.out.println("Error: " + e.getMessage());
//                 }
//                 writers.remove(out);
//             }
//         }
//     }
// }
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private static ServerSocket serverSocket;
    private static final int port = 5000;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                DataInputStream input = new DataInputStream(socket.getInputStream());
                String line = input.readUTF();
                System.out.println("Received from client: " + line);
                input.close();
                socket.close();
                System.out.println("Client disconnected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
                System.out.println("Server closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
