import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        try {
            // Step 1: Connect to the server on localhost and port 5000
            Socket socket = new Socket("localhost", 5000);

            // Step 2: Create output stream to send data to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Step 3: Take input from user
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter message to send to server: ");
            String message = userInput.readLine();

            // Step 4: Send message to server
            out.println(message);
            System.out.println("Message sent to server: " + message);

            // Step 5: Close connection
            out.close();
            userInput.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
