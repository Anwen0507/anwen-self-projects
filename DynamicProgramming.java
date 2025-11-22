import java.util.Map;
import java.util.TreeMap;

public interface DynamicProgramming {
    Map<Integer, Integer> fibSolutions = new TreeMap<>(), 
    factSolutions = new TreeMap<>();
    /**
     * Technique that builds the solution from the simplest case.
     * Thus, this avoids traversing a calling tree twice,
     * as in the naive implementation of fibonacci sequence.
     * This further improves the memoization technique
     * by using O(1) of memory storage.
     * @param n number at the nth spot on the fibonacci sequence
     * @return  the nth fibonacci number
     */
    @OrdersOfGrowth("n")
    static int bottomUpFib(int n) {
        if (n <= 1) {
            return n;
        }
        int prev = 0;
        int current = 1;
        for (int i = 0; i < n-1; i++) {
            int newFib = prev + current;
            prev = current;
            current = newFib;
        }
        return current;
    }
    /**
     * Uses an optimization technique known as memoization.
     * This technique checks if there is already a solution to the problem
     * by searching for the key-value pair in a map.
     * This reduces the time complexity to O(n),
     * but also requires O(n) storage from the map.
     * @param n number at the nth spot on the fibonacci sequence
     * @return  the nth fibonacci number
     */
    @OrdersOfGrowth("n")
    static int topDownFib(int n) {
        if (n <= 1) {
            return n;
        }
        if (fibSolutions.containsKey(n)) {
            return fibSolutions.get(n);
        }
        int result = topDownFib(n-1) + topDownFib(n-2);
        fibSolutions.put(n, result);
        return result;
    }
    /**
     * A naive implementation of the fibonacci sequence.
     * Recursively calculates the nth number through the mathematical definition.
     * Results in a time complexity of O(2^n) because of the calling tree.
     * @param n number at the nth spot on the fibonacci sequence
     * @return  the nth fibonacci number
     */
    @OrdersOfGrowth("2^n")
    static int naiveFib(int n) {
        return n <= 1 ? n : naiveFib(n-1) + naiveFib(n-2);
    }
    @OrdersOfGrowth("2^n")
    static int naivePascal(int row, int col) {
        return row == 0 || col == 0 ? 1 : naivePascal(row-1, col) + naivePascal(row-1, col-1);
    }
    @OrdersOfGrowth("n^2")
    static int bottomUpPascal(int row, int col) {
        if (row == 0 || col == 0) {
            return 1;
        }
        int[][] triangle = new int[row][];
        for (int r = 0; r < triangle.length; r++) {
            triangle[r] = new int[r+1];
            for (int c = 0; c < r+1; c++) {
                if (r-1 >= 0 && c-1 >= 0 && c < triangle[r-1].length) {
                    int diagonal = triangle[r-1][c-1];
                    int above = triangle[r-1][c];
                    triangle[r][c] = diagonal + above;
                } else {
                    triangle[r][c] = 1;
                }
            }
        }
        for (int[] i : triangle) {
            for (int e : i) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
        return triangle[row-1][col];
    }
    public static void main(String[] args) {
        // topDownFib(5);
        // topDownFib(7);
        // topDownFib(10);
        // topDownFib(20);
        long start = System.currentTimeMillis();
        System.out.println(bottomUpPascal(20, 13));
        long end = System.currentTimeMillis();
        // start = System.currentTimeMillis();
        System.out.println("Fast Time: " + (end - start));
        // System.out.println(naiveFib(40));
        // end = System.currentTimeMillis();
        // System.out.println("Slow Time: " + (end - start));
    }
}
