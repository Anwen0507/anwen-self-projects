import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Implements Fibonacci Heap. Structure involves a {@link java.util.LinkedList}
 * of {@link java.util.PriorityQueue}. Efficient at merging heaps because it can
 * simply concatenate the heads. Includes basic capabilities like adding,
 * merging, and peeking.
 * 
 * @author Anwen Hao
 * @version 1.0
 * @see java.util.List
 * @see java.util.PriorityQueue
 * @param <G> type of the element in Fibonacci Heap
 */
public class HeapForest<G> {
    private List<PriorityQueue<G>> heaps; // list of heaps
    private transient int modCount; // number of structural modifications

    /**
     * Creates empty Fibonacci Heap.
     */
    public HeapForest() {
        modCount = 0;
        heaps = new LinkedList<>();
    }

    /**
     * Merges heap into LinkedList.
     * 
     * @param heap added heap
     */
    public void merge(PriorityQueue<G> heap) {
        heaps.add(heap);
        modCount++;
    }

    /**
     * Inserts an element to the Fibonacci Heap. Functions by creating a heap with
     * that element only and merging it into the Fibonacci Heap.
     * 
     * @param e added element
     */
    public void add(G e) {
        PriorityQueue<G> heap = new PriorityQueue<>();
        heap.add(e);
        merge(heap);
        modCount++;
    }

    /**
     * Extracts minimum element in the forest. Functions by first adding heaps with
     * the same degree, which means the number of elements. Then it compares those
     * roots together for the minimum value.
     * 
     * @return minimum value
     */
    public G peek() {
        for (int i = 0; i < heaps.size(); i++) {
            int deg = heaps.get(i).size();
            for (int j = i + 1; j < heaps.size(); j++) {
                if (deg == heaps.get(j).size()) {
                    heaps.get(i).addAll(heaps.get(j));
                }
            }
        }
        G min = heaps.get(0).peek();
        for (int i = 1; i < heaps.size(); i++) {
            if ((int) min > (int) heaps.get(i).peek()) {
                min = heaps.get(i).peek();
            }
        }
        return min;
    }

    /**
     * Returns String value for Fibonacci Heap.
     */
    @Override
    public String toString() {
        int index = 1;
        String str = "";
        for (PriorityQueue<G> e : heaps) {
            str += "Heap " + index + ":\t";
            for (G i : e) {
                str += i + " ";
            }
            index++;
            str += "\n";
        }
        return str;
    }

    public static void main(String[] args) {
        HeapForest<Integer> h = new HeapForest<>();
        PriorityQueue<Integer> q1 = new PriorityQueue<>();
        PriorityQueue<Integer> q2 = new PriorityQueue<>();
        PriorityQueue<Integer> q3 = new PriorityQueue<>();
        PriorityQueue<Integer> q4 = new PriorityQueue<>();
        for (int i = 0; i < 100; i++) {
            int random1 = 1 + (int) (Math.random() * 9);
            int random2 = 10 + (int) (Math.random() * 10);
            int random3 = 20 + (int) (Math.random() * 10);
            int random4 = 30 + (int) (Math.random() * 10);
            q1.add(random1);
            q2.add(random2);
            q3.add(random3);
            q4.add(random4);
        }
        h.merge(q1);
        h.merge(q2);
        h.merge(q3);
        System.out.print(h);
        System.out.print(h.peek());
    }
}
