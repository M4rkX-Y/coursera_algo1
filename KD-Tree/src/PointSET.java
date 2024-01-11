import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.RectHV;
import java.util.TreeSet;
import java.util.NavigableSet;

public class PointSET {

    private TreeSet<Point2D> pSet;

    public PointSET() {   // construct an empty set of points 
        TreeSet<Point2D> empSet = new TreeSet<Point2D>();
        pSet = empSet;
    }   

    public boolean isEmpty() {   // is the set empty? 
        return pSet.isEmpty();
    }                     
    public int size() {  // number of points in the set 
        return pSet.size();
    }                      
    public void insert(Point2D p) {   // add the point to the set (if it is not already in the set)
        pSet.add(p);
    }  

    public boolean contains(Point2D p) {   // does the set contain point p? 
        if (pSet.isEmpty()) throw new IllegalArgumentException();
        return pSet.contains(p);
    }           
    public void draw() {   // draw all points to standard draw
        if (pSet.isEmpty()) throw new IllegalArgumentException();
        for (Point2D point: pSet) {
            point.draw();
        }
    }                         
    public Iterable<Point2D> range(RectHV rect) {  // all points that are inside the rectangle (or on the boundary) 
        if (pSet.isEmpty()) throw new IllegalArgumentException();
        Point2D bottom_left = new Point2D(rect.xmin(), rect.xmin());
        Point2D top_right = new Point2D(rect.xmax(),rect.ymax());
        NavigableSet<Point2D> rSet = pSet.subSet(bottom_left, true, top_right, true);
        return rSet;
    }  
          
    public Point2D nearest(Point2D p) {   // a nearest neighbor in the set to point p; null if the set is empty 
        if (pSet.isEmpty()) return null;
        Point2D lower = pSet.lower(p);
        Point2D higher = pSet.higher(p);
        if (lower == null) return higher;
        else if (higher == null) return lower;
        else if (lower.distanceTo(p) < higher.distanceTo(p)) return lower;
        else return higher;
    }  

 
    public static void main(String[] args) {
        StdDraw.setPenRadius(0.01);
        StdDraw.setScale(-10.0, 10.0);
        PointSET test = new PointSET();
        for (int i = 0; i < 10; i++) {
            Point2D point = new Point2D(i, i);
            test.insert(point);
        }
        test.draw();
        RectHV r = new RectHV(0, 0, 3, 3);
        for (Point2D rpoint: test.range(r)) {
            System.out.println(rpoint);
        }
    }
 }