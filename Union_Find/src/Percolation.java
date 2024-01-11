import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static int size;
    private boolean[][] grid;
    private int noa;
    private WeightedQuickUnionUF arrayQuickUnion;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        else {
            size = n;
            grid = new boolean[size][size];
            arrayQuickUnion = new WeightedQuickUnionUF(size * size + 2);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    grid[i][j] = false;
                }
            }
        }
    }


    public void open(int raw_row, int raw_col) {
        int row = raw_row - 1;
        int col = raw_col - 1;
        if (grid[row][col] != true) {
            grid[row][col] = true;
            noa++;
            int id = row * size + col; 
            if (row != 0) {
                if (isOpen(raw_row - 1, raw_col)) { 
                    arrayQuickUnion.union(id - size, id);
                } 
            } 
            if (row != size - 1) {
                if (isOpen(raw_row + 1, raw_col)) {
                    arrayQuickUnion.union(id + size, id);
                }
            }
            if (col != 0) {
                if (isOpen(raw_row, raw_col - 1)) {
                    arrayQuickUnion.union(id - 1, id);
                }
            }
            if (col != size - 1) {
                if (isOpen(raw_row, raw_col + 1)) {
                    arrayQuickUnion.union(id + 1, id);
                }
            }
            if (row == 0) {
                arrayQuickUnion.union(size * size, id);
            }
            if (row == size - 1) {
                arrayQuickUnion.union(size * size + 1, id);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int raw_row, int raw_col) {
        int row = raw_row - 1;
        int col = raw_col - 1;
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int raw_row, int raw_col) {
        int row = raw_row - 1;
        int col = raw_col - 1;
        int id = row * size + col;
        return arrayQuickUnion.find(size * size) == arrayQuickUnion.find(id);
    }
        
    // returns the number of open sites
    public int numberOfOpenSites() {
        return noa;
    }

    // does the system percolate?
    public boolean percolates() {
        return arrayQuickUnion.find(size * size) == arrayQuickUnion.find(size * size + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        System.out.println(p.isFull(1, 1));
        System.out.println(p.isOpen(1,1));
        System.out.println(p.percolates());
        System.out.println(p.numberOfOpenSites());
    }
}