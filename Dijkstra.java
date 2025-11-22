import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;

public class Dijkstra<E> {
    private static class Vertex<E> {
        private E data;
        private List<Next<E>> neighbors;

        private Vertex(E data) {
            this.data = data;
            neighbors = new LinkedList<>();
        }

        public void addNeighbor(Vertex<E> neighbor, int weight) {
            neighbors.add(new Next<>(neighbor, weight));
        }

        public String toString() {
            return data + "";
        }
    }

    private static class Next<E> {
        private int weight;
        private Vertex<E> v;

        private Next(Vertex<E> v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }

    private static final int INFINITY = 0x7fffffff;

    /**
     * Implements Dijkstra algorithm to find the shortest distance from a root to
     * all Vertices. Uses a TreeMap for Vertex-Distance pairs because distance
     * depends on each instance of a problem.
     * 
     * @param root root from which we measure the distance
     * @return Map of shortest distances from the root to all Vertices
     */
    public Map<Vertex<E>, Integer> dijkstra(Vertex<E> root) {

        // Initialize distances to all vertices as infinity, associates distance to each vertex
        Map<Vertex<E>, Integer> distances = new HashMap<>();
        for (Vertex<E> v : breadthFirstTraversal(root))
            distances.put(v, INFINITY);
        distances.put(root, 0);

        // Define a function to compare vertices by their distances
        Comparator<Vertex<E>> comparator = (x, y) -> {
            return distances.get(x) - distances.get(y);
        };

        // Priority queue with custom comparator that orders vertices by distance
        PriorityQueue<Vertex<E>> unvisited = new PriorityQueue<>(comparator);
        for (Vertex<E> v : distances.keySet())
            unvisited.add(v);
        
        // Final map of distances
        Map<Vertex<E>, Integer> visited = new HashMap<>();

        // Iterate until all vertices are visited
        while (!unvisited.isEmpty()) {

            // Get the vertex with the smallest distance
            Vertex<E> min = unvisited.remove();

            // Add it to the visited set
            visited.put(min, distances.get(min));

            // Update distances to its neighbors
            for (Next<E> e : min.neighbors) {

                // Weight between min and neighbor
                int weight = e.weight;

                // Neighbor vertex
                Vertex<E> v = e.v;

                // Distance calculated by going through min
                int sum = distances.get(min) + weight;

                // Original distance recorded in distances map
                int original = distances.get(v);

                // Update distance if the new calculated distance is smaller
                distances.put(v, sum > original ? original : sum);
            }
        }
        return visited;
    }

    private List<Vertex<E>> breadthFirstTraversal(Vertex<E> start) {
        List<Vertex<E>> visited = new LinkedList<>();
        Queue<Vertex<E>> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            Vertex<E> v = q.remove();
            if (!visited.contains(v)) {
                visited.add(v);
                for (Next<E> e : v.neighbors)
                    q.add(e.v);
            }
        }
        return visited;
    }

    /**
     * Creates a test set for the Dijkstra Algorithm.
     * 
     * @return
     */
    public Vertex<Integer> init() {
        Vertex<Integer> v = new Vertex<>(5);
        Vertex<Integer> n1 = new Vertex<>(7), n2 = new Vertex<>(9);
        v.addNeighbor(n1, 6);
        v.addNeighbor(n2, 3);
        n2.addNeighbor(n1, 5);
        Vertex<Integer> n3 = new Vertex<>(8);
        v.addNeighbor(n3, 4);
        n3.addNeighbor(n2, 10);
        Vertex<Integer> n4 = new Vertex<>(1);
        n3.addNeighbor(n4, 8);
        n2.addNeighbor(n4, 7);
        return v;
    }

    public static void main(String[] args) {
        Dijkstra<Integer> d = new Dijkstra<>();
        Vertex<Integer> v = d.init();
        System.out.println(d.breadthFirstTraversal(v));
        System.out.println(d.dijkstra(v));
    }
}
