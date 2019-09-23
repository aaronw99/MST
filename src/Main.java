import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide how many vertices you would like to create a complete graph from.");
        int vertices = scanner.nextInt();
        System.out.println("Please provide the maximum weight for an edge");
        int maxWeight = scanner.nextInt();
        int[][] graph = completeGraphWeightGenerator(vertices, maxWeight);
        int numEdges = ((vertices*vertices) - vertices) / 2;

        int minVal = Integer.MAX_VALUE;
        Set<MST> uniqueMSTs = new HashSet<>();
        for(int startingVertex = 0; startingVertex < vertices; startingVertex++){
            MST mst = primsAlgorithm(graph, startingVertex);
            uniqueMSTs.add(mst);
            int mstVal = mst.getVal();
            if(mstVal < minVal){
                minVal = mstVal;
            }
//            System.out.println("MST from " + startingVertex);
//            System.out.println(mst);
//            System.out.println("---------------------------------------------------------------");
        }
        System.out.println();
        System.out.println("Size of the graph: [Vertices: " + vertices + ", Edges: " + numEdges + "]");
        System.out.println("Lowest MST value: " + minVal);
        System.out.println("Unique MSTs created: " + uniqueMSTs.size());

        System.out.println();
//        boolean wantsAdjacencyList = false;
        System.out.println("Would you like to see the adjacency list of the graph? (Y/N)");
        System.out.println();
        adjacencyList(graph);
//        wantsAdjacencyList = scanner.nextBoolean();
//        boolean wantsEdgeList = false;
    }

    private static MST primsAlgorithm(int[][] graph, int startVertex){
        List<Edge> edgesTaken = new ArrayList<>();
        Set<Integer> discoveredVertices = new HashSet<>();
        int val = 0;
        int start = startVertex;
        discoveredVertices.add(start);
        while(discoveredVertices.size() < graph.length){
            int[] curNeighbors = graph[start];
            int min = Integer.MAX_VALUE;
            int next = startVertex;
            for(int j = 0; j < curNeighbors.length; j++){
                if(j!= start && !discoveredVertices.contains(j)){
                    if(curNeighbors[j] < min){
                        min = curNeighbors[j];
                        next = j;
                    }
                }
            }
            val += min;
            Edge taken = new Edge(start, next, min);
            start = next;
            discoveredVertices.add(next);
            edgesTaken.add(taken);
        }
        return new MST(edgesTaken, val);
    }

    /**
     * Randomly generates a complete weighted graph of v vertices
     * @param v - Number of vertices
     * @param maxWeight - the max weight for an edge
     * @return a complete graph of v vertices with random weights from 1 to maxWeight (inclusive)
     */
    private static int[][] completeGraphWeightGenerator(int v, int maxWeight){
        int[][] result = new int[v][v];
        Random rn = new Random();
        for(int i = 0; i < v; i++){
            for(int j = i; j < v; j++){
                if(i != j){
                    // 0 to maxWeight (exclusive) - add 1 to get 1 to maxWeight (inclusive)
                    result[i][j] = rn.nextInt(maxWeight) + 1;
                }else{
                    result[i][j] = 1;
                }
            }
        }
        for(int i = 0; i < v; i++){
            for(int j = 0; j < i; j++){
                result[i][j] = result[j][i];
            }
        }
        return result;
    }

    private static void adjacencyList(int[][] graph){
        System.out.println("[vertex]: (neighbors)");
        for(int i = 0; i < graph.length; i++){
            StringBuilder row = new StringBuilder("[" + i + "]: ");
            for(int j = 0; j < graph[i].length; j++){
                if(j != i){
                    row.append("(").append(j).append(")");
                }
            }
            System.out.println(row.toString());
        }
    }
}
