import java.io.*;
import java.net.*;
import java.util.*;

public class RARPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(5000);
            byte[] receiveData = new byte[1024];
            byte[] sendData;

            // Step 1: Create RARP Table (MAC → IP)
            HashMap<String, String> rarpTable = new HashMap<String, String>();
            rarpTable.put("00-A0-C9-14-C8-29", "192.168.1.1");
            rarpTable.put("00-A0-C9-14-C8-30", "192.168.1.2");
            rarpTable.put("00-A0-C9-14-C8-31", "192.168.1.3");
            rarpTable.put("00-A0-C9-14-C8-32", "192.168.1.4");

            System.out.println("RARP Server is running...");

            while (true) {
                // Step 2: Receive MAC address from client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String mac = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received MAC address: " + mac);

                // Step 3: Find corresponding IP
                String ip = rarpTable.getOrDefault(mac, "IP address not found for given MAC");

                // Step 4: Send IP back to client
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                sendData = ip.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
