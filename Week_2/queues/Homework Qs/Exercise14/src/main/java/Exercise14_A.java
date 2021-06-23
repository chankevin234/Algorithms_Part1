/*
1.3.14 Develop a class ResizingArrayQueueOfStrings that implements the queue
abstraction with a fixed-size array, and then extend your implementation to use array
resizing to remove the size restriction
 */

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class Exercise14_A { //class ResizingArrayQueueOfStrings

    //inital capacity of resizing array
    //private static final int INIT_SIZE = 8;

    private String[] items;   //queue elements
    private int n;          //number of elements on queue
    private int first;      //index of first element of queue
    private int last;       //index of next available slot

    public Exercise14_A(int capacity) {
        items = new String[capacity]; //create a string array of defined capcaity
    }

    public boolean isEmpty() {
        return n == 0; //returns whether this true
    }

    public int size() {
        return n; //returns size
    }

  
    public void enqueue(String item) {
        if (n != items.length) { //checks if the queue is full or not
            if (last == items.length) { //wrap around
                last = 0;
            }

            items[last++] = item; //sets the value for the last item
            n++;
        }
    }

    public String dequeue() {
        if (isEmpty()) { //must have an element to dequeue
            throw new NoSuchElementException("Queue underflow");
        } else {
            String item = items[first];
            items[first] = null; //sets the first element as
            first++;

            if (first == items.length) {
                first = 0;
            }
            n--;
            return item;
        }
    }


    public static void main(String[] args) {
        Exercise14_A fixedSizeArrayQueueOfStrings = new Exercise14_A(3);

        fixedSizeArrayQueueOfStrings.enqueue("1");
        fixedSizeArrayQueueOfStrings.enqueue("2");
        fixedSizeArrayQueueOfStrings.enqueue("3");

        StdOut.println("Deueue 1: " + fixedSizeArrayQueueOfStrings.dequeue());
        StdOut.println("Expected: 1\n");

        fixedSizeArrayQueueOfStrings.enqueue(("4"));
        StdOut.println("Dequeue 2: " + fixedSizeArrayQueueOfStrings.dequeue());
        StdOut.println("Expected 2");
    }

}
