public class TreeNode implements Tree {
    private Object value;
    private TreeNode right, left, parent;
    public TreeNode(Object value, TreeNode left, TreeNode right, TreeNode parent) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
    public TreeNode(Object value, TreeNode left, TreeNode right) {
        this(value, left, right, null);
    }
    public TreeNode(Object value) {
        this(value, null, null, null);
    }
    public Object getValue() { return value; }
    public TreeNode getRight() { return right; }
    public TreeNode getLeft() { return left; }
    public TreeNode getParent() { return parent; }
    public void setValue(Object v) { value = v; }
    public void setRight(TreeNode r) { right = r; }
    public void setLeft(TreeNode l) { left = l; }
    public void setParent(TreeNode p) { parent = p; }
    public String toString() { return "" + value; }
}
