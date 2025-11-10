import java.io.*;
import java.net.*;
import java.util.*;

public class RARPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter MAC address: ");
            String mac = sc.nextLine();

            // Step 1: Send MAC address to server
            byte[] sendData = mac.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 5000);
            clientSocket.send(sendPacket);

            // Step 2: Receive IP address from server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String ip = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Step 3: Display IP address
            System.out.println("IP Address from Server: " + ip);

            clientSocket.close();
            sc.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
