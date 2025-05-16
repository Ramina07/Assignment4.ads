import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {
    private boolean directed;
    private Map<V, Vertex<V>> vertices;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
        this.vertices = new HashMap<>();
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> sourceVertex = vertices.computeIfAbsent(source, Vertex::new);
        Vertex<V> destVertex = vertices.computeIfAbsent(dest, Vertex::new);

        sourceVertex.addAdjacentVertex(destVertex, weight);
        if (!directed) {
            destVertex.addAdjacentVertex(sourceVertex, weight);
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Collection<Vertex<V>> getAllVertices() {
        return vertices.values();
    }
}
