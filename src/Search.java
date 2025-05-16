import java.util.List;

public interface Search<V> {
    List<V> pathTo(V destination);
}
