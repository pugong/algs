import edu.princeton.cs.algs4.Point2D;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lpug on 05/09/2017.
 */
public class PointSETTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testIsEmpty() throws Exception {
        PointSET pointSET = new PointSET();
        assert (pointSET.isEmpty());

        pointSET.insert(new Point2D(0, 0));
        assert (!pointSET.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        PointSET pointSET = new PointSET();
        assert (pointSET.size() == 0);

        pointSET.insert(new Point2D(0.372, 0.497));
        assert (pointSET.size() == 1);
    }

    @Test
    public void testContains() throws Exception {
        PointSET pointSET = new PointSET();
        Point2D point = new Point2D(0.372, 0.497);
        pointSET.insert(point);
        assert (pointSET.contains(point));
        assert (!pointSET.contains(new Point2D(0,0)));
    }

    @Test
    public void testRange() throws Exception {

    }

    @Test
    public void testNearest() throws Exception {

        PointSET pointSET = new PointSET();

        Point2D nearest = new Point2D(0.417, 0.362);


        pointSET.insert(new Point2D(0.372, 0.497));
        pointSET.insert(new Point2D(0.564, 0.413));
        pointSET.insert(new Point2D(0.226, 0.577));
        pointSET.insert(new Point2D(0.144, 0.179));
        pointSET.insert(new Point2D(0.083, 0.510));
        pointSET.insert(new Point2D(0.320, 0.708));
        pointSET.insert(nearest);
        pointSET.insert(new Point2D(0.862, 0.825));
        pointSET.insert(new Point2D(0.785, 0.725));

        Point2D point = new Point2D(0.499, 0.208);

        assert (pointSET.nearest(point) == nearest);
    }
}