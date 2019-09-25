import java.util.*;

public class MST {
    private Set<Edge> edges;
    private int val;

    public MST(Set<Edge> edges, int val) {
        this.edges = new HashSet<>(edges);
        this.val = val;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public int getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "MST{" +
                "edges=" + edges.toString() +
                ", val=" + val +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MST mst = (MST) o;
        return this.val == mst.val &&
                this.edges.containsAll(mst.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(edges, val);
    }
}
