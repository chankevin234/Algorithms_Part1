/*
 * Exercise 1.3.7 Add a method peek() to STACK that returns the most recently inserted item on
the stack (without popping it)
 */

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class Exercise7<Item> {
    private Node first = null; //create a valueless first node
    private int n; //size of stack

    private class Node {
        Item item; //inital string item value
        Node next; //the node that is linked with this one
    }

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

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("stack underflow");
        }
        return first.item;
    }

    public static void main(String[] args) {
        Exercise7<String> stack = new Exercise7<>();
        stack.push("h");
        stack.push("e");
        stack.push("l");
        stack.push("l");
        stack.push("o");
        stack.pop();
        StdOut.println("Peeking... " + stack.peek() + " Expected: 'l' and the size of the stack is: " + stack.size());
    }
}
