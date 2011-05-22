///////////////////////////////////////////////////////////////////////////////
//                       Copyright (c) 2011 - 2012 by                        //
//                                Simon Pratt                                //
//                           (All rights reserved)                           //
///////////////////////////////////////////////////////////////////////////////
//                                                                           //
// FILE:    InPlacePST.h                                                     //
//                                                                           //
// MODULE:  Priority Search Tree                                             //
//                                                                           //
// PURPOSE: Implements the data structure presented in "In-place             //
//          Priority Search Tree and its applications" by De,                //
//          Maheshwari, Nandy, Smid in 2011.                                 //
//                                                                           //
// NOTES:   None.                                                            //
//                                                                           //
///////////////////////////////////////////////////////////////////////////////
//                             Public Methods:                               //
///////////////////////////////////////////////////////////////////////////////
#ifndef INPLACEPST_H
#define INPLACEPST_H

#include <vector>

using namespace std;

class InPlacePST {
  PSTPoint* tree;
  int npoints;
  InPlacePST() {}
  void buildLevel(int i, int n);
  void swap(int a, int b);
  void inPlaceSort(int begin, int end, PSTPoint s);
public:
  InPlacePST(PSTPoint* points, int n);
  void printArray();
  PSTPoint getPoint(int n); // index base 1
  PSTPoint leftMostNE(double xmin, double ymin);
  PSTPoint highestNE(double xmin, double ymin);
  PSTPoint highest3Sided(double xmin, double xmax, double ymin);
  vector<PSTPoint>& enumerate3Sided(double xmin, double xmax, double ymin);
};

#endif INPLACEPST_H
