import java.util.Queue;
import java.util.PriorityQueue;
import java.util.List;
import java.util.LinkedList;

public interface TopologicalSort {
    public static List<Integer> sort(int[][] graph) {
        List<Integer> sorted = new LinkedList<>();
        Queue<Integer> zeroInDegree = new PriorityQueue<>();
        int[] inDegree = new int[graph.length];
        for (int i = 0; i < graph.length; i++)
            if (graph[i] == null)
                inDegree[i] = -1;
            else
                for (int neighbor : graph[i])
                    inDegree[neighbor]++;
        for (int i = 0; i < inDegree.length; i++)
            if (inDegree[i] == 0)
                zeroInDegree.add(i);
        while (!zeroInDegree.isEmpty()) {
            int v = zeroInDegree.remove();
            sorted.add(v);
            for (int neighbor : graph[v]) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0)
                    zeroInDegree.add(neighbor);
            }
        }
        return sorted;
    }

    public static int[][] init() {
        int[][] graph = {
            null,
            null,
            {},
            {8, 10},
            null,
            {11},
            null,
            {11, 8},
            {9},
            {},
            {},
            {2, 9, 10}
        };
        return graph;
    }

    public static void main(String[] args) {
        System.out.println(sort(init()));
    }
}
