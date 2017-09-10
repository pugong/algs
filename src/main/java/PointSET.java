import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by lpug on 05/09/2017.
 */
public class PointSET {
    private final TreeSet<Point2D> pointSet;

    public PointSET() {
        pointSet = new TreeSet<Point2D>();
    }

    public boolean isEmpty() {
        return pointSet.size() == 0;
    }

    public int size() {
        return pointSet.size();
    }

    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        pointSet.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        return pointSet.contains(p);
    }

    public void draw() {
        for (Point2D p : pointSet) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();

        List<Point2D> pointList = new ArrayList<Point2D>();
        for (Point2D point : pointSet) {
            if (rect.distanceSquaredTo(point) == 0)
                pointList.add(point);
        }
        return pointList;
    }

    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        if (pointSet == null || pointSet.isEmpty())
        return null;

        Double distance = Double.MAX_VALUE;
        Point2D nearestPoint = null;
        for (Point2D point : pointSet)
            if (p.distanceTo(point) < distance) {
                nearestPoint = point;
                distance = p.distanceTo(point);
            }

        return nearestPoint;
    }

    public static void main(String[] args) {

        PointSET pointSET = new PointSET();

        pointSET.insert(new Point2D(0.372, 0.497));
        pointSET.insert(new Point2D(0.564, 0.413));
        pointSET.insert(new Point2D(0.226, 0.577));
        pointSET.insert(new Point2D(0.144, 0.179));
        pointSET.insert(new Point2D(0.083, 0.510));
        pointSET.insert(new Point2D(0.320, 0.708));
        pointSET.insert(new Point2D(0.417, 0.362));
        pointSET.insert(new Point2D(0.862, 0.825));
        pointSET.insert(new Point2D(0.785, 0.725));
        // pointSET.insert(new Point2D(0.499, 0.208));

        Point2D point = new Point2D(0.499, 0.208);
        StdOut.println(pointSET.size());
        StdOut.println(pointSET.nearest(point));

//        pointSET.draw();
//        StdDraw.show();
    }
}
