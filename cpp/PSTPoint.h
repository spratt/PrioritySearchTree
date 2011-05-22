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
  void setX(double new_x);
  void setY(double new_y);
  PSTPoint& operator=(const PSTPoint& p);
  bool operator>(const PSTPoint& p); // compares x coordinate
  bool operator<(const PSTPoint& p); // compares x coordinate
  bool yGreaterThan(const PSTPoint& p);
  bool yLessThan(const PSTPoint& p);
};

/////////////////////////////////////////////////////////////////////////////
// Boilerplate needed to send PSTPoint to cout                             //
/////////////////////////////////////////////////////////////////////////////
template <typename CharT, typename Traits>
basic_ostream<CharT, Traits>& operator<<(basic_ostream<CharT, Traits>& out,
					 const PSTPoint& p) {
  return out<< "(" << p.getX() << "," << p.getY() << ")";
}

#endif PSTPOINT_H
