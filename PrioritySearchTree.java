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
    private PSTNode root;

    // Assumes all points are valid (e.g. not null)
    public PrioritySearchTree(Point2D.Double[] points) {
	// Find point with highest Y value
	if(points == null || points.length < 1) return;
	Point2D.Double rootPoint = points[0];
	for(Point2D.Double p : points) {
	    if(p.getY() > rootPoint.getY())
		rootPoint = p;
	}
	// Find median X value
	double medianX = 0.0d;
	// Make upper and lower point array
	List<Point2D.Double> upperPoints = new ArrayList<Point2D.Double>();
	List<Point2D.Double> lowerPoints = new ArrayList<Point2D.Double>();
	for(Point2D.Double p : points) {
	    if(p == rootPoint) continue;
	    else if(p.getX() < medianX) lowerPoints.add(p);
	    else upperPoints.add(p);
	}
	// Make tree
	this.root = new PSTNode(rootPoint,medianX);
	this.root.setLeftChild((new PrioritySearchTree(lowerPoints.toArray(new Point2D.Double[0]))).getRoot());
	this.root.setRightChild((new PrioritySearchTree(upperPoints.toArray(new Point2D.Double[0]))).getRoot());
    }

    public PSTNode getRoot() { return root; }

    public static void main(String[] args) {
	new PrioritySearchTree(null);
	Point2D.Double[] testPoints = new Point2D.Double[5];
	testPoints[0] = new Point2D.Double(2.0d,5.0d);
	testPoints[1] = new Point2D.Double(-2.0d,4.0d);
	testPoints[2] = new Point2D.Double(1.0d,3.0d);
	testPoints[3] = new Point2D.Double(-1.0d,2.0d);
	testPoints[4] = new Point2D.Double(0.0d,1.0d);
	new PrioritySearchTree(testPoints);
    }
}