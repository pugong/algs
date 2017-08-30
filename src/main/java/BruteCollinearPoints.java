import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lpug on 26/08/2017.
 */
public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
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
            if (points[i].compareTo(points[i+1]) == 0) {
                return true;
            }
        }
        return false;
    }

    private LineSegment[] getSegments(Point[] points)
    {
        int count = 0;
        List<LineSegment> lineSegments = new ArrayList<LineSegment>();
        Arrays.sort(points);
        int length = points.length;

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    for (int l = k + 1; l < length; l++) {
                        Point pointFirst = points[i];
                        Point pointSecond = points[j];
                        Point pointThird = points[k];
                        Point pointFourth = points[l];
                        if (pointFirst.slopeTo(pointSecond) == pointSecond.slopeTo(pointThird)
                                && pointSecond.slopeTo(pointThird) == pointThird.slopeTo(pointFourth)
                                && pointThird.slopeTo(pointFourth) == pointFourth.slopeTo(pointFirst)
//                                && pointFirst.compareTo(pointSecond) == pointSecond.compareTo(pointThird)
//                                && pointSecond.compareTo(pointThird) == pointThird.compareTo(pointFourth)
//                                && pointThird.compareTo(pointFourth) < 1
                                ) {
                            LineSegment lineSegment = new LineSegment(pointFirst, pointFourth);

                            lineSegments.add(lineSegment);
                            count++;
                        }
                    }
                }
            }
        }

        LineSegment[] result = lineSegments.toArray(new LineSegment[count]);

        return  result;
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

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
