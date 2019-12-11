import java.util.Iterator;

public class MaxFlow {
    int[][] graphMatrix;
    int[][] residualMatrix;
    GraphNode[][] allUsedPaths;

    MaxFlow(GraphNode[] graphList){
        this.graphMatrix = this.toMatrix(graphList);
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
     * @return adjacency list of the path
     */
//    GraphNode[] findPath(){
//
//    }

    /**
     * Updates the residual matrix according to the max flow of the path
     */
    void augmentPath(GraphNode[] path){

    }

    /**
     * This method finds the max flow of the path by finding the lowest individual flow between edges
     * @param path is the path from source to sink
     * @return the max flow of the path
     */
    int maxPathFlow(GraphNode[] path){
        return 0;
    }

    /**
     * Takes the sum of all flow leaving the sink node.
     * @return the total max flow for this graph
     */
    int getMaxFlow(){
        return 0;
    }
}
