public interface Hanoi {
    /**
     * Prints the instructions for how to move disks in the Towers of Hanoi problem.
     * 
     * @param numDisks total number of disks
     * @param src      index of the source peg
     * @param dest     index of the destination peg
     * @param aux      index of the helper peg
     */
    public static void moveTowers(int numDisks, int src, int dest, int aux) {
        if (numDisks > 0) {
            moveTowers(numDisks - 1, src, aux, dest);
            move(src, dest);
            moveTowers(numDisks - 1, aux, dest, src);
        }
    }

    private static void move(int src, int dest) {
        System.out.println(src + " -> " + dest);
    }

    public static void main(String[] args) {
        moveTowers(3, 3, 1, 2);
    }
}