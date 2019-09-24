import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Edge {
    private Set<Integer> vertices;
    private int weight;

    public Edge(int startNode, int endNode, int weight) {
        vertices = new HashSet<>();
        vertices.add(startNode);
        vertices.add(endNode);
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "{Vertices: " + vertices.toString() + ", Weight: " + weight + '}';
    }

    public int getWeight(){
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return this.weight == edge.weight &&
                this.vertices.containsAll(edge.vertices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices, weight);
    }
}
