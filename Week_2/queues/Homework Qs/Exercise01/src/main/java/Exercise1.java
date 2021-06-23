/*
Exercise 1.3.1 Add a method isFull() to FixedCapacityStackOfStrings
 */

import edu.princeton.cs.algs4.StdOut;

public class Exercise1 { //fixedcapstackofstrings
    private String[] s; //create string array
    private int N; //next index in array

    public Exercise1(int capacity) {
        //constructor
        s = new String[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0; //returns empty
    }

    public void push(String item) {
        s[N++] = item; //push a new "item" into the stack
    }

    public String pop() {
        return s[N--]; //pop off the last added item from the stack
    }

    public boolean isFull() { //true or false
        return N == s.length; //check if the new index is equal to the length of the array
    }

    public static void main(String[] args) { //unit testing
        Exercise1 myFixedCapacityStackOfStrings = new Exercise1(2); //initialize a new string stack with cap of 2
        StdOut.println("Checking for full capacity: " + myFixedCapacityStackOfStrings.isFull() + " ... Expected ans = False"); //unit test 1 to check for expected false

        myFixedCapacityStackOfStrings.push("kevin");
        myFixedCapacityStackOfStrings.push("amanda");

        StdOut.println("Checking for full capacity: " + myFixedCapacityStackOfStrings.isFull() + " ... Expected ans = True");
    }
}
