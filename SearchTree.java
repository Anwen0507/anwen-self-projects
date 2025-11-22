import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Implements SearchTree concept. Exceptionally quick searching and adding
 * because of the contract: left node key must be smaller than parent node,
 * right node key must be bigger than parent node. Basic capabilities include
 * adding and searching nodes, both of which are very versatile. Keys cannot be
 * changed once set and must be unique in the tree. SearchTreeSort is based on
 * this SearchTree data structure, although SearchTree's main purpose is to find
 * elements quickly. Heap is another type of binary tree, although its
 * restrictions are not as rigid as SearchTree's.
 * @see Node
 * @see Heap
 * @author Anwen Hao
 * @version 1.2
 * @param <K> key, may be any number
 * @param <V> value, may be any data type
 * @since 1.0
 */
public class SearchTree<K extends Number, V> {
    /**
     * Tree is a recursive data structure, so one node automatically creates two
     * child nodes that are null.
     * @author Anwen Hao
     * @version 1.0
     */
    private class Node {
        private final K key;
        private V value;
        private transient Node left, right;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Node root;
    private final Comparator<? super K> comparator;
    private static final Comparator<Number> DEFAULT_COMPARATOR = (x, y) -> {
        return (int) x - (int) y;
    };

    public SearchTree(K key, V value) {
        this(key, value, DEFAULT_COMPARATOR);
    }

    public SearchTree(K key, V value, Comparator<? super K> comparator) {
        root = new Node(key, value);
        this.comparator = comparator;
    }

    public Node add(K newKey, V newValue) {
        return add(root, newKey, newValue);
    }

    /**
     * Adds an element to the tree according to the contract.
     * 
     * @param oldNode the current node/target of the addition
     * @param newKey  the key of the added node
     * @return the new node in its correct position
     */
    private Node add(Node oldNode, K newKey, V newValue) {
        if (oldNode == null) {
            return new Node(newKey, newValue);
        }
        if (comparator.compare(newKey, oldNode.key) < 0) {
            oldNode.left = add(oldNode.left, newKey, newValue);
        } else if (comparator.compare(newKey, oldNode.key) > 0) {
            oldNode.right = add(oldNode.right, newKey, newValue);
        }
        return oldNode;
    }

    public List<K> sort() {
        List<K> l = new LinkedList<>();
        searchTreeSort(root, l);
        return l;
    }

    /**
     * Recursive definition to scan and capture all elements of tree, sorting the
     * keys in the process.
     * 
     * @param root the start position of traversal
     * @return sorted list of keys
     */
    private void searchTreeSort(Node root, List<K> keys) {
        if (root != null) {
            searchTreeSort(root.left, keys);
            keys.add(root.key);
            searchTreeSort(root.right, keys);
        }
    }

    public V get(K key) {
        return get(root, key);
    }

    /**
     * Retrieves Node value while traversing tree. Takes Log(n) time to find Node,
     * where n is the number of Nodes.
     * 
     * @param current the node the key is being compared to (one can think of this
     *                as the starting point of the search)
     * @param key     the key to be found
     * @return true if there is the element in the tree
     */
    private V get(Node current, K key) {
        if (current == null) {
            return null;
        }
        if (key.equals(current.key)) {
            return current.value;
        }
        return comparator.compare(key, current.key) < 0 
            ? get(current.left, key) 
            : get(current.right, key);
    }
}