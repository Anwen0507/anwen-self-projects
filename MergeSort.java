import java.util.Queue;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * A superior implementation of MergeSort than the implementation for sorting lists
 * (see {@link MergeSortList}). Uses less intermediate data structures,
 * although Queues are used for convenience during the merging process
 * (we want to use the first in first out property).
 * Note that this method is a void method, since it is an in-place sorting algorithm.
 * 
 * @author Anwen Hao
 * @version 1.0
 * @see MergeSortList
 */
public class MergeSort {
    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int start, int end) {
        if (start == end)
            return;
        int mid = (start + end) >> 1;
        sort(arr, start, mid);
        sort(arr, mid + 1, end);
        Queue<Integer> left = new LinkedList<>(), right = new LinkedList<>();
        for (int i = start; i <= mid; i++)
            left.add(arr[i]);
        for (int i = mid + 1; i <= end; i++)
            right.add(arr[i]);
        int index = start;
        while (!left.isEmpty() && !right.isEmpty()) {
            int l = left.peek(), r = right.peek();
            if (l < r) {
                arr[index] = l;
                left.remove();
            } else {
                arr[index] = r;
                right.remove();
            }
            index++;
        }
        while (!right.isEmpty()) {
            arr[index] = right.remove();
            index++;
        }
        while (!left.isEmpty()) {
            arr[index] = left.remove();
            index++;
        }
    }

    public static void main(String[] args) {
        int[] arr = {(int) (50 * Math.random()), (int) (50 * Math.random()), (int) (50 * Math.random()), (int) (50 * Math.random()), (int) (50 * Math.random())};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
