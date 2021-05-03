import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
Randomized queue.
A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random among items in the data structure.
Create a generic data type RandomizedQueue that implements the following API:
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] resizingArray;
    private int head;
    private int tail;
    private int count;

    // construct an empty randomized queue
    public RandomizedQueue() {
        resizingArray = (Item[]) new Object[1];
        head = 0;
        tail = 0;
        count = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("enqueued item is null");
        if (count == resizingArray.length || tail == resizingArray.length) {
            resize(2 * resizingArray.length);
        }
        resizingArray[tail++] = item;
        count++;
    }

    public void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        int i = 0;
        for (int j = 0; i < count; j++) {
            if (resizingArray[j] != null) {
                copy[i] = resizingArray[j];
                i++;
            }
        }

        resizingArray = copy;
        head = 0;
        tail = count;
    }

    /**
     * remove and return a random item
     *
     * @return a random item removing it
     * @throws NosuchElementException if the client calls dequeue() when the randomized queue is empty
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("randomized queue is empty");
        int randomPlace = 0;
        Item item = null;
        do {
            randomPlace = StdRandom.uniform(head, tail);
        } while ((item = resizingArray[randomPlace]) == null);

        resizingArray[randomPlace] = null;
        count--;

        if (count > 0) {
            //invariant: array is between 25 and 100% full
            if (count == resizingArray.length / 4) {
                resize(resizingArray.length / 2);
            } else if (head == randomPlace) {
                // invariant: head is in the interval [0; tail)
                // && array[head] == null
                for (; resizingArray[head] == null; head++)
                    ;
            } else if (tail == randomPlace - 1) {
                // invariant: tail is in the interval (head; length of the array]
                // && array[tail - 1] == null && array[head] != null
                for (; resizingArray[tail - 1] == null; --tail)
                    ;
            }
        } else if (count == 0) {
            head = 0;
            tail = 0;
        }
        return item;
    }

    /**
     * return a random item (but do not remove it)
     *
     * @return a random item without remving it
     * @throws NoSuchElementException if the client calls sample() when the randomized queue is empty
     */
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("randomized queue is empty");
        int randomPLace = 0;
        Item item = null;
        do {
            randomPLace = StdRandom.uniform(head, tail);
        } while ((item = resizingArray[randomPLace]) == null);
        return item;
    }


    /**
     * return an independent iterator over items in random order
     *
     * @return an iterator that iterates over the items in random order
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final Item[] currentArray;
        private int curr;

        /**
         * Construct iterator for randomized queue.
         */
        RandomizedQueueIterator() {
            currentArray = (Item[]) new Object[count];

            int i = 0;
            for (int j = 0; i < count; j++) {
                if (resizingArray[j] != null) {
                    currentArray[i] = resizingArray[j];
                    i++;
                }
            }

            StdRandom.shuffle(currentArray);
            curr = 0;
        }

        @Override
        public boolean hasNext() {
            return curr != currentArray.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return currentArray[curr++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String sign = StdIn.readString();
            switch (sign) {
                case "+":
                    String item = StdIn.readString();
                    queue.enqueue(item);
                    break;
                case "-s":
                    if (!queue.isEmpty())
                        StdOut.print(queue.sample() + " ");
                    break;
                case "-d":
                    if (!queue.isEmpty())
                        StdOut.print(queue.dequeue() + " ");
                    break;
                default:
                    break;
            }
        }
        StdOut.println("(" + queue.size() + " left on rand queue)");
        for (String str : queue) {
            StdOut.print(str + " ");
        }
    }


}
