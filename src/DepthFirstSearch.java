import java.util.*;

public class DepthFirstSearch<V> implements Search<V> {
    private Map<V, V> edgeTo = new HashMap<>();
    private Set<V> visited = new HashSet<>();
    private V start;

    public DepthFirstSearch(UnweightedGraph<V> graph, V start) {
        this.start = start;
        dfs(graph, start);
    }

    private void dfs(UnweightedGraph<V> graph, V current) {
        visited.add(current);
        for (V neighbor : graph.getAdjacencyList(current)) {
            if (!visited.contains(neighbor)) {
                edgeTo.put(neighbor, current);
                dfs(graph, neighbor);
            }
        }
    }

    @Override
    public List<V> pathTo(V destination) {
        if (!visited.contains(destination)) return List.of();
        LinkedList<V> path = new LinkedList<>();
        for (V x = destination; x != start; x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(start);
        return path;
    }
}
