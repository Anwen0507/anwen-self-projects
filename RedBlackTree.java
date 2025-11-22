import java.util.LinkedList;
import java.util.List;
// import java.util.TreeMap;

/**
 * RedBlackTrees are a type of self-balancing {@link SearchTree}. Each node has
 * a left child, right child, and parent, in addition to an extra instance
 * variable: the color. RedBlackTrees abide by several rules:
 * <p>
 * Every node is either red or black.
 * <p>
 * The root is always black.
 * <p>
 * A red node cannot have a red child.
 * <p>
 * Null leafs are considered black.
 * <p>
 * The number of black nodes must be equal on both left and right in order to be
 * balanced.
 * <p>
 * In order to self-balance, it must have rotation abilities (left and right
 * rotate). Rotations do not affect the {@link SearchTree} contract.
 * 
 * @author Anwen Hao
 * @version 1.0
 * @see SearchTree
 * @see Node
 * @see {@code rotateLeft(Node node)}
 * @see {@code rotateRight(Node node)}
 */
public class RedBlackTree<K extends Number, V> {
    /**
     * Like any binary tree, RedBlackTrees are recursive. Each Node also has
     * information about the parent node and the left and right children.
     */
    private class Node {
        private final K key;
        private V value;
        private transient Node parent, left, right;
        private transient int color;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "key: " + key + " color: " + valueOf(color);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object obj) {
            if (obj instanceof RedBlackTree.Node) {
                Node node = (Node) obj;
                return node.key == this.key && node.value == this.value;
            }
            return false;
        }

        /**
         * Turns red or black constants into understandable Strings.
         * 
         * @param num constant representing a color
         * @return String value of the color
         */
        private String valueOf(int num) {
            if (num == 0) {
                return "RED";
            }
            return "BLACK";
        }
    }

    private Node root;
    private static final int RED = 0;
    private static final int BLACK = 1;

    public RedBlackTree(K key, V value) {
        root = new Node(key, value);
        root.color = BLACK;
    }

    /**
     * Rotates a subtree with node as a root to the left. Maintains SearchTree
     * contract, essential to balancing the tree.
     * 
     * @param node node from which rotation is oriented around
     */
    public void rotateLeft(Node node) {
        if (node == null || node.right == null) {
            return;
        }
        Node rightchild = node.right;
        node.right = rightchild.left;
        if (rightchild.left != null) {
            rightchild.left.parent = node;
        }
        rightchild.parent = node.parent;
        if (node.parent == null) {
            root = rightchild;
        } else if (node == node.parent.left) {
            node.parent.left = rightchild;
        } else {
            node.parent.right = rightchild;
        }
        rightchild.left = node;
        node.parent = rightchild;
    }

    /**
     * Rotates a subtree with node as a root to the right. Maintains SearchTree
     * contract, essential to balancing the tree.
     * 
     * @param node node from which rotation is oriented around
     */
    public void rotateRight(Node node) {
        if (node == null || node.left == null) {
            return;
        }
        Node leftchild = node.left;
        node.left = leftchild.right;
        if (leftchild.right != null) {
            leftchild.right.parent = node;
        }
        leftchild.parent = node.parent;
        if (node.parent == null) {
            root = leftchild;
        } else if (node == node.parent.left) {
            node.parent.left = leftchild;
        } else {
            node.parent.right = leftchild;
        }
        leftchild.right = node;
        node.parent = leftchild;
    }

    public void balance(Node node) {
        if (node.parent == null || node.parent.parent == null) {
            return;
        }
        while (node.parent != null && node.parent.color == RED) {
            if (node.parent == node.parent.parent.left) {
                Node r = node.parent.parent.right;
                if (r != null && r.color == RED) {
                    node.parent.color = BLACK;
                    r.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else if (node == node.parent.right) {
                    node = node.parent;
                    rotateLeft(node);
                    node.parent.color = BLACK;
                    if (node.parent.parent != null) {
                        node.parent.parent.color = RED;
                    }
                    rotateRight(node.parent.parent);
                }
            } else {
                Node l = node.parent.parent.left;
                if (l != null && l.color == RED) {
                    node.parent.color = BLACK;
                    l.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else if (node == node.parent.left) {
                    node = node.parent;
                    rotateLeft(node);
                    node.parent.color = BLACK;
                    if (node.parent.parent != null) {
                        node.parent.parent.color = RED;
                    }
                    rotateRight(node.parent.parent);
                }
            }
        }
    }

    /**
     * Adds an element to the tree.
     * 
     * @param key   key of the element to be added
     * @param value value of the element to be added
     */
    public Node add(K key, V value) {
        return add(root, key, value);
    }

    /**
     * Private implementation of the insertion.
     * 
     * @param current Node the element is being compared to
     * @param key     key of the new element
     * @param value   value of the new element
     * @return added element
     */
    private Node add(Node current, K key, V value) {
        if (current == null) {
            return new Node(key, value);
        }
        if ((int) current.key < (int) key) {
            current.right = add(current.right, key, value);
            current.right.parent = current;
            balance(current.right);
        } else if ((int) current.key > (int) key) {
            current.left = add(current.left, key, value);
            current.left.parent = current;
            balance(current.left);
        }
        return current;
    }

    /**
     * Searches for an element in the tree. Exemplifies the most fundamental
     * functionality of a balanced SearchTree.
     * 
     * @param key   key of node to be searched
     * @param value value of node to be searched
     * @return node if found, null otherwise
     */
    public Node search(K key, V value) {
        return search(root, new Node(key, value));
    }

    /**
     * Private implementation of searching.
     * 
     * @param current node being compared to
     * @param node    node being searched
     * @return node if found, null otherwise
     */
    private Node search(Node current, Node node) {
        if (current == null) {
            return null;
        }
        if (current.equals(node)) {
            return current;
        }
        if ((int) node.key > (int) current.key) {
            current = search(current.right, node);
        } else if ((int) node.key < (int) current.key) {
            current = search(current.left, node);
        }
        return current;
    }

    /**
     * Calculates the depth of a subtree from a node.
     * 
     * @param node root from which depth will be calculated
     * @return depth of the subtree
     */
    public int depth(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.color == BLACK) {
            return 1 + depth(node.left) + depth(node.right);
        }
        return depth(node.left) + depth(node.right);
    }

    public List<Node> traverse(Node start, List<Node> list) {
        if (start != null) {
            traverse(start.left, list);
            list.add(start);
            traverse(start.right, list);
        }
        return list;
    }

    /**
     * Colors a subtree based on contract. Process starts from node parameter.
     * 
     * @param node node at which the coloring starts
     */
    public void color(Node node) {
        if (node == null) {
            return;
        }
        if (node.parent == null) {
            node.color = BLACK;
        }
        if (node.left == null) {
            if (node.right != null) { // only left is null
                if (node.color == RED) {
                    node.right.color = BLACK;
                }
            }
        } else {
            if (node.right == null) { // only right is null
                if (node.color == RED) {
                    node.left.color = BLACK;
                }
            } else { // neither is null
                if (node.color == RED) {
                    node.left.color = BLACK;
                    node.right.color = BLACK;
                }
            }
        }
        color(node.left);
        color(node.right);
    }

    public void colorLeaf(Node leaf) {
        if (leaf.parent == null) {
            leaf.color = BLACK;
            return;
        }
        if (leaf.color == RED) {
            leaf.parent.color = BLACK;
        }
        colorLeaf(leaf.parent);
    }

    /**
     * Returns a String value for the tree.
     */
    @Override
    public String toString() {
        display();
        return "";
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
        RedBlackTree<Integer, Integer> tree = new RedBlackTree<Integer, Integer>(3, 7);
        for (int i = 0; i < 15; i++) {
            int random = 1 + (int) (Math.random() * 20);
            tree.add(random, random * 3);
            // tree.color(tree.root);
        }
        System.out.println(tree);
        System.out.println(tree.traverse(tree.root, new LinkedList<>()));
    }
}
