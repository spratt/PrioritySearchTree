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
#include <iostream>
#include <string>
#include "PSTPoint.h"

using namespace std;

void PSTPoint::printArray(PSTPoint* points, int nPoints) {
  cout << points[0];
  for(int i = 1; i < nPoints; i++)
    cout << " " << points[i];
  cout << endl;
}

PSTPoint& PSTPoint::operator=(const PSTPoint& p) {
  x = p.getX();
  y = p.getY();
  return *this;
}

/////////////////////////////////////////////////////////////////////////////
// Allows sending to cout                                                  //
/////////////////////////////////////////////////////////////////////////////
template <typename CharT, typename Traits>
basic_ostream<CharT, Traits>& operator<<(basic_ostream<CharT, Traits>& out,
					 const PSTPoint& p) {
  return out<< "(" << p.getX() << "," << p.getY() << ")";
}
