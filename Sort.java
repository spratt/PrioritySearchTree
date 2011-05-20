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
	buildHeap(array,beginIndex,endIndex);
	while(endIndex > beginIndex) {
	    swap(array,beginIndex,endIndex);
	    endIndex--;
	    downHeap(array,0,beginIndex,endIndex);
	}
    }
    private static void buildHeap(PSTPoint[] array, int beginIndex, int endIndex) {
	int n = 1+endIndex - beginIndex;
	for(int v = n/2-1; v >= 0; v--)
	    downHeap(array,v,beginIndex,endIndex);
    }
    private static void downHeap(PSTPoint[] array, int v,
				 int beginIndex, int endIndex) {
	int w = leftChildOf(v);
	while(beginIndex + w <= endIndex) {
	    if(beginIndex + w+1 <= endIndex)
		if(array[beginIndex + w+1].xGreaterThan(array[beginIndex + w]))
		    w++;
	    if(!(array[beginIndex + w].xGreaterThan(array[beginIndex + v])))
		return;
	    swap(array,beginIndex + w,beginIndex + v);
	    v = w;
	    w = leftChildOf(v);
	}
    }
    private static int leftChildOf(int index) {
	return 2*index+1;
    }
    private static int rightChildOf(int index) {
	return 2*index+2;
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
	// heap sort
	System.out.println("HEAP SORT ON FULL ARRAY");
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
	System.out.println("HEAP SORT ON ARRAY FROM " + n + " TO " + (2*n-1));
	copyPoints = testPoints.toArray(new PSTPoint[2*n]);
	if((n*2) < 7) {
	    System.out.print("Before: "); printArray(copyPoints);
	}
	sw = new StopWatch();
	heapSort(copyPoints,n,2*n-1);
	time = sw.stop();
	System.out.println("Took: " + time);
	if((n*2) < 7) {
	    System.out.print("After: "); printArray(copyPoints);
	}
    }
/******************************************************************************
* Utility                                                                     *
******************************************************************************/
    public static void printArray(PSTPoint[] points) {
	for(int i = 0; i < points.length; i++) System.out.print(points[i] + " ");
	System.out.println();
    }
    private static void swap(PSTPoint[] array,int a, int b) {
	PSTPoint temp = array[a];
	array[a] = array[b];
	array[b] = temp;
    }
}