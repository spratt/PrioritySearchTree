/******************************************************************************
*                       Copyright (c) 2009 - 2010 by                          *
*                               Simon Pratt                                   *
*                         (All rights reserved)                               *
*******************************************************************************
*                                                                             *
* FILE:    PSTPoint.java                                                      *
*                                                                             *
* MODULE:  Priority Search Tree                                               *
*                                                                             *
* NOTES:   See README for more information.                                   *
*                                                                             *
*          See LICENSE for license information.                               *
*                                                                             *
******************************************************************************/

import java.awt.geom.*;

public class PSTPoint {
    private Point2D.Double p;

    public PSTPoint(double x, double y) {
	this.p = new Point2D.Double(x,y);
    }

    public double getX() { return p.getX(); }
    public double getY() { return p.getY(); }
    public String toString() { return "(" + p.getX() + "," + p.getY() + ")"; }
}