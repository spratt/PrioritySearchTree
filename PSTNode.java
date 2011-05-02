/******************************************************************************
*                       Copyright (c) 2009 - 2010 by                          *
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

import java.awt.geom.*;

public class PSTNode {
    private Point2D p;
    private double medianX;
    private PSTNode leftChild, rightChild;

    public PSTNode(double x, double y, double medianX) {
	this(new Point2D.Double(x,y),medianX,null,null);
    }

    public PSTNode(Point2D p, double medianX, PSTNode leftChild, PSTNode rightChild) {
	this.p = p;
	this.medianX = medianX;
	this.leftChild = leftChild;
	this.rightChild = rightChild;
    }

    public PSTNode(Point2D p, double medianX) {
	this(p,medianX,null,null);
    }

    public double getX() { return p.getX(); }
    public double getY() { return p.getY(); }
    public double getMedianX() { return medianX; }

    public void setLeftChild(PSTNode child) {
	this.leftChild = child;
    }

    public void setRightChild(PSTNode child) {
	this.rightChild = child;
    }
    
    public static void main(String[] args) {
	PSTNode pstn = new PSTNode(2.0d,3.0d,4.0d);
    }
}