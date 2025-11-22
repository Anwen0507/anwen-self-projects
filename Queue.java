/**
 * Abides by the "first in first out" property. Queues work for any object type.
 * 
 * @author Anwen Hao
 * @version 1.0
 * @param <E> type of the element in the Queue
 * @see Stack
 */
public interface Queue<E> {
    public boolean add(E e);
    public E remove();
    public E peek();
    public boolean isEmpty();
    public String toString();
}
