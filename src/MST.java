import java.util.ArrayList;
import java.util.List;

public class MST {
    private List<Edge> edgeList;
    private int val;

    public MST(List<Edge> edgeList, int val) {
        this.edgeList = new ArrayList<>(edgeList);
        this.val = val;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public int getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "MST{" +
                "edgeList=" + edgeList +
                ", val=" + val +
                '}';
    }
}
