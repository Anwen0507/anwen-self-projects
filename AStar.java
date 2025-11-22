import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class AStar<E> {
    private static class Node<E> {
        private E data;
        private transient Node<E> parent;
        private final int[] loc;
        private List<Contig<E>> neighbors;

        private Node(int r, int c, E data) {
            neighbors = new LinkedList<>();
            loc = new int[2];
            loc[0] = r;
            loc[1] = c;
            this.data = data;
        }

        private void addNeighbor(Node<E> neighbor, int weight) {
            neighbors.add(new Contig<>(neighbor, weight));
        }

        public String toString() {
            return data + " at location: (" + loc[0] + ", " + loc[1] + ")";
        }
    }

    private static class Contig<E> {
        private Node<E> n;
        private int weight;

        private Contig(Node<E> n, int weight) {
            this.n = n;
            this.weight = weight;
        }
    }

    private static final int INFINITY = 0x7fffffff;

    public List<Node<E>> astar(Node<E> start, Node<E> end) {
        Map<Node<E>, Integer> gScore = new HashMap<>(), fScore = new HashMap<>();
        final Comparator<Node<E>> comparator = (x, y) -> {
            return fScore.get(x) - fScore.get(y);
        };
        PriorityQueue<Node<E>> visited = new PriorityQueue<>(comparator);
        List<Node<E>> l = breadthFirstTraversal(start);
        for (Node<E> e : l) {
            gScore.put(e, INFINITY);
            fScore.put(e, INFINITY);
        }
        final int startR = start.loc[0], startC = start.loc[1];
        final int endR = end.loc[0], endC = end.loc[1];
        int h = euclideanDist(startR, startC, endR, endC);
        gScore.put(start, 0);
        fScore.put(start, h);
        visited.add(start);
        while (!visited.isEmpty()) {
            Node<E> curr = visited.remove();
            int[] coordinates = curr.loc;
            int r = coordinates[0], c = coordinates[1];
            if (r == endR && c == endC) {
                List<Node<E>> path = new LinkedList<>();
                while (curr != null) {
                    path.add(0, curr);
                    curr = curr.parent;
                }
                return path;
            }
            for (Contig<E> e : curr.neighbors) {
                Node<E> n = e.n;
                int g = gScore.get(curr) + e.weight;
                if (g < gScore.get(n)) {
                    n.parent = curr;
                    int nr = n.loc[0], nc = n.loc[1];
                    int heuristic = euclideanDist(nr, nc, endR, endC);
                    int f = g + heuristic;
                    fScore.put(n, f);
                    gScore.put(n, g);
                }
                visited.add(n);
            }
        }
        return null;
    }

    private List<Node<E>> breadthFirstTraversal(Node<E> start) {
        List<Node<E>> visited = new LinkedList<>();
        Queue<Node<E>> q = new ArrayDeque<>();
        q.add(start);
        while (!q.isEmpty()) {
            Node<E> v = q.remove();
            if (!visited.contains(v)) {
                visited.add(v);
                for (Contig<E> e : v.neighbors) {
                    q.add(e.n);
                }
            }
        }
        return visited;
    }

    private int euclideanDist(int startR, int startC, int endR, int endC) {
        int sqrX = (endC - startC) * (endC - startC);
        int sqrY = (endR - startR) * (endR - startR);
        return (int) Math.sqrt(sqrX + sqrY);
    }

    public List<Node<Integer>> init() {
        Node<Integer> v = new Node<>(0, 0, 4);
        List<Contig<Integer>> neighbors = v.neighbors;

        Node<Integer> n1 = new Node<>(3, 2, 2);
        int dist = euclideanDist(0, 0, 3, 2);
        // System.out.println(dist);
        v.addNeighbor(n1, dist);

        Node<Integer> n2 = new Node<>(1, 6, 5);
        dist = euclideanDist(0, 0, 1, 6);
        // System.out.println(dist);
        v.addNeighbor(n2, dist);

        Node<Integer> n3 = new Node<>(3, 5, 7);
        neighbors.get(0).n.addNeighbor(n3, 3);
        dist = euclideanDist(1, 6, 3, 5);
        // System.out.println(dist);
        neighbors.get(1).n.addNeighbor(n3, dist);

        Node<Integer> n4 = new Node<>(4, 8, 3);
        dist = euclideanDist(1, 6, 4, 8);
        // System.out.println(dist);
        neighbors.get(1).n.addNeighbor(n4, dist);
        dist = euclideanDist(3, 5, 4, 8);
        // System.out.println(dist);
        neighbors.get(0).n.neighbors.get(0).n.addNeighbor(n4, dist);

        Node<Integer> n5 = new Node<>(9, 7, 3);
        dist = euclideanDist(3, 2, 9, 7);
        // System.out.println(dist);
        neighbors.get(0).n.addNeighbor(n5, dist);
        dist = euclideanDist(4, 8, 9, 7);
        // System.out.println(dist);
        neighbors.get(1).n.neighbors.get(0).n.addNeighbor(n5, dist);

        Node<Integer> n6 = new Node<>(7, 9, INFINITY);
        dist = euclideanDist(9, 7, 7, 9);
        // System.out.println(dist);
        neighbors.get(0).n.neighbors.get(1).n.addNeighbor(n6, dist);
        dist = euclideanDist(4, 8, 7, 9);
        // System.out.println(dist);
        neighbors.get(1).n.neighbors.get(0).n.addNeighbor(n6, dist);

        List<Node<Integer>> l = new LinkedList<>();
        l.add(v);
        l.add(n6);
        return l;
    }

    public static void main(String[] args) {
        AStar<Integer> a = new AStar<>();
        List<Node<Integer>> l = a.init();
        System.out.println(a.breadthFirstTraversal(l.get(0)));
        System.out.println(a.astar(l.get(0), l.get(1)));
    }
}
