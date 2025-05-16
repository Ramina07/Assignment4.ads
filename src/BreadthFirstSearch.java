import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private Map<V, V> edgeTo = new HashMap<>();
    private Set<V> visited = new HashSet<>();
    private V start;

    public BreadthFirstSearch(UnweightedGraph<V> graph, V start) {
        this.start = start;
        bfs(graph, start);
    }

    private void bfs(UnweightedGraph<V> graph, V start) {
        Queue<V> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            for (V neighbor : graph.getAdjacencyList(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    edgeTo.put(neighbor, current);
                    queue.add(neighbor);
                }
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
