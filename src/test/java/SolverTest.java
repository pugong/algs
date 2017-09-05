import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lpug on 02/09/2017.
 */
public class SolverTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testIsSolvable() throws Exception {
        int[][] a = new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        int[][] b = new int[][]{{1, 2, 3}, {4, 6, 5}, {7, 8, 0}};

        Board boardFirst = new Board(a);
        Board boardSecond = new Board(b);
        Solver solverFirst = new Solver(boardFirst);
        Solver solverSecond = new Solver(boardSecond);

        assert (solverFirst.isSolvable());
        assert(!solverSecond.isSolvable());
    }

    @Test
    public void testMoves() throws Exception {

        int[][] a = new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        int[][] b = new int[][]{{1, 2, 3}, {4, 6, 5}, {7, 8, 0}};

        Board boardFirst = new Board(a);
        Board boardSecond = new Board(b);
        Solver solverFirst = new Solver(boardFirst);
        Solver solverSecond = new Solver(boardSecond);

        assert (solverFirst.moves() == 4);
        assert (solverSecond.moves() == -1);
    }

    @Test
    public void testSolution() throws Exception {

    }
}