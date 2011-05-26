# compile with debug information

ifeq ($(mode),release)
	CXXFLAGS=-O2 -Wall
else
	mode = debug
	CXXFLAGS=-O0 -g -Wall
endif

all: test_sort test_partition test_pst

# executables
test_pst: PSTPoint.o InPlacePST.o array_utilities.o sort/heap_sort.o test_pst.cpp

test_sort: PSTPoint.o array_utilities.o sort/insertion_sort.o sort/heap_sort.o test_sort.cpp

test_partition: PSTPoint.o array_utilities.o sort/heap_sort.o sort/stable_01_sort.o test_partition.cpp

# object files
InPlacePST.o: PSTPoint.o InPlacePST.h

sort/insertion_sort.o: PSTPoint.o array_utilities.o sort/insertion_sort.h

sort/heap_sort.o: PSTPoint.o array_utilities.o sort/heap_sort.h

sort/stable_01_sort.o: PSTPoint.o array_utilities.o sort/stable_01_sort.h

array_utilities.o: PSTPoint.o array_utilities.h

PSTPoint.o: PSTPoint.h

clean:
	rm *.o
