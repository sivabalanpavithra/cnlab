import java.util.*;

public class DistanceVectorRouting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;  // number of nodes
        System.out.print("Enter the number of nodes: ");
        n = sc.nextInt();

        int cost[][] = new int[n][n];
        System.out.println("Enter the cost matrix (999 for infinity): ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        int distance[][] = new int[n][n];
        int via[][] = new int[n][n];

        // Initialization
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = cost[i][j];
                via[i][j] = j;
            }
        }

        boolean updated;
        do {
            updated = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (distance[i][j] > cost[i][k] + distance[k][j]) {
                            distance[i][j] = cost[i][k] + distance[k][j];
                            via[i][j] = k;
                            updated = true;
                        }
                    }
                }
            }
        } while (updated);

        System.out.println("\nFinal Distance Vector Table:");
        for (int i = 0; i < n; i++) {
            System.out.println("\nRouter " + (i + 1) + " routing table:");
            System.out.println("Destination\tNext Hop\tDistance");
            for (int j = 0; j < n; j++) {
                System.out.println((j + 1) + "\t\t" + (via[i][j] + 1) + "\t\t" + distance[i][j]);
            }
        }

        sc.close();
    }
}