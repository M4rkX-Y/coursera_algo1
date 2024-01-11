import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private static int t;
    private double[] counts;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        counts = new double[trials];
        t = trials;
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            int j;
            for (j = 1; !p.percolates();) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    j++;
                }
            }
            counts[i] = (double) j / (double) (n * n);
        }
    }

    // sample mean of percolation thresholds
    public double mean() {
        return StdStats.mean(counts);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(counts);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(t));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(t));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.valueOf(args[0]);
        int T = Integer.valueOf(args[0]);
        PercolationStats p = new PercolationStats(n, T);
        StdOut.println("mean                    = " + p.mean());
        StdOut.println("stddev                  = " + p.stddev());
        StdOut.println("95% confidence interval = [" + p.confidenceLo() + ", " + p.confidenceHi() + "]");
    }

}