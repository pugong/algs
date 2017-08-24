import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[] isOpen;
    private final WeightedQuickUnionUF percolationUF;
    private final WeightedQuickUnionUF fullUF;
    private final int gridLength;
    private final int[] mx = { -1, 0, 1, 0 };
    private final int[] my = { 0, -1, 0, 1 };

    /// create n-by-n grid, with all sites blocked
    public Percolation(int n)
    {
        isOpen = new boolean[n * n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                isOpen[i * n + j] = false;
        this.gridLength = n;
        percolationUF = new WeightedQuickUnionUF(n * n + 2);
        fullUF = new WeightedQuickUnionUF(n * n + 1);
    }

    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        if (row < 1 || col < 1 || row > gridLength || col > gridLength)
            throw new IndexOutOfBoundsException();
        int ni = row - 1, nj = col - 1;
        if (!isOpen[ni * gridLength + nj]) {
            isOpen[ni * gridLength + nj] = true;
            for (int m = 0; m < 4; m++) {
                int mi = ni + mx[m];
                int mj = nj + my[m];
                if (mi >= 0 && mj >= 0 && mi < gridLength && mj < gridLength && isOpen[mi * gridLength + mj]) {
                    percolationUF.union(ni * gridLength + nj, mi * gridLength + mj);
                    fullUF.union(ni * gridLength + nj, mi * gridLength + mj);
                }
            }
            if (row == 1) {
                percolationUF.union(ni * gridLength + nj, gridLength * gridLength);
                fullUF.union(ni * gridLength + nj, gridLength * gridLength);
            }
            if (row == gridLength)
                percolationUF.union(ni * gridLength + nj, gridLength * gridLength + 1);
        }
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        if (row < 1 || col < 1 || row > gridLength || col > gridLength)
            throw new IndexOutOfBoundsException();
        int ni = row - 1, nj = col - 1;
        return isOpen[ni * gridLength + nj];
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if (row < 1 || col < 1 || row > gridLength || col > gridLength)
            throw new IndexOutOfBoundsException();
        int ni = row - 1, nj = col - 1;
        return isOpen[ni * gridLength + nj] && fullUF.connected(ni * gridLength + nj, gridLength * gridLength);
    }

    public int numberOfOpenSites()       // number of open sites
    {
        int count = 0;
        for (int i = 0; i < gridLength; i++)
            for (int j = 0; j < gridLength; j++)
                if(isOpen[i * gridLength + j])
                    count++;

        return count;
    }

    public boolean percolates()              // does the system percolate?
    {
        return percolationUF.connected(gridLength * gridLength, gridLength * gridLength + 1);

    }

    public static void main(String[] args)   // test client (optional)
    {
        System.out.print("Hello");
    }
}