public interface BubbleSort {
    public static void smartBubbleSort(int[] arr) {
        boolean sorted = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                swap(arr, i, i + 1);
                sorted = false;
            }
            if (i + 1 == arr.length - 1) {
                if (sorted)
                    return;
                i = 0;
                sorted = true;
            }
        }
    }
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr.length - i - 1; j++)
                if (arr[j] > arr[j + 1])
                    swap(arr, j, j + 1);
    }
    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
    public static void main(String[] args) {
        final int num = 10000;
        int[] arr = new int[num], copy = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = (int) (Math.random() * num);
            copy[i] = arr[i];
        }
        long begin = System.currentTimeMillis();
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("Dumb: " + (end - begin));
        begin = System.currentTimeMillis();
        smartBubbleSort(copy);
        end = System.currentTimeMillis();
        System.out.println("Enhanced: " + (end - begin));
        // System.out.println(Arrays.toString(copy));
    }
}
