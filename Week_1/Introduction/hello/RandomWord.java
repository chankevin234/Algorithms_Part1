import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int i = 1;
        String champion = "";
        while (!StdIn.isEmpty()) {
            String result = StdIn.readString();
            boolean chance = StdRandom.bernoulli((double) 1 / i);
            if (chance) {
                champion = result;
            }
            i++;
        }
        StdOut.println(champion);
    }
}
