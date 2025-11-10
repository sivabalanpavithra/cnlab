import java.util.*;

public class LinkStateRouting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int MAX = 999;

        System.out.print("Enter the number of nodes: ");
        int n = sc.nextInt();

        int cost[][] = new int[n][n];
        System.out.println("Enter the cost adjacency matrix (999 for infinity): ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter the source node (1 to " + n + "): ");
        int src = sc.nextInt() - 1;

        int distance[] = new int[n];
        boolean visited[] = new boolean[n];
        int parent[] = new int[n];

        // Step 1: Initialize distances and parents
        for (int i = 0; i < n; i++) {
            distance[i] = cost[src][i];
            parent[i] = src;
            visited[i] = false;
        }

        distance[src] = 0;
        visited[src] = true;

        // Step 2: Dijkstra’s Algorithm
        for (int count = 1; count < n - 1; count++) {
            int min = MAX, nextNode = -1;

            // Find the node with the smallest distance
            for (int i = 0; i < n; i++) {
                if (!visited[i] && distance[i] < min) {
                    min = distance[i];
                    nextNode = i;
                }
            }

            visited[nextNode] = true;

            // Update distances
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    if (distance[nextNode] + cost[nextNode][i] < distance[i]) {
                        distance[i] = distance[nextNode] + cost[nextNode][i];
                        parent[i] = nextNode;
                    }
                }
            }
        }

        // Step 3: Display shortest paths
        System.out.println("\nShortest paths from node " + (src + 1) + ":");
        for (int i = 0; i < n; i++) {
            if (i != src) {
                System.out.print("To node " + (i + 1) + " : Path = ");
                printPath(parent, i);
                System.out.println(" -> " + (i + 1) + "  |  Total Cost = " + distance[i]);
            }
        }

        sc.close();
    }

    // Recursive function to print path
    private static void printPath(int[] parent, int j) {
        if (parent[j] == j)
            return;
        printPath(parent, parent[j]);
        System.out.print((parent[j] + 1) + " ");
    }
}
        