import java.util.Queue;
import java.util.ArrayDeque;
import java.util.List;
import java.util.LinkedList;

public interface Tree {
    int IMPOSSIBLE = -1;
    default List<TreeNode> depthFirstTraversal(TreeNode root, List<TreeNode> path) {
        if (root != null) {
            path.add(root);
            depthFirstTraversal(root.getLeft(), path);
            depthFirstTraversal(root.getRight(), path);
        }
        return path;
    }
    default List<TreeNode> breadthFirstTraversal(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<TreeNode> visited = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            visited.add(node);
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
        return visited;
    }
    default TreeNode mirror(TreeNode t) {
        return t == null
            ? null
            : new TreeNode(t.getValue(), mirror(t.getRight()), mirror(t.getLeft()));
    }
    default int maxDepth(TreeNode t) {
        if (t == null) return 0;
        int left = 1 + maxDepth(t.getLeft()), right = 1 + maxDepth(t.getRight());
        return left > right ? left : right;
    }
    default int countNonLeafNodes(TreeNode t) {
        if (t == null) return 0;
        if (t.getLeft() == null && t.getRight() == null) return 0;
        return 1 + countNonLeafNodes(t.getLeft()) + countNonLeafNodes(t.getRight());
    }
    default int countInternalNodes(TreeNode t) {
        return countNonLeafNodes(t) - 1;
    }
    default int greaterOrEqual(TreeNode t, int val) {
        if (t == null) return 0;
        int orig = (int) t.getValue();
        int left = greaterOrEqual(t.getLeft(), val), right = greaterOrEqual(t.getRight(), val);
        return orig >= val ? orig + left + right : left + right;
    }
    default int less(TreeNode t, int val) {
        if (t == null) return 0;
        int orig = (int) t.getValue();
        int left = less(t.getLeft(), val), right = less(t.getRight(), val);
        return orig < val ? orig + left + right : left + right;
    }
    default int sumDiff(TreeNode t, int val) {
        return greaterOrEqual(t, val) - less(t, val);
    }
    default TreeNode removeAll(TreeNode t, int val) {
        if (t == null) return null;
        if (t.getLeft() == null && t.getRight() == null) {
            int orig = (int) t.getValue();
            return orig == val ? null : t;
        }
        t.setLeft(removeAll(t.getLeft(), val));
        t.setRight(removeAll(t.getRight(), val));
        return t;
    }
    default TreeNode init(int lev, Object val) {
        return lev == 0
            ? null 
            : new TreeNode(val, init(lev-1, val), init(lev-1, val));
    }
    public static void main(String[] args) {
        TreeNode t = new TreeNode(1,
            new TreeNode(0, 
                new TreeNode(1), 
                new TreeNode(1)), 
            new TreeNode(1, 
                new TreeNode(0), 
                new TreeNode(0,
                    new TreeNode(1),
                    new TreeNode(0))));
        // TreeNode mirror = t.mirror(t);
        System.out.println(t.breadthFirstTraversal(t.removeAll(t, 1)));
    }
}
