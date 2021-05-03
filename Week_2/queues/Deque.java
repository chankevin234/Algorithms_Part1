/*
Dequeue:
A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue that supports adding and removing items from either the front or the back of the data structure.

Performance requirements.
       Your deque implementation must support each deque operation (including construction) in constant worst-case time.
       A deque containing n items must use at most 48n + 192 bytes of memory.
       Additionally, your iterator implementation must support each operation (including construction) in constant worst-case time.

this uses the <Item> generic value in this linkedlist implementation
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

//this is a Deque generic class
public class Deque<Item> implements Iterable<Item> {

    private Node first;    //beginning of the llist

    private Node last;     //end of the llist

    private int count = 0;    //number of nodes in the llist

    private class Node {  //creates a node object/class
        Item item; // the value of the item itself
        Node next; // the link to the next node in the list (right)
        Node prev; // the link to the prev node in the list (left)
    }

    // construct an empty deque
    public Deque() {
        first = null; //first value is null
        last = null; //last is null
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null; //checks if head exists
    }

    // return the number of items on the deque
    public int size() {
        return count; //the number of items
    }

    /* add the item to the front
    Throw an IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument.
     */
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Item was entered as null"); //checks if "item" is null
        Node oldFirst = first; //sets "oldFirst" to have the same value as the first node
        first = new Node(); //create new first node
        first.item = item; //give this new first node the value of the argument
        first.next = oldFirst; //give new first node's next value, the value of oldFirst
        first.prev = null; //make it so that there is null before this new first node

        if (isEmpty()) { //if the list itself is empty, then the last value is the same as the new first node's value
            last = first;
        } else {
            oldFirst.prev = first; //otherwise, connect the oldFirst node's prev value to the new first node's
        }
        count++; //add to the count of items in this llist
    }

    /* add the item to the back
        Throw an IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument.
    */
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("Item was entered as null"); //checks if "item" is null
        Node oldLast = last; //set "oldLast" with the value of the current last
        last = new Node(); //create new last node
        last.item = item; //give the new last node the value of the new argument
        last.next = null; //since you're last node, set so that there is nothing to the left of this new node
        last.prev = oldLast; //connect the oldLast
        if (isEmpty()) {
            first = last; //if the list is empty, set first as the last
        } else {
            oldLast.next = last; //otherwise, the oldLast's next value is the new last node
        }
        count++; //add to the total item count
    }

    /* remove and return the item from the front
        Throw a java.util.NoSuchElementException if the client calls either removeFirst() or removeLast when the deque is empty
     */
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException(); //if the llist is empty already, throw exception
        Item removingItem = first.item; //set the removingItem of the data type "Item" to be the value of the first item
        first = first.next; //set the first value of the llist to be the value of the removing item's next item
        if (isEmpty()) {
            last = null; //if list is empty, the last value is now null since the first item was removed
        } else {
            first.prev = null; //otherwise, set the new first node's prev value to null since that was just removed
        }
        count--; //remove from the total count
        return removingItem; //return the value of the removed Item
    }

    /* remove and return the item from the back
        Throw a java.util.NoSuchElementException if the client calls either removeFirst() or removeLast when the deque is empty
     */
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException(); //if the llist is already empty, throw exception
        Item removingItem = last.item; //set the item to be removed as the last item's value
        last = last.prev; //set the new last value as the item to the left
        if (isEmpty()) {
            first = null; //if llist is empty, the value of the first node is null since the last item was just removed
        } else {
            last.next = null; //otherwise, set the last node's following (right) value to be null since it is the last node now
        }
        count--; //remove from count
        return removingItem; //return value of removed item
    }


    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DoubleLinkedListIterator();
    }

    public class DoubleLinkedListIterator implements Iterator<Item> {
        private Node current = first; // set a private variable of data type Node

        @Override
        public boolean hasNext() {
            return current != null;
        }

        /*
        Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
         */
        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        /*
        Throw an UnsupportedOperationException if the client calls the remove() method in the iterator.
        */
        @Override
        public void remove() {
            throw new UnsupportedOperationException(); //remove() is an unsupported operation
        }
    }

    // Unit testing.  Your main() method must call directly every public constructor and method to help verify that they work as prescribed (e.g., by printing results to standard output).
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>(); //instantiates the Deque object
        while (!StdIn.isEmpty()) {
            String sign = StdIn.readString();
            switch (sign) {
                case "+f":
                    String itemOnFirst = StdIn.readString();
                    deque.addFirst(itemOnFirst);
                    break;
                case "+l":
                    String itemOnLast = StdIn.readString();
                    deque.addLast(itemOnLast);
                    break;
                case "-f":
                    if (!deque.isEmpty())
                        StdOut.print(deque.removeFirst() + " ");
                    break;
                case "-l":
                    if (!deque.isEmpty())
                        StdOut.print(deque.removeLast() + " ");
                    break;
                default:
                    break;
            }
        }
        StdOut.println("(" + deque.size() + " left on deque)");
        for (String str : deque) {
            StdOut.print(str + " ");
        }
//        deque.addfirst(1);
//        deque.addlast(2);
//        deque.removefirst();
//        deque.removelast();
//        for (
//                string s : deque) {
//            stdout.print(s);
//        }
    }
}
