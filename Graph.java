import java.util.ArrayList;

public class Graph {
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

        graph[0].add(new Edge(0,2));
        graph[1].add(new Edge(1,2));
        graph[1].add(new Edge(1,2));
        graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,1));
        graph[2].add(new Edge(2,3));
        graph[3].add(new Edge(3,1));
        graph[3].add(new Edge(3,2));
    }

    public static void printGraph(ArrayList<Edge>graph[]){
        for (int i = 0; i < graph.length; i++) {
            System.out.print(" Node: "+i+" & Edges: ");
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                System.out.print(e.dst+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int vertices=4;

        ArrayList<Edge>graph[]= new ArrayList[vertices];
        createGraph(graph);
        printGraph(graph);
    }
}