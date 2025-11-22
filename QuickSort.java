/**
 * Implements QuickSort algorithm for sorting arrays. This was developed by
 * British Computer Scientist Tony Hoare. First finds the right pivot by first
 * scanning the array from the right until the array element is less than the
 * chosen pivot, in which case the pivot will be swapped with that element. Then
 * we scan from the left from the "empty" spot until we reach an element larger
 * than the pivot, in which case we swap again. Recursively repeat this process
 * until the "low" and "high" meet, where the position is the right place for
 * the pivot. Everything to the right of the pivot should be greater than it,
 * and everything to the left of the pivot should be less than it (equal
 * elements go either way). Once the pivot is set, the array is divided into two
 * parts based on the pivot, and the process recursively repeats. Thus,
 * QuickSort is a divide and conquer algorithm.
 * 
 * @author Anwen Hao
 * @version 1.0
 */
public interface QuickSort {
    /**
     * Recursively sorts a given array using QuickSort algorithm. Divides the array
     * by the pivot, which is found through the {@code partition} method.
     * 
     * @param arr   array to be sorted
     * @param start start index of the sorting
     * @param end   end index of the sorting
     * @see {@code partition(int[] arr, int low, int high)}
     */
    public static void quickSort(int[] arr, int start, int end) {
        if (end - start >= 1) {
            int pivotIndex = partition(arr, start, end);
            quickSort(arr, start, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, end);
        }
    }

    /**
     * Iteratively finds the correct position of the pivot. This method may also be
     * recursive, although recursion poses no advantage in this scenario.
     * 
     * @param arr  array in which the pivot will be found
     * @param low  lower index for scanning
     * @param high higher index for scanning
     * @return the index for the pivot
     */
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low != high) {
            while (high > low) {
                if (arr[high] < pivot) {
                    arr[low] = arr[high];
                    break;
                }
                high--;
            }
            while (low < high) {
                if (arr[low] > pivot) {
                    arr[high] = arr[low];
                    break;
                }
                low++;
            }
        }
        arr[low] = pivot;
        return low;
    }

    public static void main(String[] args) {
        //final int num = 10;
        int[] arr = {5, 4, 3, 2, 1};
        // long begin = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        // long end = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }
}
