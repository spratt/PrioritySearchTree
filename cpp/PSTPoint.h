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
#include <ostream>
using std::basic_ostream;

class PSTPoint {
  double x, y;
public:
  PSTPoint(double x = 0, double y = 0)
    : x(x), y(y)
  { }
  double getX() const { return x; }
  double getY() const { return y; }
  
  /////////////////////////////////////////////////////////////////////////////
  // Implemented in PSTPoint.cpp                                             //
  /////////////////////////////////////////////////////////////////////////////
  static void printArray(PSTPoint* points, int nPoints);
  PSTPoint& operator=(const PSTPoint& p);

  /////////////////////////////////////////////////////////////////////////////
  // Boilerplate needed to send PSTPoint to cout                             //
  /////////////////////////////////////////////////////////////////////////////
  template <typename CharT, typename Traits>
  friend basic_ostream<CharT, Traits>& operator<<(basic_ostream<CharT, Traits>& out,
						  const PSTPoint& p);
};

#endif PSTPOINT_H
