import java.util.*;

public class Djkstra {
    public static class Edge {
        int src, dst, wt;

        Edge(int src, int dst, int wt) {
            this.src = src;
            this.dst = dst;
            this.wt = wt;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // Add edges to the graph using the Edge constructor
        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));

        graph[1].add(new Edge(1, 3, 7));
        graph[1].add(new Edge(1, 2, 1));

        graph[2].add(new Edge(2, 4, 3));

        graph[3].add(new Edge(3, 5, 1));
        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));
    }

    public static void Bfs(ArrayList<Edge> graph[], boolean vis[], int src) {
        Queue<Integer> q = new LinkedList<>();
        q.add(src);

        while (!q.isEmpty()) {
            int cur = q.remove();
            if (!vis[cur]) {
                System.out.print(cur + " ");
                vis[cur] = true;

                for (Edge e : graph[cur]) {
                    q.add(e.dst);
                }
            }
        }
    }

    public static class Pair implements Comparable<Pair> {
        int node, dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.dist - p2.dist;
        }
    }

    public static void dijkstra(ArrayList<Edge> graph[], int src, int v) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int dist[] = new int[v];
        boolean visit[] = new boolean[v];

        // Initialize distances to infinity, except for the source node
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Add the source node to the priority queue
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair cur = pq.remove();

            // If the current node is already visited, skip it
            if (visit[cur.node]) continue;
            visit[cur.node] = true;

            // Traverse all edges of the current node
            for (Edge e : graph[cur.node]) {
                int u = e.src;
                int vi = e.dst;

                // Relaxation step
                if (dist[vi] > dist[u] + e.wt) {
                    dist[vi] = dist[u] + e.wt;
                    pq.add(new Pair(vi, dist[vi]));
                }
            }
        }

        // Print the shortest distances
        System.out.println("Shortest distances from source " + src + ":");
        for (int i = 0; i < v; i++) {
            System.out.println("Node " + i + " Distance: " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }

    public static void displayGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print("Node: " + i);
            for (Edge e : graph[i]) {
                System.out.print(" -> (Dest: " + e.dst + ", Weight: " + e.wt + ")");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int v = 6;
        ArrayList<Edge> graph[] = new ArrayList[v];
        createGraph(graph);

        System.out.println("Graph Representation:");
        displayGraph(graph);

        System.out.println("\nBFS Traversal:");
        boolean vis[] = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!vis[i])
                Bfs(graph, vis, i);
        }

        System.out.println("\n\nDijkstra's Algorithm:");
        dijkstra(graph, 0, v);
    }
}
