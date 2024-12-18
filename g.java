import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class g {

    // Class to represent an edge in the graph
    public static class Edge {
        int src, dst, wt;

        Edge(int src, int dst, int wt) {
            this.src = src; // Source node of the edge
            this.dst = dst; // Destination node of the edge
            this.wt = wt; // Weight of the edge
        }
    }

    // Create a graph with nodes and edges
    public static void mkGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>(); // Initialize adjacency list for each node
        }

        // Add edges to the graph (src, dst, weight)
        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));

        graph[1].add(new Edge(1, 3, 7));
        graph[1].add(new Edge(1, 2, 1));

        graph[2].add(new Edge(2, 4, 3));

        graph[3].add(new Edge(3, 5, 1));
        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));
    }

    // Display the graph structure
    public static void dGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print("Node: " + i);
            for (Edge e : graph[i]) {
                System.out.print(" Edge: " + e.dst + " Weight: " + e.wt);
            }
            System.out.println();
        }
    }

    // Breadth-First Search (BFS) traversal of the graph
    public static void bfs(ArrayList<Edge> graph[], boolean visitA[], int src) {
        Queue<Integer> q = new LinkedList<>(); // Queue for BFS
        q.add(src); // Add source node to the queue

        while (!q.isEmpty()) {
            int current = q.remove(); // Remove a node from the queue
            if (!visitA[current]) {
                System.out.print(current + " "); // Visit the current node
                visitA[current] = true; // Mark the node as visited

                // Add all unvisited neighbors to the queue
                for (Edge e : graph[current]) {
                    q.add(e.dst);
                }
            }
        }
    }

    // Depth-First Search (DFS) traversal of the graph
    public static void dfs(ArrayList<Edge> graph[], boolean visitA[], int src) {
        System.out.print(src + " "); // Visit the current node
        visitA[src] = true; // Mark the node as visited

        // Recur for all unvisited neighbors
        for (Edge e : graph[src]) {
            if (!visitA[e.dst]) {
                dfs(graph, visitA, e.dst);
            }
        }
    }

    // Class to represent a pair for Dijkstra's algorithm (node and distance)
    public static class Pair implements Comparable<Pair> {
        int node, dist;

        Pair(int node, int dist) {
            this.node = node; // Node index
            this.dist = dist; // Distance to the node
        }

        @Override
        public int compareTo(Pair p2) {
            return this.dist - p2.dist; // Compare based on distance
        }
    }

    // Dijkstra's algorithm to find the shortest path from a source node
    public static void Djkstra(ArrayList<Edge> graph[], int src, boolean visitA[], int v) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(); // Priority queue for selecting minimum distance
        int dist[] = new int[v]; // Array to store distances from the source node
        pq.add(new Pair(0, 0)); // Add source node to the priority queue
        Arrays.fill(dist, Integer.MAX_VALUE); // Initialize distances to infinity
        dist[src] = 0; // Distance to source node is 0

        while (!pq.isEmpty()) {
            Pair curr = pq.remove(); // Get the node with minimum distance
            if (!visitA[curr.node]) {
                visitA[curr.node] = true; // Mark the current node as visited
            }
            // Process neighbors of the current node
            for (Edge e : graph[curr.node]) {
                int u = e.src; // Current node
                int vi = e.dst; // Neighbor node

                if (dist[vi] > dist[u] + e.wt) { // Check if a shorter path exists
                    dist[vi] = dist[u] + e.wt; // Update distance
                    pq.add(new Pair(vi, dist[vi])); // Add neighbor to priority queue
                }
            }
        }

        // Print distances from the source node
        System.out.println("From source node: " + src);
        for (int i = 0; i < dist.length; i++) {
            System.out.println("Node " + i + " Distance: " + (dist[i] == Integer.MAX_VALUE ? "Infinity" : dist[i]));
        }
    }

    // Class to represent a pair for Prim's algorithm (node and cost)
    public static class Pair2 implements Comparable<Pair2> {
        int node, cost;

        Pair2(int node, int cost) {
            this.node = node; // Node index
            this.cost = cost; // Cost to reach this node
        }

        @Override
        public int compareTo(Pair2 p2) {
            return this.cost - p2.cost; // Compare based on cost
        }
    }

    // Prim's algorithm to find the Minimum Spanning Tree (MST)
    public static void Prims(ArrayList<Edge> graph[], boolean[] visitA) {
        PriorityQueue<Pair2> pq = new PriorityQueue<>(); // Priority queue for selecting minimum cost edges
        int[] parent = new int[graph.length]; // Array to store parent nodes of MST
        Arrays.fill(parent, -1); // Initialize parent array with -1

        pq.add(new Pair2(0, 0)); // Start from node 0
        int mstCost = 0; // Initialize total cost of MST

        while (!pq.isEmpty()) {
            Pair2 curr = pq.remove(); // Get the minimum cost edge

            if (!visitA[curr.node]) {
                visitA[curr.node] = true; // Mark the current node as visited
                mstCost += curr.cost; // Add the cost to MST

                // Iterate over neighbors of the current node
                for (Edge e : graph[curr.node]) {
                    if (!visitA[e.dst]) {
                        pq.add(new Pair2(e.dst, e.wt)); // Add neighbor to the priority queue
                        parent[e.dst] = curr.node; // Set parent of the destination node
                    }
                }
            }
        }

        System.out.println("Minimum Cost of MST: " + mstCost);
        System.out.println("Edges in the MST:");

        // Print edges of the MST
        for (int i = 1; i < parent.length; i++) {
            if (parent[i] != -1) {
                System.out.println("Edge: " + parent[i] + " - " + i);
            }
        }
    }

    // Main function to execute the program
    public static void main(String[] args) {
        int v = 6; // Number of vertices in the graph
        ArrayList<Edge> graph[] = new ArrayList[v]; // Adjacency list representation of the graph
        System.out.println("Graph Representation: ");
        mkGraph(graph); // Create the graph
        dGraph(graph); // Display the graph

        System.out.println("BFS Traversal: ");
        boolean visitA[] = new boolean[v]; // Array to track visited nodes

        for (int i = 0; i < v; i++) {
            if (!visitA[i]) {
                bfs(graph, visitA, i); // Perform BFS for each component
            }
        }
        System.out.println();

        Arrays.fill(visitA, false); // Reset visited array
        System.out.println("DFS Traversal: ");
        for (int i = 0; i < v; i++) {
            if (!visitA[i]) {
                dfs(graph, visitA, i); // Perform DFS for each component
            }
        }
        System.out.println();

        Arrays.fill(visitA, false); // Reset visited array

        System.out.print("Dijkstra Algorithm: ");
        Djkstra(graph, 0, visitA, v);

        System.out.println();
        Arrays.fill(visitA, false);
        System.out.print("MST Prims Algorithm: ");
        Prims(graph, visitA);

    }
}
