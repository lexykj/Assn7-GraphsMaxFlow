import java.util.LinkedList;

public class MaxFlow {
    int[][] GRAPH_MATRIX;
    int[][] residualMatrix;
    LinkedList<LinkedList<Integer>> allUsedPaths;
    int NUM_OF_NODES;
    Graph ORIGINAL_GRAPH;

    MaxFlow(Graph graph){
        this.ORIGINAL_GRAPH = graph;
        this.GRAPH_MATRIX = this.toMatrix(graph);
        this.NUM_OF_NODES = graph.G.length;
        this.residualMatrix = this.GRAPH_MATRIX;
        this.allUsedPaths = new LinkedList<>();

    }

    /**
     * Takes a graph (in the form of an adjacency list) and turns it into a matrix.
     * @param graph
     */
    int[][] toMatrix(Graph graph){
        int numOfNodes = graph.G.length;
        int[][] graphMatrix = new int[numOfNodes][numOfNodes];

        // j loop sets all items to zero
        // k loop resets the edges to their flow value
        for(int i=0; i < numOfNodes; i++){
            for(int j=0; j < numOfNodes; j++){
                graphMatrix[i][j] = 0;
            }
            for(int k = 0; k < graph.G[i].succ.size(); k++){
                graphMatrix[i][graph.G[i].succ.get(k).to] = graph.G[i].succ.get(k).capacity;
            }
        }

        return graphMatrix;
    }

    /**
     * Finds the shortest path from the source to the sink node
     * @return adjacency list of the path or null if none exist
     */
    // This doesn't work. I need to figure it out by using Graphs and GraphNodes
    GraphNode[] findPath(){
        int s = 0;
        int numOfNodes = this.NUM_OF_NODES;
        int t = numOfNodes - 1;
        GraphNode[] path = new GraphNode[numOfNodes];
//        GraphNode[] tempPath = new GraphNode[numOfNodes];
        for(int i = 0; i < numOfNodes; i++) {
//            tempPath[i] = new GraphNode(i);
            path[i] = new GraphNode(i);
        }
        LinkedList<GraphNode[]> queue = new LinkedList<>();
        queue.add(path);
        while(!queue.isEmpty()){
            path = queue.removeFirst();

            // loop to find which GraphNode was the last one touched
            int lastNode = s;
            while(!path[lastNode].succ.isEmpty()){
                lastNode = path[lastNode].succ.getFirst().to;
            }

            // adds all items that go from lastNode to the queue as new graphs and marks them as visited
            for(int to = 0; to < numOfNodes; to++){
                GraphNode[] tempPath = new GraphNode[numOfNodes];
                for(int i = 0; i < numOfNodes; i++) {
                    tempPath[i] = new GraphNode(i);
                    if(path[i].succ != tempPath[i].succ) {
                        tempPath[i].succ = (LinkedList) path[i].succ.clone();
                    }
                }
                if(this.residualMatrix[lastNode][to] > 0 && !path[to].visited){
                    tempPath[lastNode].addEdge(lastNode, to, this.residualMatrix[lastNode][to]);
                    tempPath[lastNode].visited = true;
                    tempPath[to].parent = lastNode;
                    if(to == t){
                        return tempPath;
                    }
                    queue.add(tempPath);
                }
            }

        }
        return null;
    }

//    GraphNode[] partialPath(int lastNode, int to, GraphNode[] path){
//        if(this.residualMatrix[lastNode][to] > 0 && !path[to].visited){
//            path[lastNode].addEdge(lastNode, to, this.residualMatrix[lastNode][to]);
//            tempPath[lastNode].visited = true;
//            tempPath[to].parent = lastNode;
//            if(to == path.length - 1){
//                return tempPath;
//            }
////            queue.add(tempPath);
//        }
//    }

    /**
     * Updates the residual matrix according to the max flow of the path
     */
    void augmentPath(Graph path){
        int flow = maxPathFlow(path);
        // subtract the capacity from edge(to, from) and add to edge(from, to).
    }

    /**
     * This method finds the max flow of the path by finding the lowest individual flow between edges
     * @param path is the path from source to sink
     * @return the max flow of the path
     */
    private int maxPathFlow(Graph path){
        return 0;
    }

    /**
     * Takes the sum of all flow leaving the sink node.
     * @return the total max flow for this graph
     */
    public int getMaxFlow(){
        return 0;
    }

    /**
     * The demand is the desired flow to the sink, this finds it.
     * @return the demand
     */
    public int getDemand(){
        int demand = 0;
        for(int i = 0; i < NUM_OF_NODES; i++){
            demand += GRAPH_MATRIX[NUM_OF_NODES - 1][i];
        }
        return demand;
    }

    /**
     *
     * @return multiple lines saying "Edge (0,1) transports x cases"
     */
    public String edgeFlowToString(){
        return "";
    }

    public String pathsUsedToString(){
        return "";
    }
}
