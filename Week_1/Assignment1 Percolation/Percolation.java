/*Task: CREATE A PERCOLATION Data Type
 Throw an IllegalArgumentException if any argument to open(), isOpen(), or isFull() is outside its prescribed range.
 Throw an IllegalArgumentException in the constructor if n ≤ 0.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] array;
    private final WeightedQuickUnionUF wf;
    private final int num;
    private int opened = 0;
    private final int start;
    private final int end;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        // Throw an IllegalArgumentException in the constructor if n ≤ 0.
        if (n <= 0) throw new IllegalArgumentException(("Value of N is less than or equal to 0"));
        array = new boolean[n][n]; //initialize new boolean array
        //create a row by col grid based on n
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                array[row][col] = false; //set all values in the grid to be false
            }
        }
        num = n;
        start = num * num;
        end = num * num + 1;
        wf = new WeightedQuickUnionUF(n * n + 2);
    }

    private int oneD(int row, int col) {
        return row * num + col;
    }

    private void validate(int row, int col) { //validates the user input to check if row or col input is valid
        if (row >= num || row < 0 || col >= num || col < 0)
            throw new IllegalArgumentException("Enter a valid argument");
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int value;
        row = row - 1;//array starts at index 0
        col = col - 1;
        validate(row, col); //checks if row and col are valid aka < 0

        if (!array[row][col]) {//opens the site if it has not been opened
            value = oneD(row, col);
            array[row][col] = true;
            if (row > 0 && array[row - 1][col]) wf.union(value, value - num);
            if (col > 0 && array[row][col - 1]) wf.union(value, value - 1);
            if (row < num - 1 && array[row + 1][col]) wf.union(value, value + 1);
            if (col < num - 1 && array[row][col + 1]) wf.union(value, value + 1);
            if (value < num) wf.union(value, start);
            if (value >= num * num - num) wf.union(value, end);
            opened++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        row = row - 1;
        col = col - 1;
        validate(row, col);
        return array[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        row = row - 1;
        col = col - 1;
        validate(row, col);

        //return (wf.find(oneD(row, col)) == wf.find(start) && array[row][col]);
        return (wf.connected(oneD(row, col), start) && array[row][col]);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return opened;
    }

    // does the system percolate?
    public boolean percolates() {
        //return wf.find(start) == wf.find(end);
        return wf.connected(start, end);
    }
}
