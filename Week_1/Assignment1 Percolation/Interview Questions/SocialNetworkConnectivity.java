/*
 * Given a social network containing nn members and a log file containing mm timestamps at which times pairs of members
 * formed friendships, design an algorithm to determine the earliest time at which all members are connected
 * (i.e., every member is a friend of a friend of a friend ... of a friend).
 *  Assume that the log file is sorted by timestamp and that friendship is an equivalence relation.
 * The running time of your algorithm should be "m log n" or better and use extra space proportional to nn.
 */

/*
 * Determine when all members are connected
 */

import edu.princeton.cs.algs4.QuickFindUF;

public class SocialNetworkConnectivity {

    private QuickFindUF uf; //instant UF data type
    private int numComponents; //number of components

    public SocialNetworkConnectivity(int N) {
        uf = new QuickFindUF(N);
        numComponents = N;
    }

    public void addFriendship(int p1, int p2) {
        if (!uf.connected(p1, p2)) {
            --numComponents;
        }
        uf.union(p1, p2)
    }

    public boolean fullyConnected() {
        return this.numComponents == 1;
    }


    public static void main(String[] args) {
        //initialize social network data structure with N sites

        while (!uf.isEmpty()) {
            
        }

    }
}
