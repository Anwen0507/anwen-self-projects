/**
 * Abides by the "first in last out" property. Observe that Stacks can be used
 * to stimulate Recursion. Stacks work for any object type.
 * 
 * @author Anwen Hao
 * @version 1.0
 * @param <E> type of the element in the Stack
 * @see Queue
 */
public interface Stack<E> {
    public boolean push(E e);
    public E pop();
    public E peek();
    public boolean isEmpty();
    public String toString();
}
