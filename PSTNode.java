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
    private PSTPoint p;
    private double medianX;

    public PSTNode(double x, double y, double medianX) {
	this(new PSTPoint(x,y),medianX);
    }

    public PSTNode(PSTPoint p, double medianX) {
	this.p = p;
	this.medianX = medianX;
    }

    public double getX() { return p.getX(); }
    public double getY() { return p.getY(); }
    public PSTPoint getPoint() { return p; }
    public double getMedianX() { return medianX; }
    
    public static void main(String[] args) {
	PSTNode pstn = new PSTNode(2.0d,3.0d,4.0d);
    }
}