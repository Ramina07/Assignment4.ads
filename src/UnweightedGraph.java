import java.util.*;

public class UnweightedGraph<V> {
    private Map<V, List<V>> adjList = new HashMap<>();
    private boolean directed;

    public UnweightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(V source, V dest) {
        adjList.computeIfAbsent(source, k -> new ArrayList<>()).add(dest);
        if (!directed) {
            adjList.computeIfAbsent(dest, k -> new ArrayList<>()).add(source);
        }
    }

    public List<V> getAdjacencyList(V vertex) {
        return adjList.getOrDefault(vertex, new ArrayList<>());
    }
}
