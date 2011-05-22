///////////////////////////////////////////////////////////////////////////////
//                       Copyright (c) 2011 - 2012 by                        //
//                                Simon Pratt                                //
//                           (All rights reserved)                           //
///////////////////////////////////////////////////////////////////////////////
//                                                                           //
// FILE:    PSTPoint.cpp                                                     //
//                                                                           //
// MODULE:  Priority Search Tree                                             //
//                                                                           //
// NOTES:   None.                                                            //
//                                                                           //
///////////////////////////////////////////////////////////////////////////////
#include "PSTPoint.h"

void PSTPoint::setX(double new_x) {
  x = new_x;
}

void PSTPoint::setY(double new_y) {
  y = new_y;
}

bool PSTPoint::yGreaterThan(const PSTPoint& p) {
  return y > p.getY();
}

bool PSTPoint::yLessThan(const PSTPoint& p) {
  return y < p.getY();
}

bool PSTPoint::operator<(const PSTPoint& p) {
  return x < p.getX();
}

bool PSTPoint::operator>(const PSTPoint& p) {
  return x > p.getX();
}

PSTPoint& PSTPoint::operator=(const PSTPoint& p) {
  x = p.getX();
  y = p.getY();
  return *this;
}
