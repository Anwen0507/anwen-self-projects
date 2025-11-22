import java.util.List;
import java.util.LinkedList;

/**
 * Implements MergeSort algorithm for sorting ArrayLists. This was developed by
 * Hungarian-American Computer Scientist John von Neumann. We first divide the
 * List into the smallest possible unit: 1 element or 0 elements. Then we
 * combine them by comparing the ends of each subarray; the smaller one first
 * goes to the empty List followed by the larger one. We do this until
 * either index reaches the length of the subarray, in which case one of the
 * subarray is exhausted, and we can just append the subarray of the subarray to
 * the List for sorted values. For more sorting algorithms, see
 * {@link QuickSort} and {@link SelectionSort}.
 * 
 * @author Anwen Hao
 * @version 1.0
 * @see QuickSort
 * @see SelectionSort
 */
public interface MergeSortList {
    /**
     * Sorts a given List using MergeSort algorithm. Recursively divides the
     * List and then merges them through comparison.
     * 
     * @param arr List to be sorted
     * @return sorted List
     */
    public static List<Integer> mergeSort(List<Integer> arr) {
        if (arr.isEmpty() || arr.size() == 1) {
            return arr;
        }
        int divider = arr.size() / 2; // marks where to divide the array into subarrays
        List<Integer> subarr1 = new LinkedList<>(arr.subList(0, divider));
        List<Integer> subarr2 = new LinkedList<>(arr.subList(divider, arr.size()));
        subarr1 = mergeSort(subarr1); // assigning new value
        subarr2 = mergeSort(subarr2); // to subarr1 and subarr2 (recursive)
        return merge(subarr1, subarr2);
    }

    private static List<Integer> merge(List<Integer> subarr1, List<Integer> subarr2) {
        List<Integer> sortedArr = new LinkedList<>();
        int index1 = 0; // indexes to refer to
        int index2 = 0; // different spots of the subarrays
        /** Compares the ends of each subarray */
        while (index1 < subarr1.size() && index2 < subarr2.size()) {
            if (subarr1.get(index1) > subarr2.get(index2)) {
                sortedArr.add(subarr2.get(index2));
                index2++;
            } else {
                sortedArr.add(subarr1.get(index1));
                index1++;
            }
        }
        /**
         * Once either index1 or index2 becomes the size of the subarrays, that means
         * one of them is exhausted, so we can just append one of the subarrays to
         * sortedArr.
         */
        if (index1 == subarr1.size()) {
            sortedArr.addAll(subarr2.subList(index2, subarr2.size()));
        }
        if (index2 == subarr2.size()) {
            sortedArr.addAll(subarr1.subList(index1, subarr1.size()));
        }
        return sortedArr;
    }

    public static void main(String[] args) {
        final int num = 1000;
        List<Integer> l = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            l.add((int) (1 + Math.random() * 1000));
        }
        System.out.println(mergeSort(l));
    }
}
