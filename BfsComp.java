import java.util.*;

public class BfsComp {
    public static class Edge{
        int src, dst;
        

        Edge(int src, int dst){
            this.src=src;
            this.dst=dst;
            
    }
    }
    public static void createGraph(ArrayList<Edge>graph[]){
        for (int i = 0; i < graph.length; i++) {
            graph[i]= new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));

        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,3));

        graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,4));

        graph[3].add(new Edge(3,1));
        graph[3].add(new Edge(3,4));
        graph[3].add(new Edge(3,5));

        graph[4].add(new Edge(4,2));
        graph[4].add(new Edge(4,3));
        graph[4].add(new Edge(4,5));

        graph[5].add(new Edge(5,3));
        graph[5].add(new Edge(5,4));
        graph[5].add(new Edge(5,6));

        graph[6].add(new Edge(6,5));

        
    }

    public static void bfs(ArrayList<Edge>graph[],boolean vis[], int vertices,int start){
        Queue<Integer>q= new LinkedList<>();
        
        q.add(start);

        while(!q.isEmpty()){
            int cur= q.remove();

            if (vis[cur]==false){
                System.out.print(cur+ " ");
                vis[cur]=true;

                for (int i = 0; i < graph[cur].size(); i++) {
                 Edge e= graph[cur].get(i);
                 q.add(e.dst);
                }
            }
        }
    }

    public static void printGraph(ArrayList<Edge>graph[]){
        for (int i = 0; i < graph.length; i++) {
            System.out.print("Node: "+i+" & Edges: ");
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                System.out.print(e.dst+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int vertices=7;

        ArrayList<Edge>graph[]= new ArrayList[vertices];
        createGraph(graph);
        System.out.println("Graph Representation:");
        printGraph(graph);

        System.out.println("BFS: ");
        boolean vis[]= new boolean[vertices];
        for (int i = 0; i < vertices; i++) {
            
            if(vis[i]==false){
                bfs(graph, vis, vertices, i);
            }
        }
        
       
        
    }

}