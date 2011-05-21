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
  if(argv < 2) {
    cout << "Usage: test_sort {number of data points}" << endl;
    return 1;
  }
  int n = atoi(argc[1]);
  PSTPoint p(n,n);
  cout << p << endl;
  return 0;
}
