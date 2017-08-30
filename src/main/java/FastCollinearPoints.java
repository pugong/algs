import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by lpug on 29/08/2017.
 */
public class FastCollinearPoints {
    private final LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        if(hasNullPoint(points))
            throw new IllegalArgumentException();

        if(hasDuplicatePoints(points))
            throw new IllegalArgumentException();

        lineSegments = getSegments(points);
    }


    private boolean hasNullPoint(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDuplicatePoints(Point[] points) {
        Arrays.sort(points);
        for (int i = 0; i < points.length-1; i++) {
            if(points[i] == null || points[i+1] == null)
                return true;
            else if (points[i].compareTo(points[i+1]) == 0) {
                return true;
            }
        }
        return false;
    }

    private LineSegment[] getSegments(Point[] points) {
        Point[][] lineSegments = new Point[points.length][2];
        int lineSegmentAmount = 0;
        Point[] copiedPoints = Arrays.copyOf(points, points.length);
        for (Point p : points) {
            Arrays.sort(copiedPoints, p.slopeOrder());

            Point[] collinearPoints = new Point[points.length];
            collinearPoints[0] = p;
            int amount = 1;
            for (int i = 0; i < copiedPoints.length; i++) {
                Point q = copiedPoints[i];
                if (p.slopeTo(q) != Double.NEGATIVE_INFINITY) {
                    collinearPoints[amount] = q;
                    amount++;
                }

                if (i == copiedPoints.length - 1 || p.slopeTo(copiedPoints[i]) != p.slopeTo(copiedPoints[i + 1])) {
                    if (amount >= 4 && p.slopeTo(q) != Double.NEGATIVE_INFINITY) {
                        collinearPoints = Arrays.copyOf(collinearPoints, amount);
                        Arrays.sort(collinearPoints);
                        Point[] segment = new Point[]{collinearPoints[0], collinearPoints[amount - 1]};
                        if (!isDuplicated(lineSegments, segment)) {
                            if (lineSegmentAmount == lineSegments.length) {
                                lineSegments = Arrays.copyOf(lineSegments, 2 * lineSegmentAmount);
                            }
                            lineSegments[lineSegmentAmount] = segment;
                            lineSegmentAmount++;
                        }
                    }
                    collinearPoints = new Point[points.length];
                    collinearPoints[0] = p;
                    amount = 1;
                }
            }
        }

        LineSegment[] segments = new LineSegment[lineSegmentAmount];
        for (int i = 0; i < lineSegmentAmount; i++) {
            Point[] rawLine = lineSegments[i];
            segments[i] = new LineSegment(rawLine[0], rawLine[1]);
        }
        return segments;
    }

    private boolean isDuplicated(Point[][] lineSegments, Point[] segment) {
        for (Point[] pair : lineSegments) {
            if (pair == null) break;
            if (pair.length == 2 && segment.length == 2 && pair[0] == segment[0] && pair[1] == segment[1]) {
                return true;
            }
        }

        return false;
    }

    public int numberOfSegments() {
        return this.lineSegments.length;
    }

    public LineSegment[] segments() {
        return this.lineSegments;
    }


    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        FastCollinearPoints collinear = new FastCollinearPoints(points);

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        LineSegment[] segments = collinear.segments();
        StdOut.println("The final results: ");
        for (LineSegment segment : segments) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
