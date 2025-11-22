import java.util.Comparator;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Classic application for the Queue Data Structure. Queue is used so the
 * elements pushed into the queue will come out in the same order during
 * searching. Similar algorithms like the Dijkstra algorithm utilizes the
 * Priority Queue.
 * 
 * @author Anwen Hao
 * @see java.util.Queue
 * @see Dijkstra
 */
public interface BreadthFirstSearch {
    int INFINITY = 0x7fffffff;

    /**
     * Introduces a strange concept known as an "abstract grid". This algorithm
     * finds the shortest distance between two points on a matrix, but it does not
     * actually use an int[][] grid, but instead relies solely on the size given and
     * the start and end indices. One reason this is possible is because the actual
     * values in the grid are not important in terms of solving the problem. The
     * algorithm uses the Breadth First Search strategy, so a Queue is used. Each
     * node is represented by an array of 2 integers, where the first is the row and
     * the second is the column.
     * 
     * @param size     size of the "grid"
     * @param startRow row of the start point
     * @param startCol column of the start point
     * @param endRow   row of the end point
     * @param endCol   column of the end point
     * @return the shortest distance between two points
     */
    default int hamiltonian(int size, int startRow, int startCol, int endRow, int endCol) {
        final int[] row = { 1, -1, 0, 0 }, col = { 0, 0, 1, -1 };
        Queue<int[]> q = new ArrayDeque<>();
        Queue<Integer> dist = new ArrayDeque<>();
        List<int[]> visited = new LinkedList<>();
        int[] start = { startRow, startCol };
        q.add(start);
        dist.add(0);
        while (!q.isEmpty()) {
            int[] co = q.remove();
            int r = co[0], c = co[1], d = dist.remove();
            if (r == endRow && c == endCol) {
                return d;
            }
            if (!visited.contains(co)) {
                visited.add(co);
                int nextDist = d + 1;
                for (int i = 0; i < row.length; i++) {
                    final int nextRow = r + row[i], nextCol = c + col[i];
                    if (safe(nextRow, nextCol, size)) {
                        int[] next = { nextRow, nextCol };
                        dist.add(nextDist);
                        q.add(next);
                    }
                }
            }
        }
        return INFINITY;
    }

    /**
     * Finds the shortest distance between two points of a graph. Uses Breadth First
     * Search (with a Queue).
     * 
     * @param start start point
     * @param end   end point
     * @return shortest distance between start and end
     */
    default int hamiltonian(Vertex<Integer> start, Vertex<Integer> end) {
        Comparator<Vertex<Integer>> c = (x, y) -> {
            return x.getData() - y.getData();
        };
        List<Vertex<Integer>> visited = new LinkedList<>();
        Map<Vertex<Integer>, Integer> dist = new TreeMap<>(c);
        Queue<Vertex<Integer>> q = new ArrayDeque<>();
        q.add(start);
        dist.put(start, 0);
        while (!q.isEmpty()) {
            Vertex<Integer> v = q.remove();
            if (v.equals(end)) {
                return dist.get(v);
            }
            if (!visited.contains(v)) {
                visited.add(v);
                final int nextDist = dist.get(v) + 1;
                for (Vertex<Integer> e : v.getNeigbors()) {
                    q.add(e);
                    dist.put(e, nextDist);
                }
            }
        }
        return INFINITY;
    }

    private boolean safe(int r, int c, int length) {
        return r >= 0 && r < length && c >= 0 && c < length;
    }

    public static void main(String[] args) {
        Vertex<Integer> v = new Vertex<>(null);
        System.out.println(v.hamiltonian(10, 0, 0, 9, 9));
    }
}
