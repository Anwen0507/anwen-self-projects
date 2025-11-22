import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class QuickTree<K extends Number, V> {
    private class Node {
        private V value;
        private final K key;
        private transient Node left, right;
        private Node(K key, V value) {
            this.value = value;
            this.key = key;
        }
    }
    private final Comparator<? super K> comparator;
    private static final Comparator<Number> DEFAULT_COMPARATOR = (x, y) -> {
        return (int) x - (int) y;
    };
    private List<Node> nodes;
    private Node root;
    public QuickTree(K key, V value) {
        root = new Node(key, value);
        nodes = new LinkedList<>();
        comparator = DEFAULT_COMPARATOR;
    }
    public void balance() {
        root = null;
        int start = 0, end = nodes.size() - 1;
        while (start != end) {
            Node mid = findMidNode(start, end);
            root = add(root, mid.key, mid.value);
        }
    }
    private Node findMidNode(int start, int end) {
        int[] arr = new int[nodes.size()];
        int i = 0;
        for (Node e : nodes) {
            arr[i] = (int) e.key;
            i++;
        }
        QuickSort.quickSort(arr, start, end);
        int mid = (start + end)/2;
        final List<Node> orig = nodes;
        nodes.clear();
        i = 0;
        for (Node e : orig) {
            if ((int) e.key == arr[mid]) return e;
        }
        return null;
    }
    public Node add(K newKey, V newValue) {
        return add(root, newKey, newValue);
    }
    private Node add(Node oldNode, K newKey, V newValue) {
        Node n = new Node(newKey, newValue);
        if (oldNode == null) {
            return n;
        }
        if (comparator.compare(newKey, oldNode.key) < 0) {
            oldNode.left = add(oldNode.left, newKey, newValue);
        } else if (comparator.compare(newKey, oldNode.key) > 0) {
            oldNode.right = add(oldNode.right, newKey, newValue);
        }
        nodes.add(n);
        return oldNode;
    }
    /**
     * Utility function for printing a tree. Used in {@code toString()}.
     */
    private void display() {
        final int height = 5, width = 64;

        int len = width * height * 2 + 2;
        StringBuilder sb = new StringBuilder(len);
        for (int i = 1; i <= len; i++)
            sb.append(i < len - 2 && i % width == 0 ? "\n" : ' ');

        displayR(sb, width / 2, 1, width / 4, width, root, " ");
        System.out.println(sb);
    }

    /**
     * Utility function for printing a tree. Used in {@code display()}.
     */
    private void displayR(StringBuilder sb, int c, int r, int d, int w, Node n, String edge) {
        if (n != null) {
            displayR(sb, c - d, r + 2, d / 2, w, n.left, " /");

            String s = String.valueOf(n.key);
            int idx1 = r * w + c - (s.length() + 1) / 2;
            int idx2 = idx1 + s.length();
            int idx3 = idx1 - w;
            if (idx2 < sb.length())
                sb.replace(idx1, idx2, s).replace(idx3, idx3 + 2, edge);

            displayR(sb, c + d, r + 2, d / 2, w, n.right, "\\ ");
        }
    }
    public static void main(String[] args) {
        QuickTree<Integer, Integer> q = new QuickTree<>(1, 2);
        q.add(2, 3);
        q.add(3, 4);
        q.add(4, 5);
        q.add(5, 6);
        q.add(6, 7);
        q.add(7, 8);
        q.display();
        q.balance();
        q.display();
    }
}
