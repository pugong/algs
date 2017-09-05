import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpug on 31/08/2017.
 */
public final class Board {
    private final int[] blockArray;
    private final int boardDimension;
    private final int blankIndex;

    public Board(int[][] blocks) {
        if (blocks == null || blocks.length == 0)
            throw new IllegalArgumentException();

        boardDimension = blocks.length;

        blockArray = new int[boardDimension * boardDimension];

        int zeroIndex = 0;
        int i = 0;
        for (int[] block : blocks) {
            for (int point : block) {
                blockArray[i] = point;
                if (point == 0)
                    zeroIndex = i;
                i++;
            }
        }
        blankIndex = zeroIndex;
    }

    private Board(int[] points, int boardDimension) {
        this.blockArray = points;
        this.boardDimension = boardDimension;
        int zeroIndex = 0;
        for (int i = 0; i < points.length; i++) {
            if (points[i] == 0) {
                zeroIndex = i;
                break;
            }
        }
        blankIndex = zeroIndex;
    }

    public int dimension() {
        return boardDimension;
    }

    /* Hamming priority function. The number of blocks in the wrong position,
         plus the number of moves made so far to get to the search node.
         Intuitively, a search node with a small number of blocks in the wrong
         position is close to the goal, and we prefer a search node that have been
         reached using a small number of moves.*/
    public int hamming() {
        int distance = 0;
        for (int i = 0; i < blockArray.length; i++) {
            if (blockArray[i] != 0 && blockArray[i] != i + 1)
                distance++;
        }
        return distance;
    }

    /* Manhattan priority function. The sum of the Manhattan distances
        (sum of the vertical and horizontal distance) from the blocks to their goal
        positions, plus the number of moves made so far to get to the search node.*/
    public int manhattan() {
        int distance = 0;
        for (int i = 0; i < blockArray.length; i++) {
            if (blockArray[i] != 0 && blockArray[i] != i + 1) {
                int currRow = i / boardDimension;
                int currCol = i % boardDimension;
                int targetRow = (blockArray[i] - 1) / boardDimension;
                int targetCol = (blockArray[i] - 1) % boardDimension;
                distance += Math.abs(targetRow - currRow) + Math.abs(targetCol - currCol);
            }
        }
        return distance;
    }

    public boolean isGoal() {
        if (blockArray[blockArray.length - 1] != 0) return false;
        for (int i = 0; i < blockArray.length - 1; i++) {
            if (blockArray[i] != i + 1)
                return false;
        }

        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int oriRow = StdRandom.uniform(boardDimension);
        int oriCol = StdRandom.uniform(boardDimension - 1);

        int intOriIndex = oriRow * boardDimension + oriCol;
        if (blockArray[intOriIndex] != 0 && blockArray[intOriIndex + 1] != 0)
            return ExchangeBlocksPair(intOriIndex, intOriIndex + 1);
        else
            return twin();

    }

    private Board ExchangeBlocksPair(int firstIndex, int secondIndex) {
        int[] copyArray = blockArray.clone();
        int t = copyArray[firstIndex];
        copyArray[firstIndex] = copyArray[secondIndex];
        copyArray[secondIndex] = t;
        return new Board(copyArray, boardDimension);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;

        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }

        Board yy = (Board) y;

        if (yy.boardDimension != this.boardDimension)
            return false;

        if (blockArray.length != yy.blockArray.length)
            return false;

        for (int i = 0; i < blockArray.length; i++) {
            if (blockArray[i] != yy.blockArray[i]) {
                return false;
            }
        }

        return true;
    }


    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<Board>();
        for (int row = 0; row < boardDimension; row++)
            for (int col = 0; col < boardDimension - 1; col++) {
                int oriIndex = row * dimension() + col;

                if (blockArray[oriIndex] != 0 && blockArray[oriIndex + 1] != 0)
                    neighbors.add(ExchangeBlocksPair(oriIndex, oriIndex + 1));

                // try to swap up/down (the meaning row and col switched)
                int upIndex = col * dimension() + row;
                int downIndex = (col + 1) * dimension() + row;
                if (blockArray[upIndex] != 0 && blockArray[downIndex] != 0)
                    neighbors.add(ExchangeBlocksPair(upIndex, downIndex));
            }

        return neighbors;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(boardDimension);
        sb.append("\n");
        for (int i = 0; i < boardDimension; i++) {
            for (int j = 0; j < boardDimension; j++) {
                sb.append(" ");
                sb.append(blockArray[i * boardDimension + j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] b = new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
//        int[][] c = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        Board board = new Board(b);
//        String target = board.toString();
        System.out.println(board.toString());
//        System.out.println(board.toString().equals(target));
        System.out.println(board.manhattan());
        System.out.println(board.hamming());
        System.out.println(board.isGoal());
        Board twin = board.twin();
        System.out.print(twin.toString());

//        for (Board indv : board.neighbors()) {
//            System.out.print(indv.toString());
//        }

    }
}
