import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Edge {
//    private Set<Integer> vertices;
    private int startNode;
    private int endNode;
    private int weight;

    public Edge(int startNode, int endNode, int weight) {
//        vertices = new HashSet<>();
//        vertices.add(startNode);
//        vertices.add(endNode);
        this.startNode=startNode;
        this.endNode=endNode;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "{ (" + startNode + "," + endNode + "), Weight: " + weight + '}';
    }

    public int getWeight(){
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return weight == edge.weight &&
                startNode == edge.startNode &&
                endNode == edge.endNode;
    }
}
