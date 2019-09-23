import java.util.ArrayList;
import java.util.List;

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

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
