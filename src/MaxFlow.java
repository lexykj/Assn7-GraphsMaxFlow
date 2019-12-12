import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class MaxFlow {
    int[][] graphMatrix;
    int[][] residualMatrix;
    LinkedList<LinkedList<Integer>> allUsedPaths;
    int numOfNodes;

    MaxFlow(GraphNode[] graphList){
        this.graphMatrix = this.toMatrix(graphList);
        this.numOfNodes = graphList.length;
        this.residualMatrix = this.graphMatrix;
        this.allUsedPaths = new LinkedList<>();
    }

    /**
     * Takes a graph (in the form of an adjacency list) and turns it into a matrix.
     * @param graphList
     */
    int[][] toMatrix(GraphNode[] graphList){
        int numOfNodes = graphList.length;
        int[][] graphMatrix = new int[numOfNodes][numOfNodes];

        // j loop sets all items to zero
        // k loop resets the edges to their flow value
        for(int i=0; i < numOfNodes; i++){
            for(int j=0; j < numOfNodes; j++){
                graphMatrix[i][j] = 0;
            }
            for(int k = 0; k < graphList[i].succ.size(); k++){
                graphMatrix[i][graphList[i].succ.get(k).to] = graphList[i].succ.get(k).capacity;
            }
        }

        return graphMatrix;
    }

    /**
     * Finds the shortest path from the source to the sink node
     * @return adjacency list of the path or null if none exist
     */
    private LinkedList<Integer> findPath(){
        int s = 0;
        int t = this.numOfNodes;
        LinkedList<Integer> path = new LinkedList<>();
        path.add(s);
        LinkedList<Integer> tempPath = path;

        LinkedList<LinkedList<Integer>> queue = new LinkedList<>();
        queue.add(path);
        while(queue != null){
            path = queue.removeFirst();
            int from = path.getLast();
            for(int to = 0; to < this.numOfNodes; to++){
                if(this.residualMatrix[from][to] > 0 && !path.contains(to)){
                    // I need to also check if it's been visited. I think I've got it.
                    tempPath.add(this.residualMatrix[from][to]);
                    if(to == t){
                        return tempPath;
                    }
                    queue.add(tempPath);
                    tempPath = path;
                }
            }

        }
        return null;
    }

    /**
     * Updates the residual matrix according to the max flow of the path
     */
    void augmentPath(LinkedList<Integer> path){
        int flow = maxPathFlow(path);
        // subtract the capacity from edge(to, from) and add to edge(from, to).
    }

    /**
     * This method finds the max flow of the path by finding the lowest individual flow between edges
     * @param path is the path from source to sink
     * @return the max flow of the path
     */
    private int maxPathFlow(LinkedList<Integer> path){
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
        for(int i = 0; i < numOfNodes; i++){
            demand += graphMatrix[numOfNodes - 1][i];
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
