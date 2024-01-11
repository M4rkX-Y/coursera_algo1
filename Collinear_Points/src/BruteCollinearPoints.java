import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class BruteCollinearPoints {

    private int nos = 0;
    private LineSegment[] s;
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        Point first = null;
        Point last = null;
        s = new LineSegment[points.length];
        for (int i = 0; i < points.length; i++) {
            Point a = points[i];
            if (a == null) throw new IllegalArgumentException();
            for (int j = i + 1; j < points.length; j++) {
                Point b = points[j];
                if (a.slopeTo(b) == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException();
                    for (int k = j + 1; k < points.length; k++) {
                        Point c = points[k];
                        if (a.slopeTo(b) == a.slopeTo(c)) {
                            for (int l = k + 1; l < points.length; l++) {
                                Point d = points[l];
                                if (a.slopeTo(c) == a.slopeTo(d)) {
                                    if (a.compareTo(b) < 0 && a.compareTo(c) < 0 && a.compareTo(d) < 0) first = a;
                                    else if (b.compareTo(a) < 0 && b.compareTo(c) < 0 && b.compareTo(d) < 0) first = b;
                                    else if (c.compareTo(a) < 0 && c.compareTo(b) < 0 && c.compareTo(d) < 0) first = c;
                                    else if (d.compareTo(a) < 0 && d.compareTo(b) < 0 && d.compareTo(c) < 0) first = d;
                                    if (a.compareTo(b) > 0 && a.compareTo(c) > 0 && a.compareTo(d) > 0) last = a;
                                    else if (b.compareTo(a) > 0 && b.compareTo(c) > 0 && b.compareTo(d) > 0) last = b;
                                    else if (c.compareTo(a) > 0 && c.compareTo(b) > 0 && c.compareTo(d) > 0) last = c;
                                    else if (d.compareTo(a) > 0 && d.compareTo(b) > 0 && d.compareTo(c) > 0) last = d;
                                    s[nos++] = new LineSegment(first, last);
                                }
                            }
                        } 
                    }
                }
            }
        }

    // the number of line segments
    public int numberOfSegments() {
        return nos;
    }

    // the line segments
    public LineSegment[] segments() {
        if (nos != 0) {
            LineSegment[] copy = new LineSegment[nos];
            for (int i = 0; i < nos; i++){
                copy[i] = s[i];
            }
            return copy;
        }
        else throw new IllegalArgumentException();
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
        
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}