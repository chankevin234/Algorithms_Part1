/*
 * Exercise 1.3.12
 * Write an iterable Stack client that has a static method copy() that takes a stack of strings as argument
 * and returns a copy of the stack.
 */

public class Exercise12 {

    private class Node {
        Item item; //value of the new node
        Node next; //value of the following node
    }

    private Node first = null; //creates initial first node
    private int size = 0; //

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        Node oldFirst = new Node();

    }

    public static void main(String[] args) {
        Exercise12<Item> stack = new Exercise12<>(); //initalize empty stack (<Item> allows for diff types of parameter types)
    }


}
