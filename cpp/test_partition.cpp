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
#include "time.h"
#include "PSTPoint.h"
#include "array_utilities.h"
#include "sort/stable_01_sort.h"

using namespace std;

int main(int argv, char** argc) {
  time_t before, after;
  /////////////////////////////////////////////////////////////////////////////
  // Setup                                                                   //
  /////////////////////////////////////////////////////////////////////////////
  int n = 1000;
  if(argv >= 2) {
    n = atoi(argc[1]);
  }
  cout << "Creating " << n << " points..." << flush;
  before = time(0);
  PSTPoint* points = new PSTPoint[n];
  PSTPoint s;
  for(int i = 0; i < n; i++) {
    PSTPoint p(i,n-i);
    if(i == n/2)
      s = p;
    points[i] = p;
  }
  after = time(0);
  cout << "took: " << (after - before) << endl;
  /////////////////////////////////////////////////////////////////////////////
  // Unsort Points                                                           //
  /////////////////////////////////////////////////////////////////////////////
  cout << "Unsorting points..." << flush;
  before = time(0);
  for(int i = 0; i <= n/2; i++) { // unsort the points
    PSTArray::swap(points,i,n-i-1);
  }
  after = time(0);
  cout << "took: " << (after - before) << endl;
  /////////////////////////////////////////////////////////////////////////////
  // Print points                                                            //
  /////////////////////////////////////////////////////////////////////////////
  if(n <= 10) {
    cout << "Before sorting: ";
    PSTArray::print(points,n);
  }
  cout << "Using point: " << s << endl;
  /////////////////////////////////////////////////////////////////////////////
  // Simple Partition                                                        //
  /////////////////////////////////////////////////////////////////////////////
  cout << "Simple partition..." << flush;
  before = time(0);
  simple_partition(points,0,n-1,s);
  after = time(0);
  cout << "took: " << (after - before) << endl;
  if(n <= 10) {
    cout << "After sorting: ";
    PSTArray::print(points,n);
  }
  /////////////////////////////////////////////////////////////////////////////
  // Unsort Points                                                           //
  /////////////////////////////////////////////////////////////////////////////
  cout << "Unsorting points..." << flush;
  before = time(0);
  for(int i = 0; i <= n/2; i++) { // unsort the points
    PSTArray::swap(points,i,n-i-1);
  }
  after = time(0);
  cout << "took: " << (after - before) << endl;
  if(n <= 10) {
    cout << "Before sorting: ";
    PSTArray::print(points,n);
  }
  cout << "Using point: " << s << endl;
  /////////////////////////////////////////////////////////////////////////////
  // Algorithm A                                                             //
  /////////////////////////////////////////////////////////////////////////////
  cout << "Algorithm A..." << flush;
  before = time(0);
  algorithm_A(points,0,n-1,s);
  after = time(0);
  cout << "took: " << (after - before) << endl;
  if(n <= 10) {
    cout << "After sorting: ";
    PSTArray::print(points,n);
  }
  return 0;
}
