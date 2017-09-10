
## Algorithms Part 1 from Coursera


### Week 1: Percolation

    Percolation.java
    PercolationStats.java
    
    
Remaining Issues:

    Test 4: Create an n-by-n percolation system; interleave calls to open(),
            percolates(), isOpen(), isFull(), and numberOfOpenSites() until.
            the system percolates. The values of n go up by a factor of sqrt(2).
            The test is passed if n >= 4096 in under 10 seconds.
    
                            log   union-find     log
             n  seconds   ratio   operations   ratio
         -------------------------------------------
           128     0.16     3.9        67304     2.1
           181     0.63     3.9       129042     1.9
           256     2.47     4.0       258334     2.0
           362     9.99     4.0       528322     2.1
           512    39.46     4.0      1036660     1.9
         [ exceeded the time limit of 10.0 seconds ]
    
    ==> FAILED
    
### Week 2: Queue

    Deque.java
    RandomizeQueue.java
    Permutation.java
    
    
### Week 3 Collinear

    Point.java
    BruteCollinearPoints.java
    FastCollinearPoints.java
    
Remaining Issues
    
    check that data type is immutable by testing whether each method
    returns the same value, regardless of any intervening operations
             
     Analyzing memory of Point
     *-----------------------------------------------------------
     Running 1 total tests.
     
     The maximum amount of memory per Point object is 32 bytes.
     
     Student memory = 56 bytes (failed)
    
### Week 4 8 puzzle

    Board.java
    Solver.Java
    
Remaining Issues:

