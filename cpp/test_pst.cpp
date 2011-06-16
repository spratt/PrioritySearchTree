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
#include <string>
#include <time.h>
#include "PSTPoint.h"
#include "InPlacePST.h"
#include "array_utilities.h"
#include "control_utilities.h"

using namespace std;
using namespace PrioritySearchTree;

// Dirty hack, but hey: it's c++!
PSTPoint* vectorPointerToArray(vector<PSTPoint>* v) {
  return &(*v)[0];
}

int main(int argv, char** argc) {
  time_t before, after;
  int n, qi;
  PSTPoint result;
  vector<PSTPoint>* results;
  /////////////////////////////////////////////////////////////////////////////
  // Ensure the user has entered required parameters, otherwise print        //
  // a helpful message.                                                      //
  /////////////////////////////////////////////////////////////////////////////
  if(argv < 3) {
    cout << "Usage: test_pst [number of points] [query iterations]" << endl;
    return 1;
  }
  // parse number of points
  n = atoi(argc[1]);
  // parse query iterations
  qi = atoi(argc[2]);
  /////////////////////////////////////////////////////////////////////////////
  // Setup                                                                   //
  /////////////////////////////////////////////////////////////////////////////
  cout << "Creating " << n << " points..." << flush;
  before = time(0);
  PSTPoint *points = new PSTPoint[n]; // allocate on the heap
  for(int i = 1; i < n; i++) {
    PSTPoint p(i,i); // allocate on the stack
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
  delete points;
  if(n <= 10) {
    cout << "Tree: " << endl;
    ippst.printTree();
  }
  /////////////////////////////////////////////////////////////////////////////
  // Wait for user input                                                     //
  /////////////////////////////////////////////////////////////////////////////
  control_utilities::waitForAnyKey();
  /////////////////////////////////////////////////////////////////////////////
  // leftMostNE                                                              //
  /////////////////////////////////////////////////////////////////////////////
  cout << qi << " iterations of ";
  cout << "leftMostNE..." << flush;
  before = time(0);
  for(int i = 0; i < qi; i++)
    result = ippst.leftMostNE(-10,-10);
  after = time(0);
  cout << "took: " << (after - before) << endl;
  cout << "Found: " << result << endl;
  /////////////////////////////////////////////////////////////////////////////
  // highestNE                                                               //
  /////////////////////////////////////////////////////////////////////////////
  cout << qi << " iterations of ";
  cout << "highestNE..." << flush;
  before = time(0);
  for(int i = 0; i < qi; i++)
    result = ippst.highestNE(1,-10);
  after = time(0);
  cout << "took: " << (after - before) << endl;
  cout << "Found: " << result << endl;
  /////////////////////////////////////////////////////////////////////////////
  // highest3Sided                                                           //
  /////////////////////////////////////////////////////////////////////////////
  cout << qi << " iterations of ";
  cout << "highest3Sided..." << flush;
  before = time(0);
  for(int i = 0; i < qi; i++)
    result = ippst.highest3Sided(1,n,-n);
  after = time(0);
  cout << "took: " << (after - before) << endl;
  cout << "Found: " << result << endl;
  /////////////////////////////////////////////////////////////////////////////
  // enumerate3Sided                                                         //
  /////////////////////////////////////////////////////////////////////////////
  cout << qi << " iterations of ";
  cout << "enumerate3Sided..." << flush;
  before = time(0);
  for(int i = 0; i < qi; i++)
    results = ippst.enumerate3Sided(1,n,-n);
  after = time(0);
  cout << "took: " << (after - before) << endl;
  if(results->size() > 0 && results->size() < 10) {
    cout << "Found: ";
    PSTArray::print(vectorPointerToArray(results),results->size());
  }
  return 0;
}
