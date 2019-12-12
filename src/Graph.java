import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

public class Graph {
    int vertexCt;  // Number of vertices in the graph.
    GraphNode[] G;  // Adjacency list for graph.
    String graphName;  //The file from which the graph was created.

    public Graph() {
        this.vertexCt = 0;
        this.graphName = "";
    }

    public static void main(String[] args) {
        Graph graph1 = new Graph();
        graph1.makeGraph("src" + File.separator + "demands1.txt");
        System.out.println(graph1.toString());
        MaxFlow graph1Flow = new MaxFlow(graph1);
//        for(int i = 0; i < graph1.vertexCt; i++){
//            for(int j = 0; j < graph1.vertexCt; j++){
//                System.out.print(graph1Flow.GRAPH_MATRIX[i][j] + " ");
//            }
//            System.out.println();
//        }
//        GraphNode[] path = graph1Flow.findPath();
//        graph1Flow.augmentPath(path);
//        System.out.println();
//
//        int from = 0;
//        System.out.print(path[from].toString());
//        while(!path[from].succ.isEmpty()){
//            from = path[from].succ.get(0).to;
//            System.out.print(path[from].toString());
//        }
//        System.out.println(graph1Flow.maxPathFlow(path) + "\n");
//
//        for(int i = 0; i < graph1.vertexCt; i++){
//            for(int j = 0; j < graph1.vertexCt; j++){
//                System.out.print(graph1Flow.residualMatrix[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println(graph1Flow.getMaxFlow());
    }

    public int getVertexCt() {
        return vertexCt;
    }

    public boolean addEdge(int source, int destination, int cap) {
        //System.out.println("addEdge " + source + "->" + destination + "(" + cap + ")");
        if (source < 0 || source >= vertexCt) return false;
        if (destination < 0 || destination >= vertexCt) return false;
        //add edge
        G[source].addEdge(source, destination, cap);
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("The Graph " + graphName + " \n");

        for (int i = 0; i < vertexCt; i++) {
            sb.append(G[i].toString());
        }
        return sb.toString();
    }

    public void makeGraph(String filename) {
        try {
            graphName = filename;
            Scanner reader = new Scanner(new File(filename));
            vertexCt = reader.nextInt();
            G = new GraphNode[vertexCt];
            for (int i = 0; i < vertexCt; i++) {
                G[i] = new GraphNode(i);
            }
            while (reader.hasNextInt()) {
                int v1 = reader.nextInt();
                int v2 = reader.nextInt();
                int cap = reader.nextInt();
                if (!addEdge(v1, v2, cap))
                    throw new Exception();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}