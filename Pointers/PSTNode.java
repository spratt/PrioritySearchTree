/******************************************************************************
*                       Copyright (c) 2011 - 2012 by                          *
*                               Simon Pratt                                   *
*                         (All rights reserved)                               *
*******************************************************************************
*                                                                             *
* FILE:    PSTNode.java                                                       *
*                                                                             *
* MODULE:  Priority Search Tree                                               *
*                                                                             *
* NOTES:   See README for more information.                                   *
*                                                                             *
*          See LICENSE for license information.                               *
*                                                                             *
******************************************************************************/

public class PSTNode {
    private PSTPoint p;
    private PSTNode leftChild, rightChild;

    public PSTNode(double x, double y) {
	this(new PSTPoint(x,y));
    }

    public PSTNode(PSTPoint p) {
	this.p = p;
    }

    public double getX() { return p.getX(); }
    public double getY() { return p.getY(); }
    public PSTPoint getPoint() { return p; }
    public PSTNode getLeftChild() { return leftChild; }
    public PSTNode getRightChild() { return rightChild; }
    public void setLeftChild(PSTNode p) {
	this.leftChild = p;
    }
    public void setRightChild(PSTNode p) {
	this.rightChild = p;
    }
}