import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private Map<V, Double> distances = new HashMap<>();
    private Map<V, V> edgeTo = new HashMap<>();
    private Set<V> visited = new HashSet<>();
    private V start;

    public DijkstraSearch(WeightedGraph<V> graph, V start) {
        this.start = start;
        dijkstra(graph, start);
    }

    private void dijkstra(WeightedGraph<V> graph, V startKey) {
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(Comparator.comparingDouble(v -> distances.getOrDefault(v.getData(), Double.POSITIVE_INFINITY)));

        Vertex<V> startVertex = graph.getVertex(startKey);
        distances.put(startKey, 0.0);
        pq.add(startVertex);

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();
            V currentData = current.getData();
            if (visited.contains(currentData)) continue;
            visited.add(currentData);

            for (Map.Entry<Vertex<V>, Double> neighbor : current.getAdjacentVertices().entrySet()) {
                V neighborData = neighbor.getKey().getData();
                double newDist = distances.get(currentData) + neighbor.getValue();

                if (newDist < distances.getOrDefault(neighborData, Double.POSITIVE_INFINITY)) {
                    distances.put(neighborData, newDist);
                    edgeTo.put(neighborData, currentData);
                    pq.add(neighbor.getKey());
                }
            }
        }
    }

    @Override
    public List<V> pathTo(V destination) {
        if (!distances.containsKey(destination)) return List.of();
        LinkedList<V> path = new LinkedList<>();
        for (V x = destination; x != start; x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(start);
        return path;
    }
}

