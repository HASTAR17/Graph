import java.util.*;

public class Dfs {

    public static class Edge {
        int src, dst;

        Edge(int src, int dst) {
            this.src = src;
            this.dst = dst;
        }
    }

    public static void makeGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
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

    public static void Bfs(ArrayList<Edge> graph[],int v, int start, boolean vis[]){
        Queue<Integer>q= new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            
            int curr= q.remove();

            if(vis[curr]==false){
                System.out.print(curr+ " ");
                vis[curr]= true;
                for (int i = 0; i < graph[curr].size(); i++) {
                    Edge e =graph[curr].get(i);
                    q.add(e.dst);
                }
            }
        }


    }


    public static void Dfs(ArrayList<Edge> graph[], int curr, boolean vis[] ){
        System.out.print(curr+ " ");
        vis[curr]= true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e= graph[curr].get(i);
            if(vis[e.dst]==false){
                Dfs(graph,e.dst,vis);
            }
        }
    }
    public static void displayGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print("Node: " + i + " & Edges: ");
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                System.out.print(e.dst + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int v =11; 
        ArrayList<Edge> graph[] = new ArrayList[v];
        System.out.println("Graph Representation: ");
        makeGraph(graph);
        displayGraph(graph);

        System.out.print("Bfs Representation: ");

        boolean vis[]= new boolean[v];

        for (int i = 0; i < v; i++) {
            if(vis[i]==false){
                Bfs(graph,v, i,vis);
            }
        }
        System.out.println();
           // Reset the visited array
           Arrays.fill(vis, false);
        System.out.print("Dfs Representation: ");
        for (int i = 0; i < v; i++) {
            if(vis[i]==false){
                Dfs(graph, i,vis);
            }
        }
        
    }
}
