import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide how many vertices you would like to create a complete graph from.");
        int vertices = getValidInt(scanner);

        System.out.println("Please provide the maximum weight for an edge");
        int maxWeight = getValidInt(scanner);

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
        }

        System.out.println();
        System.out.println("Size of the graph: [Vertices: " + vertices + ", Edges: " + numEdges + "]");
        System.out.println("Lowest MST value: " + minVal);
        System.out.println("Unique MSTs created: " + uniqueMSTs.size());
        System.out.println();

        System.out.println("Would you like to see the adjacency list of the graph? (Y/N)");
        boolean wantsAdjacencyList = getYesNo(scanner).equals("Y");
        System.out.println();
        if(wantsAdjacencyList){
            adjacencyList(graph);
            System.out.println();
        }

        System.out.println("Would you like to see the edge list of the MST created from the first vertex? (Y/N)");
        boolean wantsEdgeList = getYesNo(scanner).equals("Y");
        if(wantsEdgeList){
            MST mstFrom0 = primsAlgorithm(graph, 0);
            List<Edge> edges = mstFrom0.getEdges();
            int mstVal = 0;
            for(Edge e: edges){
                mstVal += e.getWeight();
            }
            System.out.println(edges.toString());
            System.out.println("MST Value: " + mstVal);
        }
    }

    private static MST primsAlgorithm(int[][] graph, int startVertex){
        List<Edge> edgesTaken = new ArrayList<>();
        Set<Edge> edgesAvailable = new HashSet<>();
        Edge edgeRecentlyTaken = null;
        Set<Integer> discoveredVertices = new HashSet<>();
        int i = startVertex;
        discoveredVertices.add(i);
        while(discoveredVertices.size() < graph.length){
            int[] neighbors = graph[i];
            for(int j=0; j < neighbors.length; j++){
                if(j != i){
                    Edge newAvailable = new Edge(i, j, neighbors[j]);
                    edgesAvailable.add(newAvailable);
                }
            }
            if(edgeRecentlyTaken != null){
                edgesAvailable.remove(edgeRecentlyTaken);
                edgeRecentlyTaken = null;
            }
            int lightestWeight = Integer.MAX_VALUE;
            for(Edge e: edgesAvailable){
                if(e.getWeight() < lightestWeight){
                    lightestWeight = e.getWeight();
                    edgeRecentlyTaken = e;
                }
            }
            if(edgeRecentlyTaken != null){
                edgesTaken.add(edgeRecentlyTaken);
                i = edgeRecentlyTaken.getEndNode();
                discoveredVertices.add(i);
            }
        }
        int val = 0;
        for(Edge e: edgesTaken){
            val += e.getWeight();
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

    private static int getValidInt(Scanner scanner){
        int result = 0;
        while(result == 0){
            try{
                result = Integer.parseInt(scanner.next());
            }catch(Exception e){
                System.out.println("Please try again with an integer value");
                result = 0;
            }
        }
        return result;
    }

    private static String getYesNo(Scanner scanner){
        String result = "";
        while(result.equals("")){
            result = scanner.next().toUpperCase();
            if(!result.equals("Y") && !result.equals("N")) {
                System.out.println("Please provide Y or N");
                result = "";
            }
        }
        return result;
    }
}
