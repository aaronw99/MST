import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide how many vertices you would like to create a complete graph from.");
        int vertices = scanner.nextInt();
        System.out.println("Please provide the maximum weight for an edge");
        int maxWeight = scanner.nextInt();
        int[][] graph = completeGraphWeightGenerator(vertices, maxWeight);
        Set<List<Edge>> uniqueMSTs = new HashSet<>();
        int minVal = Integer.MAX_VALUE;
        int numEdges = 0;
        for(int i = 0; i < vertices; i++){
            List<Edge> mst = primsAlgorithm(graph, i);
            uniqueMSTs.add(mst);
            int mstVal = 0;
            for(Edge e : mst){
                mstVal += e.getWeight();
            }
            if(mstVal < minVal){
                minVal = mstVal;
            }
        }
        System.out.println("Size of the graph: [Vertices: " + vertices + ", Edges: " + numEdges + "]");
        System.out.println("MST Value: " + minVal);
        System.out.println("Unique MSTs: " + uniqueMSTs.size());
    }

    private static List<Edge> primsAlgorithm(int[][] graph, int startVertex){
        List<Edge> result = new ArrayList<>();
        Set<Integer> discoveredVertices = new HashSet<>();
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
            Edge taken = new Edge(start, next, min);
            start = next;
            discoveredVertices.add(next);
            result.add(taken);
        }
        return result;
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

    private static List<List<Integer>> adjacencyList(int[][] graph){
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < graph.length; i++){
            List<Integer> entry = new ArrayList<>();
            for(int j : graph[i]){
                entry.add(j);
            }
            result.add(entry);
        }
        return result;
    }
}
