all: test_sort

test_sort: PSTPoint.o array_utilities.o sort/insertion_sort.o sort/heap_sort.o test_sort.cpp

sort/insertion_sort.o: PSTPoint.o sort/insertion_sort.h

sort/heap_sort.o: PSTPoint.o sort/heap_sort.h

array_utilities.o: PSTPoint.o array_utilities.h

PSTPoint.o: PSTPoint.h

clean:
	rm *.o
