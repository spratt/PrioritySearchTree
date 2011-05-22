///////////////////////////////////////////////////////////////////////////////
//                       Copyright (c) 2011 - 2012 by                        //
//                                Simon Pratt                                //
//                           (All rights reserved)                           //
///////////////////////////////////////////////////////////////////////////////
//                                                                           //
// FILE:    array_utilities.cpp                                              //
//                                                                           //
// MODULE:  Priority Search Tree                                             //
//                                                                           //
// NOTES:   Provides useful functions for working with arrays.               //
//                                                                           //
///////////////////////////////////////////////////////////////////////////////
#include <iostream>
#include "PSTPoint.h"
#include "array_utilities.h"

using namespace std;

namespace PSTArray {
  void print(PSTPoint* points, int nPoints) {
    cout << points[0];
    for(int i = 1; i < nPoints; i++)
      cout << " " << points[i];
    cout << endl;
  }
  void swap(PSTPoint* points, int a, int b) {
    PSTPoint temp;
    temp = points[a];
    points[a] = points[b];
    points[b] = temp;
  }
  // copies from a to b
  void copy(PSTPoint* a, PSTPoint* b, int n) {
    while(--n >= 0) b[n] = a[n];
  }
}
