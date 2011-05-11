import java.util.*;
public class TestPST {
    public static void main(String[] args) 
	throws EmptyTreeException, NoPointsInRangeException {
	StopWatch sw;
	long time;
	PrioritySearchTree arrayPST, pointerPST;
	System.out.println("Creating points...");
	ArrayList<PSTPoint> testPoints = new ArrayList<PSTPoint>();
	int n = Integer.parseInt(args[0]);
	for(int i = 0; i < n ; i++) {
	    testPoints.add(new PSTPoint(n-i,i));
	    testPoints.add(new PSTPoint(-i,-n+i));
	}
	System.out.println("Building PSTs with " + doubleToInt(2*n) + " nodes...");
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
	testTime(pointerPST,n);
	System.out.println();

	System.out.println("Testing array impementation...");
	testTime(arrayPST,n);
    } 
    private static void testTime(PrioritySearchTree pst, int n)
	throws EmptyTreeException, NoPointsInRangeException {
	// Find all points in range
	System.out.println("Finding all points in range...");
	StopWatch sw = new StopWatch();
	ArrayList<PSTPoint> testPoints = pst.enumerate3Sided(-n,n,n);
	long time = sw.stop();
	System.out.println("Took: " + time);

	// Find max/min x/y in range
	System.out.println("Finding max/min x/y in range...");
	double result;
	sw = new StopWatch();
	result = pst.minYinRange(-n,n,n);
	result = pst.minXinRange(-n,n,n);
	result = pst.maxXinRange(-n,n,n);
	result = pst.highest3Sided(-n,n,n);
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