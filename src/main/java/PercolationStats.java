import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] thresholdResults;

    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        if (n<1 || trials<1)
        {
            throw new IllegalArgumentException("both arguments N and T must be greater than 1");
        }
        thresholdResults = new double[trials];
        for (int t = 0; t < trials; t++)
        {
            Percolation percolation = new Percolation(n);
            int openSites = 0;
            while (!percolation.percolates())
            {
                int i = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1,n+1);

                if (!percolation.isOpen(i,j))
                {
                    percolation.open(i,j);
                    openSites += 1;
                }
            }
            double threshold = (double)openSites/(double)(n*n);
            thresholdResults[t] = threshold;
        }
    }

    public double mean()                          // sample mean of percolation threshold
    {
        return StdStats.mean(thresholdResults);
    }
    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(thresholdResults);
    }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return mean() - (1.96*stddev()/Math.sqrt(thresholdResults.length));
    }

    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return mean() + (1.96*stddev()/Math.sqrt(thresholdResults.length));
    }

    public static void main(String[] args)        // test client (described below)
    {
        int iGridLength = Integer.parseInt(args[0]);
        int iLength = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(iGridLength,iLength);
        StdOut.println("mean = " + stats.mean());
        StdOut.println("standard deviation = " + stats.stddev());
        StdOut.println("95% confidence interval = " + stats.confidenceLo() + " , " + stats.confidenceHi());

    }
}
