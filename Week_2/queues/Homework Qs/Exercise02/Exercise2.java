/*
Exercise 1.3.2 Give the output printed by java STACK for the input
    This implementation uses a generic linked list with a static nested class for linked-list nodes.
 *
 * @param <Item> the generic type of an item in this stack
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;


public class Exercise2<Item> { //generic stack implementation (stack of strings)
    private class Node {
        Item item; //inital string item value
        Node next; //the node that is linked with this one
    }

    private Node first = null; //create a valueless first node

    private int n; //size of stack

    public int size() {
        return n; //size of the stack
    }

    /**
     * Returns true if the stack is empty.
     *
     * @return {@code true} if the stack is empty;
     * {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null; //no nodes in this stack
    }

    /**
     * Adds the item to the back.
     *
     * @param item the item which is added to the back
     * @throws IllegalArgumentException if the client calls addFirst() with a {@code null} argument
     */
    public void push(Item item) { //add a new Node with a string value
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirst = first; //create a new node and give it the value of the existing first
        first = new Node(); //make a new "first" node
        first.item = item; //give the new first node the new item value
        first.next = oldFirst; //set its following node to the oldfirst
        n++;

    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("stack underflow");
        }
        Item item = first.item; //save the item you wish to pop off
        first = first.next; //delete the first node
        n--;
        return item; //return the value of item
    }

    public static void main(String[] args) {
        Exercise2<String> stack = new Exercise2<>(); // <String> means only string values can be taken in!
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString(); //read from text file

            if (!item.equals("-")) {
                stack.push(item);
            } else if (!stack.isEmpty()) {
                StdOut.print(stack.pop() + " ");
            }
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }
}
