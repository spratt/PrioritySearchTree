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
  bool QUIET_MODE = false;
  time_t before, after;
  int n, qi;
  PSTPoint result;
  vector<PSTPoint>* results;
  /////////////////////////////////////////////////////////////////////////////
  // Seed the PRNG                                                           //
  /////////////////////////////////////////////////////////////////////////////
  srand( time(0) );
  /////////////////////////////////////////////////////////////////////////////
  // Ensure the user has entered required parameters, otherwise print        //
  // a helpful message.                                                      //
  /////////////////////////////////////////////////////////////////////////////
  if(argv < 3) {
    cout << "Usage: test_pst [number of points] [query iterations] [quiet]"
	 << endl;
    return 1;
  }
  // parse number of points
  n = atoi(argc[1]);
  // parse query iterations
  qi = atoi(argc[2]);
  // check for quiet mode
  if(argv > 3)
    QUIET_MODE = true;
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
  if(QUIET_MODE) {
    cout << "Memory usage(%): " << flush;
    system("ps auxww | grep test_pst | grep -v grep | grep -v ps\\ | awk '{print $4}'");
  } else {
    control_utilities::waitForAnyKey();
  }
  /////////////////////////////////////////////////////////////////////////////
  // leftMostNE                                                              //
  /////////////////////////////////////////////////////////////////////////////
  cout << qi << " iterations of ";
  cout << "leftMostNE..." << flush;
  before = time(0);
  for(int i = 0; i < qi; i++)
    result = ippst.leftMostNE(rand() % n, rand() % n);
  after = time(0);
  cout << "took: " << (after - before) << endl;
  /////////////////////////////////////////////////////////////////////////////
  // highestNE                                                               //
  /////////////////////////////////////////////////////////////////////////////
  cout << qi << " iterations of ";
  cout << "highestNE..." << flush;
  before = time(0);
  for(int i = 0; i < qi; i++)
    result = ippst.highestNE(rand() % n, rand() % n);
  after = time(0);
  cout << "took: " << (after - before) << endl;
  /////////////////////////////////////////////////////////////////////////////
  // highest3Sided                                                           //
  /////////////////////////////////////////////////////////////////////////////
  cout << qi << " iterations of ";
  cout << "highest3Sided..." << flush;
  int xmin = 0;
  before = time(0);
  for(int i = 0; i < qi; i++)
    result = ippst.highest3Sided((xmin = rand() % n),
				 xmin + (rand() % (n - xmin)),
				 rand() % n);
  after = time(0);
  cout << "took: " << (after - before) << endl;
  /////////////////////////////////////////////////////////////////////////////
  // enumerate3Sided                                                         //
  /////////////////////////////////////////////////////////////////////////////
  cout << qi << " iterations of ";
  cout << "enumerate3Sided..." << flush;
  before = time(0);
  for(int i = 0; i < qi; i++) {
    results = ippst.enumerate3Sided((xmin = rand() % n),
				    xmin + (rand() % (n - xmin)),
				    rand() % n);
    delete results;
  }
  after = time(0);
  cout << "took: " << (after - before) << endl;
  return 0;
}
