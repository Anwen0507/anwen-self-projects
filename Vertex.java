import java.util.List;
import java.util.LinkedList;

public class Vertex<E> implements BreadthFirstSearch {
    private E data;
    private List<Vertex<E>> neighbors;
    public Vertex(E data) {
        this.data = data;
        this.neighbors = new LinkedList<>();
    }
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (obj instanceof Vertex) {
            Vertex<E> v = (Vertex<E>) obj;
            return v.getData().equals(this.getData());
        }
        return false;
    }
    public E getData() {
        return data;
    }
    public List<Vertex<E>> getNeigbors() {
        return neighbors;
    }
    public void setData(E data) {
        this.data = data;
    }
    public void addNeighbor(Vertex<E> neighbor) {
        neighbors.add(neighbor);
    }
    public static Vertex<Integer> init() {
        final int initial = 3;
        final Vertex<Integer> root = new Vertex<Integer>(initial);
        for (int i = 1; i < 6; i++) {
            root.addNeighbor(new Vertex<Integer>(i));
            for (Vertex<Integer> e : root.neighbors) {
                e.addNeighbor(new Vertex<Integer>(i));
            }
        }
        return root;
    }
    public String toString() {
        return data + " -> " + neighbors;
    }
}
