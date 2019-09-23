import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[][] graph1 = completeGraphWeightGenerator(50, 10);
//        System.out.println("First graph");
//        for(int i = 0; i < graph1.length; i++){
//            for(int j = 0; j < graph1[0].length; j++){
//                System.out.print("|" + graph1[i][j] + "|");
//            }
//            System.out.println();
//        }
        Set<Set<Edge>> uniqueMSTs = new HashSet<>();
        for(int i = 0; i < 50; i++){
            Set<Edge> mst = primsAlgorithm(graph1, i);
            int value = 0;
            for(Edge e: mst){
                value += e.getWeight();
            }
            System.out.println(value);
            uniqueMSTs.add(mst);
        }
        System.out.println(uniqueMSTs.size());
    }

    public static Set<Edge> primsAlgorithm(int[][] graph, int startVertex){
        Set<Edge> result = new HashSet<>();
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
    public static int[][] completeGraphWeightGenerator(int v, int maxWeight){
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

    public static List<List<Integer>> adjacencyList(int[][] graph){
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
