import java.util.*;

public class ARPSimulation {
    public static void main(String[] args) {
        // Step 1: Create ARP table using HashMap
        HashMap<String, String> arpTable = new HashMap<String, String>();

        // Step 2: Predefine some IP–MAC mappings
        arpTable.put("192.168.1.1", "00-A0-C9-14-C8-29");
        arpTable.put("192.168.1.2", "00-A0-C9-14-C8-30");
        arpTable.put("192.168.1.3", "00-A0-C9-14-C8-31");
        arpTable.put("192.168.1.4", "00-A0-C9-14-C8-32");
        arpTable.put("192.168.1.5", "00-A0-C9-14-C8-33");

        // Step 3: Get IP address from user
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the IP address to find its MAC address: ");
        String ip = sc.nextLine();

        // Step 4: Check if IP exists in ARP table
        if (arpTable.containsKey(ip)) {
            System.out.println("MAC address for IP " + ip + " is: " + arpTable.get(ip));
        } else {
            System.out.println("The IP address " + ip + " is not found in the ARP table.");
        }

        sc.close();
    }
}
