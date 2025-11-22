public class HeapSort<G extends Number> extends Heap<G> {
    public static final int MAX_SIZE = 90;
    public G[] sort(G[] arr) {
        Sortable<G> s = (end, array) -> {
            while (end > 0) {
                heapify(0, end, array);
                G temp = array[end];
                array[end] = array[0];
                array[0] = temp;
                end--;
            }
            return array;
        };
        return s.sort(arr.length-1, arr);
    }
    public static void main(String[] args) {
        // HeapSort<Integer> h = new HeapSort<>();
        final int MAX_SIZE = 100;
        int[] arr = new int[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            arr[i] = 1 + (int) (Math.random() * MAX_SIZE);
        }
        // BubbleSort.bubbleSort(arr, 0, false);
        for (int e : arr) {
            System.out.print(e + " ");
        }
    }
}
