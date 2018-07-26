
## Remaining Issues

Just Keep issues here temporary. The issues will be moved to github issues later.

### Percolation 


      Create an n-by-n percolation system; interleave calls to open(),
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
    
###  Queue

    None
    
### Collinear

    
    * check that data type is immutable by testing whether each method
    returns the same value, regardless of any intervening operations
             
     * Analyzing memory of Point
     *-----------------------------------------------------------
     Running 1 total tests.
     
     The maximum amount of memory per Point object is 32 bytes.
     
     Student memory = 56 bytes (failed)
    
### 8 puzzle

    Lots of "wrong initial board"



### kdtree

    
    Test 4a: insert points from file; check range() with random query rectangles
             and check traversal of kd-tree
      * input5.txt
        - failed on trial 1 of 1000
        - performs incorrect traversal of kd-tree during call to range()
        - do not check whether the point is inside the query rectangle unless
          the rectangle corresponding to the node intersects the query rectangle
    
      * input10.txt
        - failed on trial 1 of 1000
        - performs incorrect traversal of kd-tree during call to range()
        - do not check whether the point is inside the query rectangle unless
          the rectangle corresponding to the node intersects the query rectangle
    
    ==> FAILED
    
    Test 4b: insert non-degenerate points; check range() with random query rectangles
             and check traversal of kd-tree
      * 3 random non-degenerate points and 1000 random rectangles in a 4-by-4 grid
      * 6 random non-degenerate points and 1000 random rectangles in a 8-by-8 grid
        - failed on trial 1 of 1000
        - performs incorrect traversal of kd-tree during call to range()
        - do not check whether the point is inside the query rectangle unless
          the rectangle corresponding to the node intersects the query rectangle
    
      * 10 random non-degenerate points and 1000 random rectangles in a 16-by-16 grid
        - failed on trial 1 of 1000
        - performs incorrect traversal of kd-tree during call to range()
        - do not check whether the point is inside the query rectangle unless
          the rectangle corresponding to the node intersects the query rectangle
    
      * 20 random non-degenerate points and 1000 random rectangles in a 32-by-32 grid
        - failed on trial 1 of 1000
        - performs incorrect traversal of kd-tree during call to range()
        - do not check whether the point is inside the query rectangle unless
          the rectangle corresponding to the node intersects the query rectangle
    
      * 30 random non-degenerate points and 1000 random rectangles in a 64-by-64 grid
        - failed on trial 1 of 1000
        - performs incorrect traversal of kd-tree during call to range()
        - do not check whether the point is inside the query rectangle unless
          the rectangle corresponding to the node intersects the query rectangle
    
    ==> FAILED
    
    
    Test 6a: insert points from file; check nearest() with random query points
             and check traversal of kd-tree
      * input5.txt
        - failed on trial 2 of 1000
        - performs incorrect traversal of kd-tree during call to nearest()
        - do not compute the distance between the query point and the point in a node
          if the closest point discovered so far is closer than the distance between
          the query point and the rectangle corresponding to the node
    
      * input10.txt
        - failed on trial 1 of 1000
        - performs incorrect traversal of kd-tree during call to nearest()
        - do not compute the distance between the query point and the point in a node
          if the closest point discovered so far is closer than the distance between
          the query point and the rectangle corresponding to the node
    
    ==> FAILED
    
    Test 6b: insert non-degenerate points; check nearest() with random query points
             and check traversal of kd-tree
      * 5 random non-degenerate points in a 8-by-8 grid
        - failed on trial 1 of 1000
        - performs incorrect traversal of kd-tree during call to nearest()
        - do not compute the distance between the query point and the point in a node
          if the closest point discovered so far is closer than the distance between
          the query point and the rectangle corresponding to the node
    
      * 10 random non-degenerate points in a 16-by-16 grid
        - failed on trial 1 of 1000
        - performs incorrect traversal of kd-tree during call to nearest()
        - do not compute the distance between the query point and the point in a node
          if the closest point discovered so far is closer than the distance between
          the query point and the rectangle corresponding to the node
    
      * 20 random non-degenerate points in a 32-by-32 grid
        - failed on trial 1 of 1000
        - performs incorrect traversal of kd-tree during call to nearest()
        - do not compute the distance between the query point and the point in a node
          if the closest point discovered so far is closer than the distance between
          the query point and the rectangle corresponding to the node
    
      * 30 random non-degenerate points in a 64-by-64 grid
        - failed on trial 1 of 1000
        - performs incorrect traversal of kd-tree during call to nearest()
        - do not compute the distance between the query point and the point in a node
          if the closest point discovered so far is closer than the distance between
          the query point and the rectangle corresponding to the node
    
      * 50 random non-degenerate points in a 128-by-128 grid
        - failed on trial 1 of 1000
        - performs incorrect traversal of kd-tree during call to nearest()
        - do not compute the distance between the query point and the point in a node
          if the closest point discovered so far is closer than the distance between
          the query point and the rectangle corresponding to the node
    
      * 1000 random non-degenerate points in a 2048-by-2048 grid
        - failed on trial 1 of 1000
        - performs incorrect traversal of kd-tree during call to nearest()
        - do not compute the distance between the query point and the point in a node
          if the closest point discovered so far is closer than the distance between
          the query point and the rectangle corresponding to the node
    
    ==> FAILED
    
    
### 