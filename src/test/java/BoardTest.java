import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lpug on 01/09/2017.
 */
public class BoardTest {


    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstruction() {

        int[][] a = new int[][]{};
        int[][] b = null;

        Board boardFirst = new Board(a);
        Board boardSecond = new Board(b);
    }


    @Test
    public void testDimension() throws Exception {
        int[][] a = new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        int[][] b = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        Board boardFirst = new Board(a);
        Board boardSecond = new Board(b);

        assert(boardFirst.dimension() == 3);
        assert(boardSecond.dimension() == 3);
    }

    @Test
    public void testHamming() throws Exception {
        int[][] a = new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        int[][] b = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        Board boardFirst = new Board(a);
        Board boardSecond = new Board(b);
        assert(boardFirst.hamming() == 5);
        assert(boardSecond.hamming() == 0);
    }

    @Test
    public void testManhattan() throws Exception {
        int[][] a = new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        int[][] b = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        Board boardFirst = new Board(a);
        Board boardSecond = new Board(b);
        assert(boardFirst.manhattan() == 10);
        assert(boardSecond.manhattan() == 0);
    }

    @Test
    public void testIsGoal() throws Exception {

        int[][] a = new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        int[][] b = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        Board boardFirst = new Board(a);
        Board boardSecond = new Board(b);
        assert(boardFirst.isGoal() == false);
        assert(boardSecond.isGoal() == true);
    }


    @Test
    public void testToString() throws Exception {

        String target = "3\n" +
                " 8  1  3 \n" +
                " 4  0  2 \n" +
                " 7  6  5 \n";
        int[][] a = new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};

        Board board = new Board(a);

        assert(board.toString().equals(target));

    }
}