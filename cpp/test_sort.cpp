///////////////////////////////////////////////////////////////////////////////
//                       Copyright (c) 2011 - 2012 by                        //
//                                Simon Pratt                                //
//                           (All rights reserved)                           //
///////////////////////////////////////////////////////////////////////////////
//                                                                           //
// FILE:    test_sort.cpp                                                    //
//                                                                           //
// MODULE:  Sort                                                             //
//                                                                           //
// NOTES:   None.                                                            //
//                                                                           //
///////////////////////////////////////////////////////////////////////////////
#include <iostream>
#include "PSTPoint.h"
#include "array_utilities.h"
#include "sort/insertion_sort.h"
#include "sort/heap_sort.h"

using namespace std;

int main(int argv, char** argc) {
  int n = 1000;
  if(argv >= 2) {
    n = atoi(argc[1]);
  }
  cout << "Creating " << n << " points..." << endl;
  PSTPoint *points = new PSTPoint[n];
  for(int i = 0; i < n; i++) {
    points[i].setX(n-i);
    points[i].setY(i);
  }
  // Heap Sort
  cout << "Heap Sort" << endl;
  if(n <= 10) {
    cout << "Before sorting: ";
    print(points,n);
  }
  heap_sort(points,n);
  if(n <= 10) {
    cout << "After sorting: ";
    print(points,n);
  }
  // Insertion Sort
  for(int i = 0; i <= n/2; i++) { // unsort the points
    swap(points,i,n-i-1);
  }
  cout << "Insertion Sort" << endl;
  if(n <= 10) {
    cout << "Before sorting: ";
    print(points,n);
  }
  insertion_sort(points,n);
  if(n <= 10) {
    cout << "After sorting: ";
    print(points,n);
  }
  return 0;
}
