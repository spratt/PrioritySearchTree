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
    Point2D.Double[] heap;

    // Assumes all points are valid (e.g. not null)
    public PrioritySearchTree(Point2D.Double[] points) {
	if(points == null) return;
	this.heap = new Point2D.Double[heapSize(points.length)];
	System.out.println("Heap size: " + this.heap.length);
	buildTree(0,points);
    }

    public void buildTree(int rootIndex, Point2D.Double[] points) {
	// Find point with highest Y value
	if(points == null || points.length < 1) return;
	System.out.println(points.length);
	heap[rootIndex] = points[0];
	double sumX = 0.0d;
	for(Point2D.Double p : points) {
	    if(p.getY() < heap[rootIndex].getY())
		heap[rootIndex] = p;
	    sumX += p.getX();
	}
	sumX -= heap[rootIndex].getX();
	// Find median X value
	double medianX = sumX/(points.length-1);
	// Make upper and lower point array
	List<Point2D.Double> upperPoints = new ArrayList<Point2D.Double>();
	List<Point2D.Double> lowerPoints = new ArrayList<Point2D.Double>();
	for(Point2D.Double p : points) {
	    if(p == heap[rootIndex]) continue;
	    else if(p.getX() < medianX) lowerPoints.add(p);
	    else upperPoints.add(p);
	}
	if(upperPoints.size() != lowerPoints.size()) {
	    System.out.println("Not Balanced");
	    for(Point2D.Double p : points) {
		if(p == heap[rootIndex]) continue;
		System.out.print(p + ", ");
	    }
	    System.out.println();
	    System.out.println("medianX: " + medianX);
	}
	    
	// Left child
	buildTree((2*rootIndex)+1,lowerPoints.toArray(new Point2D.Double[0]));
	// Right child
	buildTree((2*rootIndex)+2,upperPoints.toArray(new Point2D.Double[0]));
    }
    
    public static int heapSize(int n) {
	// Determine the height of the tree
	double height = Math.ceil(Math.log(n+1)/Math.log(2));
	// Determine the max number of heap nodes in a tree of that height
	return doubleToInt(Math.pow(2, height)-1);
    }
    
    private static int doubleToInt(double d) {
	return (new Double(d)).intValue();
    }
	
    public static void main(String[] args) {
	new PrioritySearchTree(null);
	Point2D.Double[] testPoints = new Point2D.Double[7];
	testPoints[4] = new Point2D.Double(3.0d,1.0d);
	testPoints[0] = new Point2D.Double(2.0d,5.0d);
	testPoints[2] = new Point2D.Double(1.0d,3.0d);
	testPoints[5] = new Point2D.Double(-3.0d,0.0d);
	testPoints[1] = new Point2D.Double(-2.0d,4.0d);
	testPoints[3] = new Point2D.Double(-1.0d,2.0d);
	testPoints[6] = new Point2D.Double(0.0d,-1.0d);
	//testPoints[7] = new Point2D.Double(0.0d,-2.0d);
	//testPoints[8] = new Point2D.Double(0.0d,-3.0d);
	new PrioritySearchTree(testPoints);
    }
}