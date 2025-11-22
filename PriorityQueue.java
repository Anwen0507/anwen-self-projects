import java.util.Arrays;
// import java.util.Comparator;
// import java.util.PriorityQueue;

public class PriorityQueue {
    private int[] heap;
    private volatile transient int size;
    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    public PriorityQueue(int... arr) {
        heap = arr;
        size = arr.length - 1;
    }

    public PriorityQueue(int capacity) {
        heap = new int[capacity];
    }

    public PriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public void add(int element) {
        if (size == heap.length - 1) {
            int[] temp = heap;
            heap = new int[size << 1];
            for (int i = 0; i < temp.length; i++) {
                heap[i] = temp[i];
            }
        }
        size++;
        heap[size] = element;
        siftUp(element, size);
    }

    public int[] heapSort() {
        int end = size - 1;
        while (end > 0) {
            int first = heap[1];
            heap[1] = heap[end];
            heap[end] = first;
            end--;
            buildHeap();
        }
        return heap;
    }

    private void siftUp(int e, int currIndex) {
        int parentIndex = currIndex >> 1;
        if (parentIndex > 0) {
            int p = heap[parentIndex];
            if (e > p) {
                heap[parentIndex] = e;
                heap[currIndex] = p;
            }
            siftUp(e, currIndex >> 1);
        }
    }

    public void buildHeap() {
        final int lastNonLeaf = size >> 1;
        for (int i = lastNonLeaf; i > 0; i--) {
            heapify(i);
        }
    }

    private void heapify(int index) {
        if (index < size) {
            int leftIndex = index << 1, rightIndex = (index << 1) + 1;
            if (leftIndex < size && rightIndex < size) {
                int next = swapWithTwo(index, leftIndex, rightIndex);
                if (next != -1) {
                    heapify(next);
                }
            } else if (leftIndex < size) {
                if (swapWithOne(index, leftIndex)) {
                    heapify(leftIndex);
                }
            } else if (rightIndex < size) {
                if (swapWithOne(index, rightIndex)) {
                    heapify(rightIndex);
                }
            }
        }
    }

    private int swapWithTwo(int currIndex, int leftIndex, int rightIndex) {
        int curr = heap[currIndex];
        int left = heap[leftIndex], right = heap[rightIndex];
        int max = (left > right) ? left : right;
        int nextIndex = (max == left) ? leftIndex : rightIndex;
        if (max > curr) {
            heap[currIndex] = max;
            heap[nextIndex] = curr;
            return nextIndex;
        }
        return -1;
    }
    
    private boolean swapWithOne(int currIndex, int childIndex) {
        int curr = heap[currIndex], child = heap[childIndex];
        if (child > curr) {
            heap[currIndex] = child;
            heap[childIndex] = curr;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        PriorityQueue p = new PriorityQueue();
        p.heap = new int[10];
        // p.size = 9;
        for (int i = 1; i < 10; i++) {
            p.add(i);
        }
        System.out.println(Arrays.toString(p.heapSort()));
    }
}
