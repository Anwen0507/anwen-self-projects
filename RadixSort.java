import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

/**
 * A non-comparison based sorting algorithm that goes through each number in the
 * array from the least significant to most significant digit.
 * 
 * @author Anwen Hao
 */
public interface RadixSort {
    public static int[] sort(int[] nums, int digits) {
        for (int i = 0; i < digits; i++) {
            List<Queue<Integer>> buckets = itemsToQueues(nums, i);
            nums = queuesToArray(buckets, nums.length);
        }
        return nums;
    }

    private static int[] queuesToArray(List<Queue<Integer>> buckets, int numVals) {
        int[] nums = new int[numVals];
        int index = 0;
        for (Queue<Integer> q : buckets) {
            while (!q.isEmpty()) {
                nums[index] = q.remove();
                index++;
            }
        }
        return nums;
    }

    private static List<Queue<Integer>> itemsToQueues(int[] nums, int k) {
        List<Queue<Integer>> buckets = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new LinkedList<>());
        }
        for (int e : nums) {
            int digit = getDigit(e, k);
            buckets.get(digit).add(e);
        }
        return buckets;
    }

    private static int getDigit(int num, int k) {
        int digit = 0;
        while (k >= 0) {
            digit = num % 10;
            num /= 10;
            k--;
        }
        return digit;
    }

    public static void main(String[] args) {
        final int num = 1000000;
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = (int) (Math.random() * num);
        }
        long begin = System.currentTimeMillis();
        arr = sort(arr, 3);
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}
