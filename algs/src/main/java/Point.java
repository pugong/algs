import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

/**
 * Created by lpug on 25/08/2017.
 */
public final class Point implements Comparable<Point> {
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(this.x, this.y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        double deltaY = that.y - this.y;
        double deltaX = that.x - this.x;
        double slope;

        if (deltaX == 0 && deltaY == 0)
            slope = Double.NEGATIVE_INFINITY;
        else if (deltaX == 0)
            slope = Double.POSITIVE_INFINITY;
        else if (deltaY == 0)
            slope = 0.0;
        else
            slope =  deltaY / deltaX;

        return slope;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        int result;
        if (this.y > that.y)
            result = 1;
        else if (this.y < that.y)
            result = -1;
        else {
            if (this.x > that.x)
                result = 1;
            else if (this.x < that.x)
                result = -1;
            else
                result = 0;
        }
        return result;

    }

    private final Comparator<Point> slopeOrder = new PointComparator();

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    private final class PointComparator implements Comparator<Point> {
        /* YOUR CODE HERE */
        public int compare(Point aPoint, Point bPoint) {
            // aPoint.equals(bPoint);
            double slopeA = slopeTo(aPoint);
            double slopeB = slopeTo(bPoint);
            if (slopeA > slopeB) {
                return 1;
            } else if (slopeA < slopeB) {
                return -1;
            } else
                return 0;
        }
    }

    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        return this.slopeOrder;
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + this.x + ", " + this.y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point a = new Point(472, 858);
        Point b = new Point(472, 2);
        Point c = new Point(130, 2681);
        Point d = new Point(472, 2681);

        StdOut.println(a.slopeTo(b));
        StdOut.println(c.slopeTo(d));
        StdOut.println(b.slopeTo(c));
        StdOut.println(a.slopeTo(d));


        Point p = new Point(4,5);
        Point q = new Point(4, 1);
        Point r = new Point(9, 6);
        StdOut.println(p.slopeOrder().compare(q, r));

        // StdOut.println(a.compareTo(b));
        // StdOut.println(a.slopeOrder().compare(b, c));
    }
}
