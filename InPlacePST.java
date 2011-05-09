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
    }
    
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