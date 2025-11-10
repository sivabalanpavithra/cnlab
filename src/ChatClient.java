import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try {
            // Step 1: Connect to server
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to Server.");

            // Step 2: Create input/output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            String clientMsg, serverMsg;

            // Step 3: Chat loop
            while (true) {
                System.out.print("Client: ");
                clientMsg = keyboard.readLine();
                out.println(clientMsg); // send to server

                if (clientMsg.equalsIgnoreCase("bye"))
                    break;

                serverMsg = in.readLine(); // receive from server
                System.out.println("Server: " + serverMsg);

                if (serverMsg.equalsIgnoreCase("bye"))
                    break;
            }

            // Step 4: Close connections
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
