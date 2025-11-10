import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        try {
            // Step 1: Create a datagram socket
            DatagramSocket clientSocket = new DatagramSocket();

            // Step 2: Get server IP address
            InetAddress serverAddress = InetAddress.getByName("localhost");

            // Step 3: Take input from user
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter message to send to server: ");
            String message = userInput.readLine();

            byte[] sendData = message.getBytes();
            byte[] receiveData = new byte[1024];

            // Step 4: Send message to server
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
            clientSocket.send(sendPacket);
            System.out.println("Message sent to server: " + message);

            // Step 5: Receive echoed message from server
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String echoedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Echoed message from server: " + echoedMessage);

            // Step 6: Verify echoed message
            if (message.equals(echoedMessage)) {
                System.out.println("Verification successful: Message matches!");
            } else {
                System.out.println("Verification failed: Message does not match!");
            }

            // Step 7: Close connection
            clientSocket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
