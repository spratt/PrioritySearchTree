all: PSTPoint.o sort/insertion_sort.o test_sort

test_sort: PSTPoint.o sort/insertion_sort.o test_sort.cpp

sort/insertion_sort.o: PSTPoint.o sort/insertion_sort.h

PSTPoint.o: PSTPoint.h

clean:
	rm *.o
