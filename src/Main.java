import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[][] graph1 = completeGraphWeightGenerator(5, 10);
        int[][] graph2 = completeGraphWeightGenerator(5, 10);
        System.out.println("First graph");
        for(int i = 0; i < graph1.length; i++){
            for(int j = 0; j < graph1[0].length; j++){
                System.out.print("|" + graph1[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("Second graph");
        for(int i = 0; i < graph2.length; i++){
            for(int j = 0; j < graph2[0].length; j++){
                System.out.print("|" + graph2[i][j] + "|");
            }
            System.out.println();
        }
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
}
