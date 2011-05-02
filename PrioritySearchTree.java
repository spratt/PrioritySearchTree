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

    // Assumes all points are valid (e.g. not null)
    public PrioritySearchTree(Point2D.Double[] points) {
	if(points == null) return;
	this.heap = new PSTNode[heapSize(points.length)];
	buildTree(0,points);
    }

    private void buildTree(int rootIndex, Point2D.Double[] points) {
	// Find point with lowest Y value
	if(points == null || points.length < 1) return;
	Point2D.Double rootPoint = points[0];
	double sumX = 0.0d;
	for(Point2D.Double p : points) {
	    if(p.getY() < rootPoint.getY())
		rootPoint = p;
	    sumX += p.getX();
	}
	sumX -= rootPoint.getX();
	// Find median X value
	//  - uses average X value of non-root points
	double medianX = sumX/(points.length-1);
	// Set the root node
	heap[rootIndex] = new PSTNode(rootPoint,medianX);
	// Make upper and lower point array
	List<Point2D.Double> upperPoints = new ArrayList<Point2D.Double>();
	List<Point2D.Double> lowerPoints = new ArrayList<Point2D.Double>();
	for(Point2D.Double p : points) {
	    if(p == rootPoint) continue;
	    else if(p.getX() < medianX) lowerPoints.add(p);
	    else upperPoints.add(p);
	} 
	buildTree(indexOfLeftChild(rootIndex),lowerPoints.toArray(new Point2D.Double[0]));
	buildTree(indexOfRightChild(rootIndex),upperPoints.toArray(new Point2D.Double[0]));
    }

    ArrayList<Point2D.Double> findAllPointsWithin(double x1, double y1, double x2, double y2) {
	return findAllPointsWithin(x1,y1,x2,y2,new ArrayList<Point2D.Double>(),0);
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
    ArrayList<Point2D.Double> findAllPointsWithin(double x1, double y1,
						  double x2, double y2,
						  ArrayList<Point2D.Double> list,
						  int rootIndex) {
	PSTNode node = heap[rootIndex];
	if(node == null) return list;
	double nodeX = node.getX();
	double nodeY = node.getY();
	double nodeR = node.getMedianX();
	if(nodeY <= y2) {
	    if(nodeX >= x1 && nodeY >= y1 && nodeX <= x2) { // nodeY MUST <= y2
		list.add(node.getPoint());
	    }
	    if(x1 < nodeR)
		findAllPointsWithin(x1,y1,x2,y2,list,indexOfLeftChild(rootIndex));
	    if(x2 >= nodeR)
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

/******************************************************************************
* Testing                                                                     *
******************************************************************************/  
    public static void main(String[] args) {
	new PrioritySearchTree(null);
	Point2D.Double[] testPoints = new Point2D.Double[9];
	testPoints[0] = new Point2D.Double(3.0d,1.0d);
	testPoints[1] = new Point2D.Double(2.0d,5.0d);
	testPoints[2] = new Point2D.Double(1.0d,3.0d);
	testPoints[3] = new Point2D.Double(-3.0d,0.0d);
	testPoints[4] = new Point2D.Double(-2.0d,4.0d);
	testPoints[5] = new Point2D.Double(-1.0d,2.0d);
	testPoints[6] = new Point2D.Double(0.0d,-1.0d);
	testPoints[7] = new Point2D.Double(0.0d,-2.0d);
	testPoints[8] = new Point2D.Double(0.0d,-3.0d);
	PrioritySearchTree pst = new PrioritySearchTree(testPoints);
	for(Point2D.Double p : pst.findAllPointsWithin(-2.0d,-2.0d,2.0d,2.0d))
	    System.out.println(p);
    }
}