import java.util.Arrays;

public interface SelectionSort {
    public static void sort(int[] arr) {
        arr = sort(0, arr, new boolean[arr.length]);
    }
    private static int[] sort(int start, int[] arr, boolean[] bool) {
        int[] sorted = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int index = minIndex(arr, bool);
            System.out.println(index);
            sorted[i] = arr[index];
            bool[index] = true;
            System.out.println(Arrays.toString(bool));
        }
        return sorted;
    }
    private static int minIndex(int[] arr, boolean[] bool) {
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++)
            if (!bool[0])
                if (arr[minIndex] > arr[i])
                    minIndex = i;
        return minIndex;
    }
    
    public static void main(String[] args) {
        // final int num = 100;
        // int[] arr = new int[num];
        // for (int i = 0; i < num; i++) {
        //     arr[i] = (int) (1 + Math.random() * num * num * num);
        // }
        int[] arr = {9, 8, 7, 6, 5, 4};
        sort(arr);
        for (int e : arr) {
            System.out.print(e + " ");
        }
    }
}
