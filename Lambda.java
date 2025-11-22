/**
 * Represents any operation on a variable.
 * Because this is a functional interface,
 * lambda expressions are supported and needed to
 * operate on the term.
 * 
 * @param <T> type of the term
 */
@FunctionalInterface
public interface Lambda<T> {
    /**
     * Operates on the supplied term.
     * Considering the existence of wrapper classes for primitive types,
     * (e.g. Integer, Double, Boolean), we perfect
     * parameter passing and make sure that all changes are retained
     * by returning the final value to the caller.
     * 
     * @param t term being operated on
     * @return modified element
     */
    T perform(T t);
}
