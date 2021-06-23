//Libraries

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Parantheses {

    /*
     * Returns true if the array is balanced.
     *
     * @return {@code true} if the stack is balanced;
     * {@code false} otherwise
     */
    public boolean isBalanced(String input) {
        char[] inputArray = input.toCharArray();
        Stack<Character> stack = new Stack<>();

        // Printing array
        for (char c : inputArray) {
            if (c == '[' || c == '(' || c == '{') {
                stack.push(c); //add to the stack if there are any left brackets
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char checkItem = stack.pop(); //removes the value and checks it against the new incoming value

                if (c == '(' && c != ')' || c == '[' && c != ']' || c == '{' && c != '}') {
                    return false; //return statement will break out of the loop!! this only breaks out if it is found to be unbalanced!
                }
            }
        }
        //StdOut.println(stack.isEmpty()); //checks if there's things in the stack

        return true;
    }

    public static void main(String[] args) {
        StdOut.println("starting...");
        Parantheses parantheses = new Parantheses();
        StdOut.println(parantheses.isBalanced("[[()()()]]"));
    }
}
