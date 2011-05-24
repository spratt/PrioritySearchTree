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
#include <cmath>
#include "time.h"
#include "PSTPoint.h"
#include "array_utilities.h"
#include "sort/stable_01_sort.h"

using namespace std;

int main(int argv, char** argc) {
  cout << "log2(1):      " << log2(1) << endl;
  cout << "spo2gtl2o(1): " << smallestPowerOf2GreaterThanLog2Of(1) << endl;
  cout << "log2(2):      " << log2(2) << endl;
  cout << "spo2gtl2o(2): " << smallestPowerOf2GreaterThanLog2Of(2) << endl;
  cout << "log2(3):      " << log2(3) << endl;
  cout << "spo2gtl2o(3): " << smallestPowerOf2GreaterThanLog2Of(3) << endl;
  cout << "log2(4):      " << log2(4) << endl;
  cout << "spo2gtl2o(4): " << smallestPowerOf2GreaterThanLog2Of(4) << endl;
  cout << "log2(5):      " << log2(5) << endl;
  cout << "spo2gtl2o(5): " << smallestPowerOf2GreaterThanLog2Of(5) << endl;
  cout << "log2(9):      " << log2(9) << endl;
  cout << "spo2gtl2o(9): " << smallestPowerOf2GreaterThanLog2Of(9) << endl;
  cout << "log2(1000):      " << log2(1000) << endl;
  cout << "spo2gtl2o(1000): " << smallestPowerOf2GreaterThanLog2Of(1000) << endl;
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
