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

public class PrioritySearchTree {
    PSTNode root;

    public PrioritySearchTree(Point2D.Double[] points) {
	// Find point with highest Y value
	if(points == null || points.length < 1) return;
	Point2D.Double root = points[0];
	for(Point2D.Double p : points) {
	    if(p.getY() > root.getY())
		root = p;
	}
	// Find median X value
	// Make lower point array
	// Make upper point array
	// Make tree
    }

    public static void main(String[] args) {
	new PrioritySearchTree(null);
    }
}