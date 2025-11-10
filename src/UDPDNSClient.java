import java.io.*;
import java.net.*;
import java.util.*;

public class UDPDNSClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // Step 1: Get domain name and frame size from user
            System.out.print("Enter domain name (e.g., www.google.com): ");
            String domain = reader.readLine();

            System.out.print("Enter frame size: ");
            int frameSize = Integer.parseInt(reader.readLine());

            // Step 2: Break domain into frames
            byte[] domainBytes = domain.getBytes();
            int totalFrames = (int) Math.ceil((double) domainBytes.length / frameSize);
            System.out.println("Total frames to be sent: " + totalFrames);

            // Step 3: Send frames sequentially
            for (int i = 0; i < totalFrames; i++) {
                int start = i * frameSize;
                int end = Math.min(start + frameSize, domainBytes.length);
                byte[] frame = Arrays.copyOfRange(domainBytes, start, end);

                DatagramPacket sendPacket = new DatagramPacket(frame, frame.length, serverAddress, 9876);
                clientSocket.send(sendPacket);
                System.out.println("Frame " + (i + 1) + " sent: " + new String(frame));
            }

            // Step 4: Receive IP address from server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String ipAddress = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received IP Address from server: " + ipAddress);

            // Step 5: Close socket
            clientSocket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
