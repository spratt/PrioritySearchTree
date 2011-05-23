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
#include "../PSTPoint.h"
#include "../array_utilities.h"
#include "stable_01_sort.h"

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
// Exported Interface                                                        //
///////////////////////////////////////////////////////////////////////////////

void stable_01_sort(PSTPoint* array, int npoints, const PSTPoint& s) {
  stable_01_sort(array,0,npoints-1,s);
}
void stable_01_sort(PSTPoint* array, int begin, int end, const PSTPoint& s) {
  simple_partition(array,begin,end,s);
}
