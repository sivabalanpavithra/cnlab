import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try {
            // Step 1: Create a server socket on port 5000
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is waiting for client connection...");

            // Step 2: Accept the client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            // Step 3: Create input stream to read data from client
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Step 4: Read message from client
            String message = in.readLine();
            System.out.println("Received from client: " + message);

            // Step 5: Close connections
            in.close();
            socket.close();
            serverSocket.close();
            System.out.println("Connection closed.");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
