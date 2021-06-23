/*
 * Exercise 1.3.12
 * Write an iterable Stack client that has a static method copy() that takes a stack of strings as argument
 * and returns a copy of the stack.
 */

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Exercise12 {

    private static Stack<String> copy(Stack<String> stack) {

        //create stack & a copy stack
        Stack<String> temp = new Stack<>();
        Stack<String> copy = new Stack<>();

        //push values from orig stack to the copy
        for (String s : stack) {
            StdOut.print(s);
            temp.push(s);
        }

        //push temp values to copy
        for (String s : temp) {
            copy.push(s);
        }

        return copy;

    }


    public static void main(String[] args) {
//      Exercise12<Item> stack = new Exercise12<>(); //initalize empty stack (<Item> allows for diff types of parameter types)
        StdOut.print("Initializing...");
        Stack<String> stack = new Stack<>(); //created a stack

        stack.push("Item 1");//added in 3 items
        stack.push("Item 2");
        stack.push("Item 3");


        Stack<String> copy = copy(stack); //initate the 'copy' method and add these values
        stack.pop(); //pops off item 3
        stack.pop(); //pops off item 2
        stack.pop();

        for (String s : copy) { //print each value of copy?
            StdOut.print(s);
        }

        StdOut.print("Expected: Item 3, Item 2, Item 1");
        StdOut.println(stack.isEmpty());

    }


}
