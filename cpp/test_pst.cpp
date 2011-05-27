///////////////////////////////////////////////////////////////////////////////
//                       Copyright (c) 2011 - 2012 by                        //
//                                Simon Pratt                                //
//                           (All rights reserved)                           //
///////////////////////////////////////////////////////////////////////////////
//                                                                           //
// FILE:    test_pst.cpp                                                     //
//                                                                           //
// MODULE:  Priority Search Tree                                             //
//                                                                           //
// NOTES:   None.                                                            //
//                                                                           //
///////////////////////////////////////////////////////////////////////////////
#include <iostream>
#include <time.h>
#include "PSTPoint.h"
#include "InPlacePST.h"
#include "array_utilities.h"

using namespace std;

// Dirty hack, but hey: it's c++!
PSTPoint* vectorPointerToArray(vector<PSTPoint>* v) {
  return &(*v)[0];
}

int main(int argv, char** argc) {
  time_t before, after;
  /////////////////////////////////////////////////////////////////////////////
  // Setup                                                                   //
  /////////////////////////////////////////////////////////////////////////////
  int n = 1000000;
  if(argv >= 2) {
    n = atoi(argc[1]);
  }
  cout << "Creating " << n << " points..." << flush;
  before = time(0);
  PSTPoint *points = new PSTPoint[n]; // allocate on the heap
  for(int i = 0; i < n; i++) {
    PSTPoint p(i,n-i); // allocate on the stack
    points[i] = p;
  }
  after = time(0);
  cout << "took: " << (after - before) << endl;
  if(n <= 10) {
    cout << "Points: ";
    PSTArray::print(points,n);
  }
  /////////////////////////////////////////////////////////////////////////////
  // Build tree                                                              //
  /////////////////////////////////////////////////////////////////////////////
  cout << "Building tree..." << flush;
  before = time(0);
  InPlacePST ippst(points,n);
  after = time(0);
  cout << "took: " << (after - before) << endl;
  if(n <= 10) {
    cout << "Tree: ";
    ippst.printArray();
  }
  /////////////////////////////////////////////////////////////////////////////
  // leftMostNE                                                              //
  /////////////////////////////////////////////////////////////////////////////
  cout << "Querying leftMostNE..." << flush;
  before = time(0);
  PSTPoint result = ippst.leftMostNE(-10,-10);
  after = time(0);
  cout << "took: " << (after - before) << endl;
  cout << "Found: " << result << endl;
  /////////////////////////////////////////////////////////////////////////////
  // highestNE                                                               //
  /////////////////////////////////////////////////////////////////////////////
  cout << "Querying highestNE..." << flush;
  before = time(0);
  result = ippst.highestNE(1,-10);
  after = time(0);
  cout << "took: " << (after - before) << endl;
  cout << "Found: " << result << endl;
  /////////////////////////////////////////////////////////////////////////////
  // highest3Sided                                                           //
  /////////////////////////////////////////////////////////////////////////////
  cout << "Querying highest3Sided..." << flush;
  before = time(0);
  result = ippst.highest3Sided(4,5,0);
  after = time(0);
  cout << "took: " << (after - before) << endl;
  cout << "Found: " << result << endl;
  /////////////////////////////////////////////////////////////////////////////
  // enumerate3Sided                                                         //
  /////////////////////////////////////////////////////////////////////////////
  cout << "Enumerating 3 sided..." << flush;
  before = time(0);
  vector<PSTPoint>* results = ippst.enumerate3Sided(1,7,-8);
  after = time(0);
  cout << "took: " << (after - before) << endl;
  if(results->size() > 0 && results->size() < 10) {
    cout << "Found: ";
    PSTArray::print(vectorPointerToArray(results),results->size());
  }
  return 0;
}
