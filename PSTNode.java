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
    private Point2D.Double p;
    private double medianX;

    public PSTNode(double x, double y, double medianX) {
	this(new Point2D.Double(x,y),medianX);
    }

    public PSTNode(Point2D.Double p, double medianX) {
	this.p = p;
	this.medianX = medianX;
    }

    public double getX() { return p.getX(); }
    public double getY() { return p.getY(); }
    public Point2D.Double getPoint() { return p; }
    public double getMedianX() { return medianX; }
    
    public static void main(String[] args) {
	PSTNode pstn = new PSTNode(2.0d,3.0d,4.0d);
    }
}