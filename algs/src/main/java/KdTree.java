import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpug on 05/09/2017.
 */
public class KdTree {

    private static class Node {

        private final Point2D p;
        private final RectHV rect;
        private Node lb;
        private Node rt;

        private Node(Point2D p, RectHV r) {
            this.p = p;
            rect = r;
            lb = null;
            rt = null;
        }
    }

    private Node rootNode;
    private int size;

    public KdTree() {
        rootNode = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        if (rootNode == null) {
            rootNode = new Node(p, new RectHV(0, 0, 1, 1));
            size += 1;
        }

        Node node = rootNode;

        boolean compareXCoordinate = true;

        while (true) {

            if (node.p.equals(p)) return;

            if (compareXCoordinate && (p.x() < node.p.x())) {

                if (node.lb != null) {
                    node = node.lb;
                    compareXCoordinate = !compareXCoordinate;
                    continue;
                } else {
                    RectHV rectHV = new RectHV(
                            node.rect.xmin(),
                            node.rect.ymin(),
                            node.p.x(),
                            node.rect.ymax());
                    node.lb = new Node(p, rectHV);
                    size += 1;
                    return;
                }
            } else if (compareXCoordinate && (p.x() >= node.p.x())) {

                if (node.rt != null) {

                    node = node.rt;
                    compareXCoordinate = !compareXCoordinate;
                    continue;
                } else {

                    RectHV rectHV = new RectHV(
                            node.p.x(),
                            node.rect.ymin(),
                            node.rect.xmax(),
                            node.rect.ymax());
                    node.rt = new Node(p, rectHV);
                    size += 1;
                    return;
                }
            } else if (!compareXCoordinate && (p.y() < node.p.y())) {

                if (node.lb != null) {

                    node = node.lb;
                    compareXCoordinate = !compareXCoordinate;
                    continue;
                } else {

                    RectHV rectHV = new RectHV(
                            node.rect.xmin(),
                            node.rect.ymin(),
                            node.rect.xmax(),
                            node.p.y());

                    node.lb = new Node(p, rectHV);
                    size += 1;
                    return;
                }
            } else {

                if (node.rt != null) {

                    node = node.rt;
                    compareXCoordinate = !compareXCoordinate;
                    continue;
                } else {

                    RectHV rectHV = new RectHV(
                            node.rect.xmin(),
                            node.p.y(),
                            node.rect.xmax(),
                            node.rect.ymax());

                    node.rt = new Node(p, rectHV);
                    size += 1;
                    return;
                }
            }
        }
    }


    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        Node node = rootNode;

        boolean compareX = true;

        while (true) {
            if (node == null) return false;
            if (node.p.equals(p)) return true;

            if ((compareX && (p.x() < node.p.x()))
                    || (!compareX && (p.y() < node.p.y()))) {
                node = node.lb;
            } else {
                node = node.rt;
            }
            compareX = !compareX;
            continue;
        }
    }


    public void draw() {
        draw(rootNode, true);
    }

    private void draw(Node node, boolean compareX) {
        if (node != null) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            node.p.draw();

            StdDraw.setPenRadius(0.002);
            if (compareX) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
            }
            draw(node.lb, !compareX);
            draw(node.rt, !compareX);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException();

        List<Point2D> points = new ArrayList<Point2D>();
        if (rootNode != null) {
            range(rect, rootNode, points);
        }
        return points;
    }

    private List<Point2D> range(RectHV rect, Node node, List<Point2D> points) {
        if (node == null)
            return points;

        if (rect.distanceSquaredTo(node.p) == 0) {
            points.add(node.p);
        }

        if (rect.intersects(node.rect)) {
            range(rect, node.lb, points);
            range(rect, node.rt, points);
        }
        return points;
    }


    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException();

        if (rootNode == null)
            return null;

        return nearest(p, rootNode, rootNode.p, true);
    }

    private Point2D nearest(Point2D point, Node node, Point2D rootPoint, boolean compareXCoordinate) {
        if (node == null)
            return rootPoint;

        if (point.distanceSquaredTo(node.p) < point.distanceSquaredTo(rootPoint)) {
            rootPoint = node.p;
        }

        if (node.rect.distanceSquaredTo(point) < point.distanceSquaredTo(rootPoint)) {

            if ((compareXCoordinate && point.x() < node.p.x()) ||
                    (!compareXCoordinate && point.y() < node.p.y())) {

                rootPoint = nearest(point, node.lb, rootPoint, !compareXCoordinate);
                rootPoint = nearest(point, node.rt, rootPoint, !compareXCoordinate);
            } else {


                rootPoint = nearest(point, node.rt, rootPoint, !compareXCoordinate);
                rootPoint = nearest(point, node.lb, rootPoint, !compareXCoordinate);
            }
        }
        return rootPoint;
    }

    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);

        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
        }

        kdtree.draw();
        StdDraw.show();
    }
}