///////////////////////////////////////////////////////////////////////////////
//                       Copyright (c) 2011 - 2012 by                        //
//                                Simon Pratt                                //
//                           (All rights reserved)                           //
///////////////////////////////////////////////////////////////////////////////
// 
// FILE:    test_sort.cpp
// 
// MODULE:  Sort
// 
// NOTES:   None.
// 
///////////////////////////////////////////////////////////////////////////////
#include <iostream>
#include "PSTPoint.h"
#include "sort/insertion_sort.h"

using namespace std;

int main(int argv, char** argc) {
  int n = 1000;
  if(argv >= 2) {
    n = atoi(argc[1]);
  }
  PSTPoint *points = new PSTPoint[n];
  for(int i = 0; i < n; i++) {
    points[i].setX(n-i);
    points[i].setY(i);
  }
  cout << "Before sorting: ";
  PSTPoint::printArray(points,n);
  insertion_sort(points,0,n-1);
  cout << "After sorting: ";
  PSTPoint::printArray(points,n);
  return 0;
}
