///////////////////////////////////////////////////////////////////////////////
//                       Copyright (c) 2011 - 2012 by                        //
//                                Simon Pratt                                //
//                           (All rights reserved)                           //
///////////////////////////////////////////////////////////////////////////////
//                                                                           //
// FILE:    InPlacePST.cpp                                                   //
//                                                                           //
// MODULE:  Priority Search Tree                                             //
//                                                                           //
// NOTES:   Implements the data structure presented in "In-place             //
//          Priority Search Tree and its applications" by De,                //
//          Maheshwari, Nandy, Smid in 2011.                                 //
//                                                                           //
///////////////////////////////////////////////////////////////////////////////
#include <vector>
#include <cmath>
#include "PSTPoint.h"
#include "array_utilities.h"
#include "sort/heap_sort.h"
#include "InPlacePST.h"

using namespace std;

InPlacePST::InPlacePST(PSTPoint* points, int n) {
  npoints = n;
  tree = new PSTPoint[n];
  PSTArray::copy(points,tree,n);
  heap_sort(tree,n);
  int h = (int)log2(n);
  for(int i = 0; i <= h-1; i++)
    buildLevel(i,n);
}

void InPlacePST::printArray() {
  PSTArray::print(tree,npoints);
}

int powerOf2(int x) {
  return (int)pow((double)2,(double)x);
}

PSTPoint InPlacePST::getPoint(int n) { // index base 1
  return tree[n-1];
}

void InPlacePST::inPlaceSort(int begin, int end, PSTPoint s) {
  heap_sort(tree,begin-1,end-1);
}

void InPlacePST::swap(int a, int b) {
  PSTArray::swap(tree,a-1,b-1);
}

void InPlacePST::buildLevel(int i, int n) {
  /////////////////////////////////////////////////////////////////////////////
  // Find all the variables                                                  //
  /////////////////////////////////////////////////////////////////////////////
  // height of tree
  int h = (int)log2(n);
  // number of nodes filled in the last level
  int A = n - (powerOf2(h) - 1);
  // the first k nodes are roots of subtrees of size k1
  int k = (int)(A/powerOf2(h-i));
  // s is the median value
  PSTPoint s = getPoint(powerOf2(i+1));
  // the first k nodes are roots of subtrees of size k1
  int k1 = powerOf2(h+1-i) - 1;
  // the (k+1)-st node is the root of subtree of size k2
  int k2 = powerOf2(h-i) - 1 + A - k*powerOf2(h-i);
  // the remaining nodes are roots of subtrees of size k3
  int k3 = powerOf2(h-i) - 1;

  /////////////////////////////////////////////////////////////////////////////
  // Order nodes at given level                                              //
  /////////////////////////////////////////////////////////////////////////////
  int indexOfMaxY;

  // build the subtrees of size k1
  for(int j = 1; j <= k; j++) {
    // Find point with maximum Y in range
    indexOfMaxY = powerOf2(i)+(j-1)*k1;
    for(int index = indexOfMaxY; index <= powerOf2(i)+j*k1-1; index++)
      if(getPoint(index).yGreaterThan(getPoint(indexOfMaxY)))
	indexOfMaxY = index;
    swap(indexOfMaxY,powerOf2(i)+j-1);
  }

  if(k < powerOf2(i)) {
    // build the one subtree of size k2
    indexOfMaxY = powerOf2(i)+k*k1;
    for(int index = indexOfMaxY; index <= powerOf2(i)+k*k1+k2-1; index++) {
      if(getPoint(index).yGreaterThan(getPoint(indexOfMaxY)))
	indexOfMaxY = index;
    }
    swap(indexOfMaxY,powerOf2(i)+k);
	
    // build the subtrees of size k3
    int m = powerOf2(i)+k*k1+k2;
    for(int j = 1; j <= powerOf2(i)-k-1; j++) {
      indexOfMaxY = m+(j-1)*k3;
      for(int index = indexOfMaxY; index <= m+j*k3-1; index++)
	if(getPoint(index).yGreaterThan(getPoint(indexOfMaxY)))
	  indexOfMaxY = index;
      swap(indexOfMaxY,powerOf2(i)+k+j);
    }
  }
  // Finally, sort all points past the current level
  inPlaceSort(powerOf2(i+1),n,s);
}
PSTPoint InPlacePST::leftMostNE(double xmin, double ymin) {
}
PSTPoint InPlacePST::highestNE(double xmin, double ymin) {
}
PSTPoint InPlacePST::highest3Sided(double xmin, double xmax, double ymin) {
}
vector<PSTPoint>& InPlacePST::enumerate3Sided(double xmin,
					      double xmax,
					      double ymin) {
  
}
