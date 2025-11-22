public class Trie {
    private class Node {
        private String letter;
        private Node[] children;
        private transient Node parent;
        private transient boolean end;
        private static final int ALPHABET = 26;
        private Node(String letter) {
            this.letter = letter;
            children = new Node[ALPHABET];
        }
        public String toString() {
            return letter;
        }
    }
    private final Node root;
    public Trie() {
        root = new Node("");
    }
    public Trie(String[] strings) {
        this();
        for (String e : strings) {
            add(e);
        }
    }
    public Trie(String str) {
        this();
        add(str);
    }
    public void add(String str) {
        add(str.toLowerCase(), root);
    }
    private void add(String str, Node start) {
        if (str.isEmpty()) {
            start.parent.end = true;
            return;
        }
        int i = str.charAt(0) - 'a';
        if (start.children[i] == null) {
            Node node = new Node(str.substring(0, 1));
            start.children[i] = node;
            node.parent = start;
        }
        add(str.substring(1), start.children[i]);
    }
    public boolean exists(String finding) {
        return exists(finding, root, 0);
    }
    private boolean exists(String finding, Node current, int start) {
        if (current == null) {
            return false;
        }
        if (start >= finding.length()) {
            return current.parent.end;
        }
        int i = finding.charAt(start) - 'a';
        return exists(finding, current.children[i], start+1);
    }
}