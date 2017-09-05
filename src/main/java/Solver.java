import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.Stack;

/**
 * Created by lpug on 31/08/2017.
 */
public class Solver {
    private int moves;
    private Iterable<Board> solution;
    private boolean isSolvable;

    private static final Comparator<SearchNode> SEARCH_NODE_COMPARATOR = new Comparator<SearchNode>() {
        public int compare(SearchNode searchNode, SearchNode searchNodeSecond) {
            int manhattanPriorityFirst = searchNode.getManhattanPriority();
            int manhattanPrioritySecond = searchNodeSecond.getManhattanPriority();
//            if (manhattanPriorityFirst == manhattanPrioritySecond) {
//                return searchNode.getHammingPriority() - searchNodeSecond.getHammingPriority();
//            } else {
                return manhattanPriorityFirst - manhattanPrioritySecond;
//            }
        }
    };


    public Solver(Board initial) {
        this(initial, SEARCH_NODE_COMPARATOR);
    }

    private Solver(Board initial, Comparator<SearchNode> comp) {
        if (initial == null)
            throw new IllegalArgumentException();

        MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>(comp);
        MinPQ<SearchNode> twinMinPQ = new MinPQ<SearchNode>(comp);
        minPQ.insert(new SearchNode(initial, 0, null));
        twinMinPQ.insert(new SearchNode(initial.twin(), 0, null));

        while (!minPQ.isEmpty() && !twinMinPQ.isEmpty()) {
            SearchNode currNode = minPQ.delMin();
            SearchNode twinCurrNode = twinMinPQ.delMin();

            if (currNode.board.isGoal()) {
                solution = currNode.solution();
                moves = currNode.moves;
                isSolvable = true;
                break;
            } else if (twinCurrNode.board.isGoal()) {
                solution = null;
                moves = -1;
                isSolvable = false;
                break;
            } else {
                for (Board board : currNode.board.neighbors()) {
                    if (currNode.prev == null ||
                            !board.equals(currNode.prev.board)) {
                        minPQ.insert(new SearchNode(board, currNode.moves + 1, currNode));
                    }
                }
                for (Board board : twinCurrNode.board.neighbors()) {
                    if (twinCurrNode.prev == null ||
                            !board.equals(twinCurrNode.prev.board)) {
                        twinMinPQ.insert(new SearchNode(board, twinCurrNode.moves + 1, twinCurrNode));
                    }
                }
            }
        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        return solution;
    }

    private static final class SearchNode {
        private Board board;
        private int moves;
        private SearchNode prev;
        private int hamming;
        private int manhattan;

        private SearchNode(Board board, int moves, SearchNode prev) {
            this(board, moves, prev, -1);
        }

        private SearchNode(Board board, int moves, SearchNode prev, int manhattan) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.hamming = -1;
            this.manhattan = manhattan;
        }

        private int getHammingPriority() {
            if (this.hamming == -1) {
                this.hamming = board.hamming();
            }
            return this.hamming + moves;
        }

        private int getManhattanPriority() {
            if (this.manhattan == -1) {
                this.manhattan = board.manhattan();
            }
            return this.manhattan + moves;
        }

        private Iterable<Board> solution() {
            Stack<Board> solution = new Stack<Board>();
            SearchNode searchNode = this;
            while (searchNode != null) {
                solution.push(searchNode.board);
                searchNode = searchNode.prev;
            }
            return solution;
        }
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

    }


}
