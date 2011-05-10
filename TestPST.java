import java.util.*;
public class TestPST {
    public static void main(String[] args) 
	throws EmptyTreeException, NoPointsInRangeException {
	StopWatch sw;
	long time;
	PrioritySearchTree arrayPST, pointerPST;
	System.out.println("Creating points...");
	ArrayList<PSTPoint> testPoints = new ArrayList<PSTPoint>();
	double MAX_Y = 500000d;
	for(double i = 0; i < MAX_Y ; i++) {
	    testPoints.add(new PSTPoint(MAX_Y-i,i));
	    testPoints.add(new PSTPoint(-i,-MAX_Y+i));
	}
	System.out.println("Building PSTs with " + doubleToInt(2*MAX_Y) + " nodes...");
	sw = new StopWatch();
	pointerPST = new PointerPST(testPoints);
	time = sw.stop();
	System.out.println("Pointer implementation took: " + time);
	sw = new StopWatch();
	arrayPST = new ArrayPST(testPoints);
	time = sw.stop();
	System.out.println("Array implementation took: " + time);
	System.out.println();

	System.out.println("Testing pointer impementation...");
	testTime(pointerPST,MAX_Y);
	System.out.println();

	System.out.println("Testing array impementation...");
	testTime(arrayPST,MAX_Y);
    } 
    private static void testTime(PrioritySearchTree pst, double MAX_Y)
	throws EmptyTreeException, NoPointsInRangeException {
	// Find all points in range
	System.out.println("Finding all points in range...");
	StopWatch sw = new StopWatch();
	ArrayList<PSTPoint> testPoints = pst.enumerate3Sided(-MAX_Y,MAX_Y,MAX_Y);
	long time = sw.stop();
	System.out.println("Took: " + time);

	// Find max/min x/y in range
	System.out.println("Finding max/min x/y in range...");
	double result;
	sw = new StopWatch();
	result = pst.minYinRange(-MAX_Y,MAX_Y,MAX_Y);
	result = pst.minXinRange(-MAX_Y,MAX_Y,MAX_Y);
	result = pst.maxXinRange(-MAX_Y,MAX_Y,MAX_Y);
	result = pst.maxYinRange(-MAX_Y,MAX_Y,MAX_Y);
	time = sw.stop();
	System.out.println("Took: " + time);
    }
    private static int doubleToInt(double d) {
	return (new Double(d)).intValue();
    }
    // height of a balanced tree with n elements
    private static int treeHeight(int n) {
	return doubleToInt(Math.ceil(Math.log(n+1)/Math.log(2)));
    }
    // max number of heap nodes in a tree of given height
    private static int heapSize(int height) {
	return doubleToInt(Math.pow(2, height)-1);
    }
    // width of a tree at a given depth
    private static int width(int depth) {
	return doubleToInt(Math.pow(2,depth-1));
    }
    // amount of unused space allocated for a given number of nodes
    private static int waste(int n) {
	int height = treeHeight(n);
	return (width(height) - (n - heapSize(height-1)));
    }
    private static void printList(ArrayList<PSTPoint> points) {
	for(PSTPoint p : points) System.out.print(p + " ");
	System.out.println();
    }
    private static void report(int n) {
	System.out.println("Nodes: " + n);
	int height = treeHeight(n);
	System.out.println("Tree depth: " + height);
	int heapSize = heapSize(height);
	System.out.println("Heap size: " + heapSize);
	System.out.println("Width at max depth: " + width(height));
	System.out.println("Unused nodes: " + (heapSize - n));
    }
}