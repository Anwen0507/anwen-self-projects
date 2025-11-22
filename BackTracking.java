public interface BackTracking {
    /**
     * This is the famous subset sum problem. This can be divided into two paths:
     * <p>
     * We first check if {@code arr[start] == target}; if that is true, then we
     * return true and do not progress down the array. Otherwise, we repeat the
     * process on the rest of the array, but with the first element subtracted.
     * <p>
     * The second case is when we did not return true in the first case; therefore,
     * we must shrink the array by one and repeat the above process.
     * <p>
     * This is an especially challenging problem considering that there is a
     * recursion in a recursion, but it may be helpful to think of this as a
     * decision tree. Note that this method works only for consecutive groups.
     * 
     * @param start  starting point of search
     * @param arr    array to be searched
     * @param target target to be summed to
     * @return true if a group in the array can be summed to target
     * @see {@code accumulation()}
     */
    public static boolean groupSum(int start, int[] arr, int target) {
        if (start == arr.length) {
            return false;
        }
        boolean case1 = accumulation(start, arr, target);
        return case1
                ? true
                : groupSum(start + 1, arr, target);
    }

    /**
     * Used to avoid adjusting the original sum detrimentally.
     * 
     * @param start  starting point of search
     * @param arr    array to be searched
     * @param target target to be summed to
     * @return true if a group in the array can be summed to target
     */
    public static boolean accumulation(int start, int[] arr, int target) {
        if (start == arr.length) {
            return false;
        }
        if (arr[start] == target) {
            return true;
        }
        return accumulation(start + 1, arr, target - arr[start]);
    }

    public static boolean splitArray(int[] arr) {
        if (arr.length == 0) {
            return true;
        }
        if (arr.length == 1) {
            return false;
        }
        return sumToZero(1, arr, false, arr[0]);
    }

    private static boolean sumToZero(int start, int[] arr, boolean negatedBefore, int sum) {
        if (start == arr.length - 1) {
            sum -= arr[start];
            if (sum == 0) {
                return true;
            }
            if (negatedBefore) {
                sum += arr[start] << 1;
                return sum == 0;
            }
            return false;
        }
        sum += arr[start];
        if (sumToZero(start + 1, arr, negatedBefore, sum)) {
            return true;
        }
        sum -= arr[start] << 1;
        return sumToZero(start + 1, arr, true, sum);
    }

    public static boolean matchStrings(int start, String str, String combo, String match, boolean addedLetter) {
        if (start == str.length() - 1) {
            combo += str.substring(start, start + 1);
            if (combo.equals(match)) {
                return true;
            }
            if (addedLetter) {
                combo = combo.substring(0, combo.length() - 1);
                return combo.equals(match);
            }
            return false;
        }
        if (matchStrings(start + 1, str, combo, match, addedLetter)) {
            return true;
        }
        combo += str.substring(start, start + 1);
        return matchStrings(start + 1, str, combo, match, true);
    }

    public static boolean subsetSum(int start, int[] arr, boolean added, int sum, int target) {
        if (start == arr.length - 1) {
            sum += arr[start];
            if (sum == target) {
                return true;
            }
            if (added) {
                sum -= arr[start];
                return sum == target;
            }
            return false;
        }
        if (subsetSum(start + 1, arr, added, sum, target)) {
            return true;
        }
        sum += arr[start];
        return subsetSum(start + 1, arr, true, sum, target);
    }

    public static boolean chosenSum(int start, int sum, int[] arr, int target) {
        if (start == arr.length && sum == target) {
            return true;
        }
        if (chosenSum(start + 1, sum, arr, target)) {
            return true;
        }
        sum += arr[start];
        return chosenSum(start + 1, sum, arr, target);
    }

    public static boolean splitEvenOdd(int start, int sum, int[] arr, boolean negatedBefore) {
        if (start == arr.length - 1) {
            sum -= arr[start];
            if (sum % 2 != 0) {
                return true;
            }
            if (negatedBefore) {
                sum += arr[start] << 1;
                return sum % 2 != 0;
            }
            return false;
        }
        sum += arr[start];
        if (splitEvenOdd(start + 1, sum, arr, negatedBefore)) {
            return true;
        }
        sum -= arr[start] << 1;
        return splitEvenOdd(start + 1, sum, arr, true);
    }

    public static boolean splitOdd(int start, int sumGrp1, int sumGrp2, int[] arr) {
        if (start == arr.length) {
            return sumGrp1 % 2 != 0 && sumGrp2 % 2 != 0;
        }
        sumGrp1 += arr[start];
        if (splitOdd(start + 1, sumGrp1, sumGrp2, arr)) {
            return true;
        }
        sumGrp1 -= arr[start];
        sumGrp2 += arr[start];
        return splitOdd(start + 1, sumGrp1, sumGrp2, arr);
    }

    public static boolean splitNoAdj(int start, int sum, int prev, int[] arr, int target, boolean chosen) {
        if (start == arr.length - 1) {
            if (arr[start] != prev) {
                sum += arr[start];
            }
            if (sum == target) {
                return true;
            }
            if (chosen) {
                if (arr[start] != prev) {
                    sum -= arr[start];
                    return sum == target;
                }
            }
            return false;
        }
        prev = arr[start];
        if (splitNoAdj(start + 1, sum, prev, arr, target, chosen)) {
            return true;
        }
        sum += arr[start];
        return splitNoAdj(start + 1, sum, prev, arr, target, true);
    }

    int notpossible = 0;

    public static int knapsack(int start, int goal, int total, int[] weights) {
        if (start >= weights.length) {
            return total >= goal
                    ? notpossible
                    : total;
        }
        int added = knapsack(start + 1, goal, total + weights[start], weights);
        int notadded = knapsack(start + 1, goal, total, weights);
        return added != notpossible && added > notadded
                ? added
                : notadded;
    }

    public static boolean exists(Vertex<Integer> graph, Vertex<Integer> node) {
        if (graph.equals(node)) {
            return true;
        }
        if (graph.getNeigbors().isEmpty()) {
            return false;
        }
        for (Vertex<Integer> e : graph.getNeigbors()) {
            if (exists(e, node)) {
                return true;
            }
        }
        return false;
    }

    public static String paren(int num, String trial) {
        if (num == 0) {
            return valid(trial)
                    ? trial
                    : null;
        }
        String temp = trial;
        trial = paren(num - 1, temp + "(");
        if (trial != null) {
            return trial;
        }
        return paren(num - 1, temp + ")");
    }

    private static boolean valid(String str) {
        int countLeft = 0;
        int countRight = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.substring(0, 1).equals("(")) {
                countRight++;
            } else {
                countLeft++;
            }
        }
        System.out.println(countLeft + " " + countRight);
        return countLeft == countRight;
    }

    // private static String reverse(String str) {
    // return str.equals("(")
    // ? ")"
    // : "(";
    // }
    public static void main(String[] args) {
        // Vertex<Integer> root = Vertex.init();
        // System.out.println(root);
        // System.out.println(exists(root, new Vertex<>(15)));
        System.out.println(paren(4, ""));
    }
}
