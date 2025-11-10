import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try {
            // Step 1: Create server socket
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for client...");

            // Step 2: Accept client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            // Step 3: Create input/output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            String clientMsg, serverMsg;

            // Step 4: Chat loop
            while (true) {
                clientMsg = in.readLine(); // receive message from client
                if (clientMsg == null || clientMsg.equalsIgnoreCase("bye")) {
                    System.out.println("Client ended the chat.");
                    break;
                }
                System.out.println("Client: " + clientMsg);

                System.out.print("Server: ");
                serverMsg = keyboard.readLine(); // server input
                out.println(serverMsg);

                if (serverMsg.equalsIgnoreCase("bye"))
                    break;
            }

            // Step 5: Close connections
            in.close();
            out.close();
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
