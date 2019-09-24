import java.util.*;

public class MST {
    private List<Edge> edges;
    private int val;

    public MST(List<Edge> edges, int val) {
        this.edges = new ArrayList<>(edges);
        this.val = val;
    }

    public List<Edge> getEdges() {
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
        Set<Edge> edgeSet = new HashSet<>(edges);
        return Objects.hash(edgeSet, val);
    }
}
