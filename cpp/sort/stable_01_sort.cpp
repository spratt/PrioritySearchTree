///////////////////////////////////////////////////////////////////////////////
//                       Copyright (c) 2011 - 2012 by                        //
//                                Simon Pratt                                //
//                           (All rights reserved)                           //
///////////////////////////////////////////////////////////////////////////////
//                                                                           //
// FILE:    stable_01_sort.cpp                                               //
//                                                                           //
// MODULE:  Sort                                                             //
//                                                                           //
// NOTES:   None.                                                            //
//                                                                           //
///////////////////////////////////////////////////////////////////////////////
#include <vector>
#include "../PSTPoint.h"
#include "../array_utilities.h"
#include "stable_01_sort.h"

using namespace std;

///////////////////////////////////////////////////////////////////////////////
// Utility Functions                                                         //
///////////////////////////////////////////////////////////////////////////////

bool isZero(const PSTPoint& p, const PSTPoint& s) {
  return p.getX() < s.getX();
}

bool isOne(const PSTPoint& p, const PSTPoint& s) {
  return !isZero(p,s);
}

void reverse(PSTPoint* array, int begin, int end) {
  while(begin < end)
    PSTArray::swap(array,begin++,end--);
}

void block_permute(PSTPoint* array, int beginA, int endA, int endB) {
  if(beginA == endB) return;
  reverse(array,beginA,endA);
  reverse(array,endA +1, endB);
  reverse(array,beginA,endB);
}

///////////////////////////////////////////////////////////////////////////////
// Simple Partition                                                          //
///////////////////////////////////////////////////////////////////////////////

void simple_partition(PSTPoint* array, int begin, int end, const PSTPoint& s) {
  int n = 1 + end - begin;
  if(n <= 1) return;
  // Count the zeroes
  int countZeroes = 0;
  for(int i = begin; i <= end; i++)
    if(isZero(array[i],s))
      countZeroes++;
  // now do the partitioning
  int zeroEnd = end;
  int oneEnd;
  do {
    // find the preceding zero
    while(zeroEnd > begin && isOne(array[zeroEnd],s)) zeroEnd--;
    if(zeroEnd <= begin) return;
    // find the zero immediately following the preceding one
    oneEnd = zeroEnd-1;
    while(oneEnd > begin && isZero(array[oneEnd],s)) oneEnd--;
    if(oneEnd < begin) return;
    // find the one immediately following the preceding zero
    int oneStart = oneEnd;
    while(oneStart > begin && isOne(array[oneStart-1],s)) oneStart--;
    if(isZero(array[oneStart],s)) return;
    // finally, permute the block of zeroes with the block of ones
    block_permute(array,oneStart,oneEnd,zeroEnd);
  } while((zeroEnd-oneEnd) < countZeroes);
}

///////////////////////////////////////////////////////////////////////////////
// Algorithm A                                                               //
///////////////////////////////////////////////////////////////////////////////

void algorithm_A(PSTPoint* array, int begin, int end, const PSTPoint& s) {
  int n = 1 + end - begin;
  if(n == 1) return;
  int zeroesTotal = 0;
  for(int i = begin; i <= end; i++)
    if(isZero(array[i],s))
      zeroesTotal++;
  int c0 = 0, c1 = 0;
  int* rank = (int*)malloc(n*sizeof(int));
  bool* inPlace = (bool*)malloc(n*sizeof(bool));
  for(int i = 0; i < n; i++) {
    if(isZero(array[begin + i],s)) {
      rank[i] = c0;
      c0++;
    } else { // is one
      rank[i] = zeroesTotal + c1;
      c1++;
    }
    if(i == rank[i]) inPlace[i] = true;
    else inPlace[i] = false;
  }
  // permute elements to their final positions
  for(int i = 0; i < n; i++) {
    while(!inPlace[i]) {
      PSTArray::swap(array,begin + i,begin + rank[i]);
      inPlace[rank[i]] = true;
      rank[i] = rank[rank[i]];
      if(i == rank[i]) inPlace[i] = true;
    }
  }
}

///////////////////////////////////////////////////////////////////////////////
// Exported Interface                                                        //
///////////////////////////////////////////////////////////////////////////////

void stable_01_sort(PSTPoint* array, int npoints, const PSTPoint& s) {
  stable_01_sort(array,0,npoints-1,s);
}
void stable_01_sort(PSTPoint* array, int begin, int end, const PSTPoint& s) {
  simple_partition(array,begin,end,s);
}
