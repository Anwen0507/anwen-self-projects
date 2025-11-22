public interface SortingTimeComplexity {
    public static void main(String[] args) {
        System.out.println("Starting Tester...\f");
        final int num = 5;
        long[] q = new long[num], m = new long[num], r = new long[num];
        for (int i = 0; i < num; i++) {
            int[] arr = generate();
            long begin = System.currentTimeMillis();
            QuickSort.quickSort(arr, 0, arr.length - 1);
            long end = System.currentTimeMillis();
            q[i] = end - begin;
            System.out.println("QuickSort done. " + (i + 1));
        }
        for (int i = 0; i < num; i++) {
            int[] arr = generate();
            long begin = System.currentTimeMillis();
            MergeSort.sort(arr);
            long end = System.currentTimeMillis();
            m[i] = end - begin;
            System.out.println("MergeSort done. " + (i + 1));
        }
        for (int i = 0; i < num; i++) {
            int[] arr = generate();
            long begin = System.currentTimeMillis();
            RadixSort.sort(arr, 7);
            long end = System.currentTimeMillis();
            r[i] = end - begin;
            System.out.println("RadixSort done. " + (i + 1));
        }
        int qsum = 0, msum = 0, rsum = 0;
        for (int i = 0; i < num; i++) {
            qsum += q[i];
            msum += m[i];
            rsum += r[i];
        }
        System.out.println("QuickSort: " + (qsum / num));
        System.out.println("MergeSort: " + (msum / num));
        System.out.println("RadixSort: " + (rsum / num));
    }

    private static int[] generate() {
        final int num = 10000000;
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = (int) (Math.random() * num);
        }
        return arr;
    }
}
