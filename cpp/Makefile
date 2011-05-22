CXX=g++ -O0 -g

all: test_sort test_pst

test_pst: PSTPoint.o InPlacePST.o array_utilities.o sort/heap_sort.o test_pst.cpp

test_sort: PSTPoint.o array_utilities.o sort/insertion_sort.o sort/heap_sort.o test_sort.cpp

InPlacePST.o: PSTPoint.o InPlacePST.h

sort/insertion_sort.o: PSTPoint.o sort/insertion_sort.h

sort/heap_sort.o: PSTPoint.o sort/heap_sort.h

array_utilities.o: PSTPoint.o array_utilities.h

PSTPoint.o: PSTPoint.h

clean:
	rm *.o
