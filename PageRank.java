import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class PageRank {
    private static class Page {
        private Set<Page> links;
        private String name;

        private Page(String name) {
            this.name = name;
            this.links = new HashSet<>();
        }

        public String toString() {
            return name;
        }
    }

    private double[][] ranks, weights;
    private Map<Integer, Page> idMap;
    private Map<Page, Integer> inverse;

    public PageRank(List<Page> pages) {
        ranks = new double[pages.size()][1];
        for (double[] r : ranks)
            r[0] = 1.0 / ranks.length;
        weights = new double[pages.size()][pages.size()];
        idMap = new HashMap<>();
        inverse = new HashMap<>();
        int id = 0;
        for (Page e : pages) {
            inverse.put(e, id);
            idMap.put(id, e);
            id++;
        }
        for (int r = 0; r < weights.length; r++)
            for (int c = 0; c < weights[r].length; c++) {
                Page source = idMap.get(c), target = idMap.get(r);
                if (hasLink(source, target)) {
                    int links = source.links.size();
                    weights[r][c] = 1.0 / links;
                }
            }
        ranks = multiply(weights, ranks);
    }

    public double PR(Page p) {
        int id = inverse.get(p);
        return ranks[id][0];
    }

    /**
     * Multiplies 2 matrices.
     * 
     * @param m1 one of the matrix being multiplied
     * @param m2 second matrix being multiplied
     * @return a matrix of products
     */
    private double[][] multiply(double[][] m1, double[][] m2) {
        if (m1[0].length != m2.length)
            throw new IllegalArgumentException();
        double[][] product = new double[m1.length][m2[0].length];
        for (int r = 0; r < m1.length; r++)
            for (int c = 0; c < m2[r].length; c++) {
                double[] row = m1[r];
                double[] col = getCol(m2, c);
                product[r][c] = dot(row, col);
            }
        return product;
    }

    /**
     * Returns the contents of a column in an array.
     * 
     * @param arr 2D array
     * @param col column being searched
     * @return the contents of a column
     */
    private double[] getCol(double[][] arr, int col) {
        double[] column = new double[arr.length];
        for (int r = 0; r < arr.length; r++)
            column[r] = arr[r][col];
        return column;
    }

    /**
     * Takes the dot product of two vectors of the same dimension.
     * The dot product is defined as arr1[0] * arr2[0] + ... arr1[n-1] * arr2[n-1].
     * 
     * @param arr1 one of the vectors being multiplied
     * @param arr2 the other vectors being multipled
     * @return a scalar product of the two vectors
     */
    private double dot(double[] arr1, double[] arr2) {
        if (arr1.length != arr2.length)
            throw new IllegalArgumentException();
        double product = 0;
        final int length = arr1.length;
        for (int i = 0; i < length; i++)
            product += arr1[i] * arr2[i];
        return product;
    }

    private boolean hasLink(Page source, Page target) {
        for (Page e : source.links)
            if (e == target)
                return true;
        return false;
    }

    public static void main(String[] args) {
        List<Page> pages = new ArrayList<>();

        Page a = new Page("a");
        Page b = new Page("b");
        Page c = new Page("c");
        Page d = new Page("d");
        
        d.links.add(a);
        d.links.add(b);
        d.links.add(c);

        b.links.add(a);
        b.links.add(c);

        c.links.add(a);

        pages.add(a);
        pages.add(b);
        pages.add(c);
        pages.add(d);

        PageRank p = new PageRank(pages);
        System.out.println(p.PR(a));
    }
}
