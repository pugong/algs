import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpug on 31/08/2017.
 */
public final class Board {
    private int blankIndex;
    private final int[] blockArray;
    private final int boardDimension;
    private final int hamming;
    private final int manhattan;

    public Board(int[][] blocks) {
        if (blocks == null || blocks.length == 0 || blocks[0].length == 0)
            throw new IllegalArgumentException();

        boardDimension = blocks.length;

        this.blockArray = new int[boardDimension * boardDimension];
        int i = 0;
        for (int[] block : blocks) {
            for (int point : block) {
                this.blockArray[i] = point;
                if (point == 0) {
                    blankIndex = i;
                }
                i++;
            }
        }

        this.manhattan =getManhattan();
        this.hamming = getHamming();
    }

    private Board(int[] blockArray, int boardDimension) {
        this.blockArray = blockArray;
        this.boardDimension = boardDimension;
        for (int i = 0; i < blockArray.length; i++) {
            if (blockArray[i] == 0) {
                blankIndex = i;
                break;
            }
        }
        this.manhattan =getManhattan();
        this.hamming = getHamming();
    }

    private int getHamming() {
        int distance = 0;
        for (int i = 0; i < blockArray.length; i++) {
            if (blockArray[i] != 0 && blockArray[i] != i + 1) {
                distance++;
            }
        }
        return distance;
    }

    private int getManhattan() {
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

    public int dimension() {
        return boardDimension;
    }

    public int hamming() {
        return hamming;
    }

    public int manhattan() {
        return manhattan;
    }

    public boolean isGoal() {
        if (blockArray[blockArray.length - 1] != 0) return false;
        else {
            for (int i = 0; i < blockArray.length - 1; i++) {
                if (blockArray[i] != i + 1) {
                    return false;
                }
            }
            return true;
        }

    }

    public Board twin() {
        for (int row = 0; row < boardDimension; row++) {
            for (int col = 0; col < boardDimension - 1; col++) {
                if (blockArray[row * boardDimension + col] != 0 && blockArray[row * boardDimension + col + 1] != 0) {
                    int oriIndex = row * boardDimension + col;
                    int destIndex = row * boardDimension + col + 1;
                    return getExchangePairedBoard(oriIndex, destIndex);
                }
            }
        }

        return this;
    }

    private Board getExchangePairedBoard(int oriIndex, int destIndex) {
        int[] newData = new int[blockArray.length];
        System.arraycopy(blockArray, 0, newData, 0, blockArray.length);
        int temp = newData[oriIndex];
        newData[oriIndex] = newData[destIndex];
        newData[destIndex] = temp;
        return new Board(newData, boardDimension);
    }

    public boolean equals(Object y) {
        if (y == this) return true;

        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }

        Board yy = (Board) y;
        if (blockArray.length != yy.blockArray.length)
            return false;

        for (int i = 0; i < blockArray.length; i++) {
            try {
                if (blockArray[i] != yy.blockArray[i]) {
                    return false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<Board>(4);
        int blankRow = blankIndex / boardDimension;
        int blandCol = blankIndex % boardDimension;
        for (int[] d : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
            int row = blankRow + d[0];
            int col = blandCol + d[1];
            if (0 <= row && row < boardDimension &&
                    0 <= col && col < boardDimension) {
                neighbors.add(getExchangePairedBoard(blankIndex, row * boardDimension + col));
            }
        }
        return neighbors;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(boardDimension);
        sb.append("\n");
        for (int i = 0; i < boardDimension; i++) {
            for (int j = 0; j < boardDimension; j++) {
//                sb.append(" ");
                sb.append(String.format("%2d ",blockArray[i * boardDimension + j]));
//                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] b = new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
//        int[][] c = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        Board board = new Board(b);
        System.out.println(board.toString());
        System.out.println(board.manhattan());
        System.out.println(board.hamming());
        System.out.println(board.isGoal());
        Board twin = board.twin();
        System.out.print(twin.toString());


    }
}
