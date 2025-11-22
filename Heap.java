
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Implements Heap binary tree data structure. Heap is a packed binary tree, so
 * it may be compressed into and visualized as an array. This is resizable,
 * meaning that adding and deleting elements is permitted. However, there is no
 * control over the position of the element if the heap structure must be
 * maintained. Left and right children must be smaller than parent. Heapsort is
 * based on this data structure. While SearchTree can control the ordering of
 * nodes, Heap cannot, so SearchTree would be the most useful when finding
 * nodes.
 * <p>
 * Find parent node: (i-1) >>> 1 where i is the index.
 * <p>
 * Find left node: (i << 1) + 1 where i is the index.
 * <p>
 * Find right node: (i << 1) + 2 where i is the index.
 * 
 * @author Anwen Hao
 * @version 1.2
 * @since 1.0
 * @see SearchTree
 */
@SuppressWarnings("unchecked")
public class Heap<E extends Number> {
    private Object[] heap;
    private volatile transient int size;
    private final Comparator<? super E> comparator;
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static final Comparator<Number> MAX_HEAP = (x, y) -> {
        return (int) y - (int) x;
    };
    private static final Comparator<Number> MIN_HEAP = (x, y) -> {
        return (int) x - (int) y;
    };

    public Heap() {
        this(DEFAULT_INITIAL_CAPACITY, MAX_HEAP);
    }

    public Heap(int size, boolean isMax) {
        this(size, isMax ? MAX_HEAP : MIN_HEAP);
    }

    public Heap(int size, Comparator<? super E> comparator) {
        heap = new Object[size];
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Recursively builds heap from bottom up. Starts with children and builds until
     * parent.
     * 
     * @param heap  random array
     * @param index index at which to start
     * @return heap
     */
    protected void heapify(int index, int end, Object[] heap) {
        if (index > end) {
            return;
        }
        if (index > 0 && comparator.compare((E) heap[index], (E) heap[(index - 1) >>> 1]) < 0) {
            Object temp = heap[index];
            heap[index] = heap[(index - 1) >>> 1];
            heap[(index - 1) >>> 1] = temp;
            heapify((index - 1) >>> 1, end, heap);
        } else {
            heapify(index + 1, end, heap);
        }
    }

    /**
     * Returns the first element in the heap.
     * 
     * @return largest/smallest element of heap
     */
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty heap.");
        }
        return (E) heap[0];
    }

    /**
     * Adds an element to the heap. Maintains heap contract.
     * 
     * @param element element to be added
     * @return new array with added element
     */
    public void add(E element) {
        if (size == heap.length) {
            morph(heap.length << 1);
        }
        heap[size] = element;
        size++;
        heapify(0, size-1, heap);
    }

    public E remove() {
        E temp = (E) heap[0];
        for (int i = 0; i < size; i++) {
            heap[i] = heap[i + 1];
        }
        size--;
        heapify(0, size-1, heap);
        return temp;
    }

    private void morph(int newLength) {
        Object[] copy = new Object[newLength];
        for (int i = 0; i < heap.length && i < copy.length; i++) {
            copy[i] = heap[i];
        }
        heap = copy;
    }

    public Heap<E> clone() {
        Heap<E> copy = new Heap<>(size, comparator);
        for (Object e : heap) {
            copy.add((E) e);
        }
        return copy;
    }

    /**
     * Returns String value for heap.
     */
    @Override
    public String toString() {
        String str = "";
        for (Object e : heap) {
            if (e != null)
                str += e + " ";
        }
        return str;
    }

    public static void main(String[] args) {
    }
}
