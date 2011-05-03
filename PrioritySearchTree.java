/******************************************************************************
*                       Copyright (c) 2009 - 2010 by                          *
*                               Simon Pratt                                   *
*                         (All rights reserved)                               *
*******************************************************************************
*                                                                             *
* FILE:    PrioritySearchTree.java                                            *
*                                                                             *
* MODULE:  Priority Search Tree                                               *
*                                                                             *
* NOTES:   A priority search tree is a tree data structure which stores       *
*          a set of coordinates in sorted order.  The root element of         *
*          the tree is the point with highest Y value.  The rest of           *
*          the points are divided into two sets, one set having               *
*          smaller x values than the median, the other having higher.         *
*                                                                             *
*          See README for more information.                                   *
*                                                                             *
*          See LICENSE for license information.                               *
*                                                                             *
******************************************************************************/

import java.awt.geom.*;
import java.util.*;

public class PrioritySearchTree {
    PSTNode[] heap;

/******************************************************************************
* The worst case for space is when there are 2^m nodes, for some m.           *
* In which case, O(2^(logn) - 1) extra space is allocated.                    *
******************************************************************************/
    public PrioritySearchTree(ArrayList<PSTPoint> points) {
	if(points == null) return;
	// Sort the points by y-coordinate in increasing ordering
	Collections.sort(points,new Comparator<PSTPoint>() {
		public int compare(PSTPoint o1, PSTPoint o2) {
		    if(o1.getY() < o2.getY()) return -1;
		    else if(o1.getY() > o2.getY()) return 1;
		    else return 0;
		}
	    });
	this.heap = new PSTNode[heapSize(points.size())];
	buildTree(0,points);
    }

/******************************************************************************
* Given a root index and a list of valid points P ordered by                  *
* y-coordinate in increasing order, determines a median which bisects         *
* the remaining points, then builds:                                          *
*                                                                             *
*   root: point with lowest y-value                                           *
*   left child:  {p ∈ (P - root) | p.x <= medianX}                            *
*   right child: {p ∈ (P - root) | p.x >  medianX}                            *
*                                                                             *
* Note: points are also assumed to have distinct coordinates, i.e. no         *
*       two points have the same x coordinate and no two points have          *
*       the same y coordinate.                                                *
*                                                                             *
*       While this may seem unrealistic, we can convert any indistinct        *
*       coordinates by replacing all real coordinates with distinct           *
*       coordinates from the composite-number space without any loss          *
*       of generality.  See: Computational Geometry: Applications and         *
*       Algorithms, de Berg et al.  Section 5.5.                              *
*                                                                             *
******************************************************************************/
    private void buildTree(int rootIndex, ArrayList<PSTPoint> points) {
	if(points == null || points.size() < 1) return;
	// Since points are ordered by y, smallest is first
	PSTPoint rootPoint = points.get(0);
	// Find median X value
	//  - uses average X value of non-root points
	double sumX = 0.0d;
	for(PSTPoint p : points) { 
	    sumX += p.getX();
	}
	sumX -= rootPoint.getX();
	double medianX = sumX/(points.size()-1);
	// Set the root node
	heap[rootIndex] = new PSTNode(rootPoint,medianX);
	// Bisect the non-root points into two arrays above and below the median
	ArrayList<PSTPoint> upperPoints = new ArrayList<PSTPoint>();
	ArrayList<PSTPoint> lowerPoints = new ArrayList<PSTPoint>();
	for(PSTPoint p : points) {
	    if(p == rootPoint) continue;
	    // note: if p.x is equal to median, it will be added to left child
	    else if(p.getX() <= medianX) lowerPoints.add(p);
	    else upperPoints.add(p);
	} 
	buildTree(indexOfLeftChild(rootIndex),lowerPoints);
	buildTree(indexOfRightChild(rootIndex),upperPoints);
    }
    
/******************************************************************************
*                                                                             *
* Find all points within the box given by (x1,y1) and (x2,y2)                 *
*                                                                             *
*          +--------+ (x2,y2)                                                 *
*          |        |                                                         *
*          |        |                                                         *
*          |        |                                                         *
*  (x1,y1) +--------+                                                         *
*                                                                             *
* Assumes x2 > x1 and y2 > y1.  Choose x1,y1,x2,y2 appropriately.             *
*                                                                             *
******************************************************************************/
    ArrayList<PSTPoint> findAllPointsWithin(double x1, double y1,
						  double x2, double y2) {
	return findAllPointsWithin(x1,y1,x2,y2,new ArrayList<PSTPoint>(),0);
    }

    ArrayList<PSTPoint> findAllPointsWithin(double x1, double y1,
						  double x2, double y2,
						  ArrayList<PSTPoint> list,
						  int rootIndex) {
	PSTNode node = heap[rootIndex];
	if(node == null) return list;
	double nodeX = node.getX();
	double nodeY = node.getY();
	double nodeR = node.getMedianX();
	if(nodeY <= y2) {
	    // Since we already know nodeY <= y2, we don't need to check again
	    if(nodeX >= x1 && nodeY >= y1 && nodeX <= x2) { 
		list.add(node.getPoint());
	    }
	    // x1 <= points in left tree <= nodeR
	    if(x1 <= nodeR)
		findAllPointsWithin(x1,y1,x2,y2,list,indexOfLeftChild(rootIndex));
	    // x2 >= points in right tree > nodeR
	    if(x2 > nodeR) 
		findAllPointsWithin(x1,y1,x2,y2,list,indexOfRightChild(rootIndex));
	}
	return list;
    }

/******************************************************************************
* Utility Functions                                                           *
******************************************************************************/
    private static int heapSize(int n) {
	// Determine the height of the tree
	double height = Math.ceil(Math.log(n+1)/Math.log(2));
	// Determine the max number of heap nodes in a tree of that height
	return doubleToInt(Math.pow(2, height)-1);
    }

    private static int indexOfLeftChild(int rootIndex) {
	return (2*rootIndex)+1;
    }

    private static int indexOfRightChild(int rootIndex) {
	return (2*rootIndex)+2;
    }
    
    private static int doubleToInt(double d) {
	return (new Double(d)).intValue();
    }

    private static void printList(ArrayList<PSTPoint> points) {
	for(PSTPoint p : points) System.out.print(p + " ");
	System.out.println();
    }

/******************************************************************************
* Testing                                                                     *
******************************************************************************/  
    public static void main(String[] args) {
	// Test construction
	new PrioritySearchTree(null);
	ArrayList<PSTPoint> testPoints = new ArrayList<PSTPoint>();
	testPoints.add(new PSTPoint(1.0d,1.0d));
	testPoints.add(new PSTPoint(2.0d,5.0d));
	testPoints.add(new PSTPoint(3.0d,3.0d));
	testPoints.add(new PSTPoint(-3.0d,0.0d));
	testPoints.add(new PSTPoint(-2.0d,4.0d));
	testPoints.add(new PSTPoint(-1.0d,2.0d));
	testPoints.add(new PSTPoint(4.0d,-1.0d));
	testPoints.add(new PSTPoint(5.0d,-2.0d));
	testPoints.add(new PSTPoint(6.0d,-3.0d));
	testPoints.add(new PSTPoint(7.0d,22.0d));
	testPoints.add(new PSTPoint(8.0d,42.0d));
	testPoints.add(new PSTPoint(0.0d,-30.0d));

	// Test query
	System.out.print("All points within bounds: ");
	PrioritySearchTree pst = new PrioritySearchTree(testPoints);
	printList(pst.findAllPointsWithin(-3.0d,-3.0d,3.0d,3.0d));
    }
}