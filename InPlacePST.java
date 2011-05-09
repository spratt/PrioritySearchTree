/******************************************************************************
*                       Copyright (c) 2011 - 2012 by                          *
*                               Simon Pratt                                   *
*                         (All rights reserved)                               *
*******************************************************************************
*                                                                             *
* FILE:    InPlacePST.java                                                    *
*                                                                             *
* MODULE:  Priority Search Tree                                               *
*                                                                             *
* NOTES:   Data structure presented in "In-place Priority Search Tree         *
*          and its applications" by De, Maheshwari, Nandy, Smid in 2011.      *
*                                                                             *
******************************************************************************/

public class InPlacePST implements PrioritySearchTree {
    PSTPoint[] tree;

    public InPlacePST(PSTPoint[] points) {
	tree = points;
	inPlaceSort(0,tree.length);
	int h = (int)Math.floor(log2(tree.length));
	for(int i = 0; i < h-1; i++)
	    buildLevel(i);
    }

    private void buildLevel(int i) {
/******************************************************************************
* Find all the variables                                                      *
******************************************************************************/
	// number of nodes
	int n = tree.length;
	// height of tree
	int h = (int)Math.floor(log2(n));
	// number of nodes filled in the last level
	int nodesAtLastLevel = n - (int)(Math.pow(2,h) -1);
	// the first k nodes are roots of subtrees of size k1
	int k = (int)Math.floor(nodesAtLastLevel/Math.pow(2,h-i));
	// s is the median value
	PSTPoint s = tree[(int)Math.pow(2,i+1)];
	// the first k nodes are roots of subtrees of size k1
	int k1 = (int)Math.pow(2,h+1-i)-1;
	// the (k+1)-st node is the root of subtree of size k2
	int k2 = (int)(Math.pow(2,h-i)-1+nodesAtLastLevel-k*Math.pow(2,h-i));
	// the remaining nodes are roots of subtrees of size k3
	int k3 = (int)Math.pow(2,h-i)-1;

/******************************************************************************
* Order nodes at given level                                                  *
******************************************************************************/

	int indexOfMaxY;

	// 
	for(int j = 1; j <= k; j++) {
	    // Find point with maximum Y in range
	    indexOfMaxY = baseZeroIndex((int)Math.pow(2,i)+(j-1)*k1);
	    for(int index = indexOfMaxY;
		index <= baseZeroIndex((int)Math.pow(2,i)+j*k1-1);
		index++)
		if(tree[index].yGreaterThan(tree[indexOfMaxY]))
		    indexOfMaxY = index;
	    swap(indexOfMaxY,baseZeroIndex((int)Math.pow(2,i)+j-1));
	}

	//
	indexOfMaxY = baseZeroIndex((int)Math.pow(2,i)+k*k1);
	for(int index = indexOfMaxY;
	    index <= baseZeroIndex((int)Math.pow(2,i)+k*k1+k2-1);
	    index++)
		if(tree[index].yGreaterThan(tree[indexOfMaxY]))
		    indexOfMaxY = index;
	swap(indexOfMaxY,baseZeroIndex((int)Math.pow(2,i)+k));

	//
	int m = (int)Math.pow(2,i)+k*k1+k2;
	for(int j = 1; j <= (int)Math.pow(2,i)-k-1; j++) {
	    indexOfMaxY = baseZeroIndex(m+(j-1)*k3);
	    for(int index = indexOfMaxY;
		index <= baseZeroIndex(m+j*k3-1);
		index++)
		if(tree[index].yGreaterThan(tree[indexOfMaxY]))
		    indexOfMaxY = index;
	    swap(indexOfMaxY,baseZeroIndex((int)Math.pow(2,i)+k+j));
	}

	// Finally, sort all points past the current level
	inPlaceSort((int)Math.pow(2,i+1),n);
    }

    private void inPlaceSort(int beginIndex, int endIndex) {
	bubbleSort(beginIndex,endIndex);
    }

    // worst case and average: O(n^2)
    // could use heapsort or insertionsort for better performance
    private void bubbleSort(int beginIndex, int endIndex) {
	boolean swapped;
	do {
	    swapped = false;
	    for(int i = (beginIndex + 1); i < endIndex; i++) {
		if(tree[i-1].xGreaterThan(tree[i])) {
		    swap(i-1,i);
		    swapped = true;
		}
	    }
	} while(swapped);
    }
    private void swap(int i, int j) {
	PSTPoint temp;
	temp = tree[i];
	tree[i] = tree[j];
	tree[j] = temp;
    }
/******************************************************************************
* Utility                                                                     *
******************************************************************************/
    public void printArray() {
	printArray(tree);
    }
    public static void printArray(PSTPoint[] points) {
	for(int i = 0; i < points.length; i++) System.out.print(points[i] + " ");
	System.out.println();
    }
    // Gives the base-2 logarithm of a double
    private double log2(int x) {
	return Math.log(x) / Math.log(2);
    }

/******************************************************************************
*                                                                             *
* FUNCTION NAME: baseZeroIndex                                                *
*                                                                             *
* PURPOSE:       Converts the index of a base one array into the index        *
*                of a base zero array.                                        *
*                                                                             *
* PARAMETERS                                                                  *
*   Type/Name:   int/baseOneIndex                                             *
*   Description: The index of an array with base one.                         *
*                                                                             *
* RETURN:        The integer equivalent index in an array with base zero.     *
*                                                                             *
******************************************************************************/
    private static int baseZeroIndex(int baseOneIndex) {
	return baseOneIndex - 1;
    }

/******************************************************************************
* Testing                                                                     *
******************************************************************************/
    public static void main(String[] args) {
	System.out.println("Creating points...");
	int MAX_Y = 2;
	PSTPoint[] testPoints = new PSTPoint[2*MAX_Y];
	int count = 0;
	for(int i = -MAX_Y; i < MAX_Y ; i++) {
	    testPoints[count++] = new PSTPoint(i,i+MAX_Y);
	}
	System.out.println("Building PST with " + (2*MAX_Y) + " nodes...");
	System.out.print("Points: "); printArray(testPoints);
	InPlacePST ippst = new InPlacePST(testPoints);
	System.out.print("PST: "); ippst.printArray();
    }

/******************************************************************************
* Stubs (for now)                                                             *
******************************************************************************/
    
    public java.util.ArrayList<PSTPoint> findAllPointsWithin(double minX,
							     double maxX,
							     double maxY)
	throws EmptyTreeException {
	return null;
    }
    public double minYinRange(double minX, double maxX, double maxY)
	throws NoPointsInRangeException {
	throw new NoPointsInRangeException();
    }
    public double minXinRange(double minX, double maxX, double maxY)
	throws NoPointsInRangeException {
	throw new NoPointsInRangeException();
    }
    public double maxXinRange(double minX, double maxX, double maxY)
	throws NoPointsInRangeException {
	throw new NoPointsInRangeException();
    }
    public double maxYinRange(double minX, double maxX, double maxY)
	throws NoPointsInRangeException {
	throw new NoPointsInRangeException();
    }
}
