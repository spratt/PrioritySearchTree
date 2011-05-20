import java.util.*;

public class Sort {
/******************************************************************************
* Insertion Sort                                                              *
******************************************************************************/
    public static void insertionSort(PSTPoint[] array) {
	insertionSort(array,0,array.length-1);
    }
    public static void insertionSort(PSTPoint[] array, int beginIndex, int endIndex) {
	PSTPoint p;
	int j;
	boolean done;
	for(int i = beginIndex +1; i <= endIndex; i++) {
	    p = array[i];
	    j = i -1;
	    done = false;
	    while(!done) {
		if(array[j].xGreaterThan(p)) {
		    array[j+1] = array[j];
		    j--;
		    if(j < beginIndex)
			done = true;
		} else {
		    done = true;
		}
	    }
	    array[j+1] = p;
	}
    }
/******************************************************************************
* Heap Sort                                                                   *
******************************************************************************/
    public static void heapSort(PSTPoint[] array) {
	heapSort(array,0,array.length-1);
    }
    public static void heapSort(PSTPoint[] array, int beginIndex, int endIndex) {
	int count = 1+ endIndex - beginIndex;
	heapify(array,beginIndex,count);
	while(endIndex > beginIndex) {
	    swap(array,endIndex,beginIndex);
	    siftDown(array,beginIndex,endIndex-1);
	    endIndex--;
	}
    }
    private static void heapify(PSTPoint[] array, int beginIndex, int count){
	int start = count/2 - 1;
	while(start >= beginIndex) {
	    siftDown(array,start,count-1);
	    start--;
	}
    }
    private static void siftDown(PSTPoint[] array, int start, int end) {
	int root = start;
	while(root*2+1 <= end) {
	    int child = root * 2 + 1;
	    int swap = root;
	    if(array[swap].xLessThan(array[child])) {
		swap = child;
	    }
	    if(child+1 <= end && array[swap].xLessThan(array[child+1])) {
		swap = child+1;
	    }
	    if(swap != root) {
		swap(array,root,swap);
		root = swap;
	    } else return;
	}
    }
    private static void swap(PSTPoint[] array,int a, int b) {
	PSTPoint temp = array[a];
	array[a] = array[b];
	array[b] = temp;
    }
/******************************************************************************
* Testing                                                                     *
******************************************************************************/
    public static void main(String[] args) {
	int n = 1000;
	if(args.length > 0) n = Integer.parseInt(args[0]);
	System.out.println("Creating " + (2*n) + " points...");
	ArrayList<PSTPoint> testPoints = new ArrayList<PSTPoint>();
	for(int i = -n; i < n ; i++) {
	    if((i%2) == 0)
		testPoints.add(new PSTPoint(i,i+n));
	    else
		testPoints.add(new PSTPoint(-i,-(i+n)));
	}
	PSTPoint[] copyPoints, oldPoints;
	StopWatch sw;
	long time;
	boolean same;
	// heap sort
	System.out.println("HEAP SORT");
	copyPoints = testPoints.toArray(new PSTPoint[2*n]);
	if((n*2) < 7) {
	    System.out.print("Before: "); printArray(copyPoints);
	}
	sw = new StopWatch();
	heapSort(copyPoints);
	time = sw.stop();
	System.out.println("Took: " + time);
	if((n*2) < 7) {
	    System.out.print("After: "); printArray(copyPoints);
	}
	// insertion sort
	System.out.println("INSERTION SORT");
	oldPoints = copyPoints;
	copyPoints = testPoints.toArray(new PSTPoint[2*n]);
	if((n*2) < 7) {
	    System.out.print("Before: "); printArray(copyPoints);
	}
	sw = new StopWatch();
	insertionSort(copyPoints);
	time = sw.stop();
	System.out.println("Took: " + time);
	if((n*2) < 7) {
	    System.out.print("After: "); printArray(copyPoints);
	}
	same = true;
	for(int i = 0; i < copyPoints.length; i++)
	    if(copyPoints[i] != oldPoints[i]) {
		same = false;
		break;
	    }
	if(same)
	    System.out.println("...same as previous.");
	else
	    System.out.println("NOT SAME AS PREVIOUS!");
    }
/******************************************************************************
* Utility                                                                     *
******************************************************************************/
    public static void printArray(PSTPoint[] points) {
	for(int i = 0; i < points.length; i++) System.out.print(points[i] + " ");
	System.out.println();
    }
}