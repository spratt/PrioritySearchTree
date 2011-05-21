//-*- mode: c++ -*-////////////////////////////////////////////////////////////
//                       Copyright (c) 2011 - 2012 by                        //
//                                Simon Pratt                                //
//                           (All rights reserved)                           //
///////////////////////////////////////////////////////////////////////////////
//                                                                           //
// FILE:    PSTPoint.h                                                       //
//                                                                           //
// MODULE:  Priority Search Tree                                             //
//                                                                           //
// PURPOSE: Defines the PST Point class.                                     //
//                                                                           //
// NOTES:   None.                                                            //
//                                                                           //
///////////////////////////////////////////////////////////////////////////////
//                             Public Methods:                               //
//                                                                           //
//   Type/Name:   double/getX                                                //
//   Description: Returns the x value of the point.                          //
//                                                                           //
//   Type/Name:   double/getY                                                //
//   Description: Returns the y value of the point.                          //
//                                                                           //
///////////////////////////////////////////////////////////////////////////////
#ifndef PSTPOINT_H
#define PSTPOINT_H

class PSTPoint {
  double x, y;
  PSTPoint() {}
public:
  PSTPoint(double x, double y)
    : x(x), y(y)
  { }

  double getX() const { return x; }
  double getY() const { return y; }
}

#endif PSTPOINT_H
