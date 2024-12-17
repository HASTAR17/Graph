import java.util.*;

public class bfsDfs {
    // Edge class to represent an edge from src to dst
    public static class Edge{
        int src, dst;

        // Constructor to initialize source and destination of an edge
        Edge(int src, int dst){
            this.src = src;
            this.dst = dst;
        }
    }

    // Method to build the graph by adding edges
    public static void buildGraph(ArrayList<Edge> graph[]){
        // Initialize each graph index with an empty ArrayList
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>(); // Empty graph for each node
        }

        // Add edges to the graph using the Edge constructor
        graph[0].add(new Edge(0, 5)); // Node 0 -> Node 5
        graph[0].add(new Edge(0, 6)); // Node 0 -> Node 6
        graph[0].add(new Edge(0, 7)); // Node 0 -> Node 7
        graph[1].add(new Edge(1, 2)); // Node 1 -> Node 2
        graph[1].add(new Edge(1, 3)); // Node 1 -> Node 3   
        graph[2].add(new Edge(2, 8)); // Node 2 -> Node 8
        graph[3].add(new Edge(3, 9)); // Node 3 -> Node 9
        graph[4].add(new Edge(4, 10)); // Node 4 -> Node 10
        graph[3].add(new Edge(3, 1)); // Node 3 -> Node 1
        graph[3].add(new Edge(3, 2)); // Node 3 -> Node 2
    }

    // Method to display the graph representation
    public static void showGraph(ArrayList<Edge> graph[]){
        // Iterate over all nodes in the graph
        for (int i = 0; i < graph.length; i++) {
            System.out.print("Node : " + i + " & Edges: ");
            
            // Display all destination nodes for the current node
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                System.out.print(e.dst + " ");
            }
            System.out.println();
        }
    }

    // BFS method to traverse the graph
    public static void Bfs(ArrayList<Edge> graph[], boolean visited[], int source){
        // Create a queue to store nodes for BFS traversal
        Queue<Integer> q = new LinkedList<>();
        q.add(source); // Add the source node to the queue

        // Perform BFS traversal
        while (!q.isEmpty()) {
            int curr = q.remove(); // Remove a node from the queue
            if (visited[curr] == false) { // If node is not visited
                System.out.print(curr + " "); // Print the node
                visited[curr] = true; // Mark the node as visited

                // Add all the neighbors of the current node to the queue
                for (int i = 0; i < graph[curr].size(); i++) {
                    Edge e = graph[curr].get(i);
                    q.add(e.dst); // Add destination node of edge to queue
                }
            }
        }
    }

    // DFS method to traverse the graph
    public static void Dfs(ArrayList<Edge> graph[], boolean visited[], int curr){
        // Print the current node in DFS traversal
        System.out.print(curr + " ");
        visited[curr] = true; // Mark the node as visited

        // Recursively visit all the neighbors of the current node
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (visited[e.dst] == false) { // If neighbor is not visited
                Dfs(graph, visited, e.dst); // Recur on the destination node
            }
        }
    }

    public static void printAllPath(ArrayList<Edge> graph[],boolean visited[], int target,String path, int curr){
        if(curr==target){
            System.out.print(path);
            return;
        }

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if(visited[e.dst]==false){
                visited[curr]=true;
                printAllPath(graph, visited, target, path+e.dst, e.dst);
                visited[curr]=false;
            }
        }
    }

    public static void main(String[] args) {
        int v = 11; // Number of vertices in the graph
        ArrayList<Edge> graph[] = new ArrayList[v]; // Create an array of ArrayLists to store edges
        
        System.out.println("Graph Representation: ");
        buildGraph(graph); // Build the graph using the buildGraph method
        showGraph(graph); // Display the graph

        System.out.print("BFS Representation: ");
        boolean visited[] = new boolean[v]; // Array to keep track of visited nodes

        // Perform BFS traversal for all unvisited nodes
        for (int i = 0; i < v; i++) {
            if (visited[i] == false) { // If node is not visited
                Bfs(graph, visited, i); // Perform BFS starting from the node
            }
        }

        System.out.println();
        Arrays.fill(visited, false); // Reset visited array

        System.out.print("DFS Representation: ");
        // Perform DFS traversal for all unvisited nodes
        for (int j = 0; j < v; j++) {
            if (visited[j] == false) { // If node is not visited
                Dfs(graph, visited, j); // Perform DFS starting from the node
            }
        }
        System.out.println();
        Arrays.fill(visited, false);
        System.out.print("Path: ");
        int src=0, target=5;
        
        printAllPath(graph, visited, target, "0", src);

    }
}
