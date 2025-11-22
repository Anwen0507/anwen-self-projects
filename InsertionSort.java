import java.util.Arrays;

public interface InsertionSort {
    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            final int key = arr[i];
            int insert = i;
            while (insert > 0 && key < arr[insert - 1]) {
                arr[insert] = arr[insert - 1];
                insert--;
            }
            arr[insert] = key;
        }
    }
    public static void main(String[] args) {
        final int num = 100;
        int[] arr = new int[num];
        for (int i = 0; i < num; i++)
            arr[i] = (int) (Math.random() * num);
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
