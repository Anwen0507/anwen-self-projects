import java.util.Stack;

public interface Parsing {
    public static int parse(String str) {
        if (str.length() == 1) {
            int parsed = str.charAt(0) - '0';
            return parsed;
        }
        return parse(str.substring(str.length() - 1)) + parse(str.substring(0, str.length() - 1)) * 10;
    }

    public static String toString(int num) {
        if (num / 10 == 0) {
            char character = (char) (num + '0');
            return String.valueOf(character);
        }
        return toString(num / 10) + toString(num % 10);
    }

    public static int eval(String expr) {
        Stack<Integer> postfixOperands = new Stack<Integer>();
        expr = infixToPostfix(expr);
        String[] strings = expr.split(" ");
        for (String e : strings) {
            if (e.equals("+")) {
                postfixOperands.push(postfixOperands.pop() + postfixOperands.pop());
            } else if (e.equals("-")) {
                int right = postfixOperands.pop();
                int left = postfixOperands.pop();
                postfixOperands.push(left - right);
            } else if (e.equals("*")) {
                postfixOperands.push(postfixOperands.pop() * postfixOperands.pop());
            } else if (e.equals("/")) {
                int right = postfixOperands.pop();
                int left = postfixOperands.pop();
                postfixOperands.push(left / right);
            } else if (e.equals("%")) {
                int right = postfixOperands.pop();
                int left = postfixOperands.pop();
                postfixOperands.push(left % right);
            } else {
                postfixOperands.push(Integer.parseInt(e));
            }
        }
        return postfixOperands.peek();
    }

    private static String infixToPostfix(String expr) {
        Stack<String> postFix = new Stack<String>();
        String strPostfix = "";
        String[] strings = expr.split(" ");
        for (String e : strings) {
            if (e.equals("(")) {
                postFix.push(e);
            } else if (e.equals(")")) {
                while (!postFix.peek().equals("(")) {
                    strPostfix += postFix.pop() + " ";
                }
                postFix.pop();
            } else if (e.equals("*") || e.equals("/") || e.equals("%")) {
                if (postFix.isEmpty() || postFix.peek().equals("+") || postFix.peek().equals("-")) {
                    postFix.push(e);
                } else {
                    strPostfix += postFix.pop() + " ";
                    postFix.push(e);
                }
            } else if (e.equals("+") || e.equals("-")) {
                if (postFix.isEmpty() || postFix.peek().equals("(")) {
                    postFix.push(e);
                } else if (postFix.peek().equals("+") || postFix.peek().equals("-")) {
                    strPostfix += postFix.pop() + " ";
                    postFix.push(e);
                } else {
                    while (!postFix.isEmpty() && (postFix.peek().equals("*") || postFix.peek().equals("/")
                            || postFix.peek().equals("%"))) {
                        strPostfix += postFix.pop() + " ";
                    }
                    postFix.push(e);
                }
            } else {
                strPostfix += e + " ";
            }
        }
        while (!postFix.isEmpty()) {
            strPostfix += postFix.pop() + " ";
        }
        return strPostfix;
    }
    public static void main(String[] args) {
        System.out.println(Parsing.eval("( 1 + 3 ) * 5"));
    }
}
