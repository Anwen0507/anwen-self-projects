import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Informational annotation type that states the order of growth for a method.
 * Because it is difficult to replicate the conditions on which algorithms are
 * run,
 * we must compare the algorithm to itself at different input levels in order to
 * measure time complexity.
 * This annotation type is only allowed at the location of a method.
 * User must specify the order of growth when using this annotation type.
 * <p>
 * The Big O is the worst case orders of growth, which describes time
 * complexity. The value of O only includes the most influential component of
 * the growth:
 * <p>
 * T(n) = n^2 + 2n + 1, where function T is the time complexity
 * <p>
 * As n approaches infinity, only the n^2 will be impactful, so the O notation
 * for this would be O(n^2).
 * <p>
 * In Data Structures course, we will only be focusing on:
 * O(1), O(n), O(n^2), O(2^n), and O(log(n)).
 */
@Target(ElementType.METHOD)
public @interface OrdersOfGrowth {
    /**
     * The order of growth for a method.
     * 
     * @return the order of growth for a method
     */
    String value() default "1";
}
