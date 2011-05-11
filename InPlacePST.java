/******************************************************************************
*                       Copyright (c) 2011 - 2012 by                          *
*                               Simon Pratt                                   *
*                         (All rights reserved)                               *
*******************************************************************************
*                                                                             *
* FILE:    InPlacePST.java                                                    *
*                                                                             *
* MODULE:  Priority Search Tree                                               *
*                                                                             *
* NOTES:   Data structure presented in "In-place Priority Search Tree         *
*          and its applications" by De, Maheshwari, Nandy, Smid in 2011.      *
*                                                                             *
******************************************************************************/

public class InPlacePST implements PrioritySearchTree {
    PSTPoint[] tree;

    public InPlacePST(PSTPoint[] points) {
	tree = points;
	insertionSort(0,tree.length-1);
	int h = (int)Math.floor(log2(tree.length));
	for(int i = 0; i <= h-1; i++)
	    buildLevel(i);
    }

    private void buildLevel(int i) {
/******************************************************************************
* Find all the variables                                                      *
******************************************************************************/
	// number of nodes
	int n = tree.length;
	// height of tree
	int h = (int)Math.floor(log2(n));
	// number of nodes filled in the last level
	int A = n - (powerOf2(h) - 1);
	// the first k nodes are roots of subtrees of size k1
	int k = (int)Math.floor(A/powerOf2(h-i));
	// s is the median value
	PSTPoint s = getPoint(powerOf2(i+1));
	// the first k nodes are roots of subtrees of size k1
	int k1 = powerOf2(h+1-i) - 1;
	// the (k+1)-st node is the root of subtree of size k2
	int k2 = powerOf2(h-i) - 1 + A - k*powerOf2(h-i);
	// the remaining nodes are roots of subtrees of size k3
	int k3 = powerOf2(h-i) - 1;

/******************************************************************************
* Order nodes at given level                                                  *
******************************************************************************/

	int indexOfMaxY;

	// 
	for(int j = 1; j <= k; j++) {
	    // Find point with maximum Y in range
	    indexOfMaxY = powerOf2(i)+(j-1)*k1;
	    for(int index = indexOfMaxY; index <= powerOf2(i)+j*k1-1; index++)
		if(getPoint(index).yGreaterThan(getPoint(indexOfMaxY)))
		    indexOfMaxY = index;
	    swap(indexOfMaxY,powerOf2(i)+j-1);
	    System.out.println((powerOf2(i)+j-1));
	}
	
	//
	indexOfMaxY = powerOf2(i)+k*k1;
	for(int index = indexOfMaxY; index <= powerOf2(i)+k*k1+k2-1; index++) {
	    if(getPoint(index).yGreaterThan(getPoint(indexOfMaxY)))
		indexOfMaxY = index;
	}
	swap(indexOfMaxY,powerOf2(i)+k);
	
	//
	int m = powerOf2(i)+k*k1+k2;
	for(int j = 1; j <= powerOf2(i)-k-1; j++) {
	    indexOfMaxY = m+(j-1)*k3;
	    for(int index = indexOfMaxY; index <= m+j*k3-1; index++)
		if(getPoint(index).yGreaterThan(getPoint(indexOfMaxY)))
		    indexOfMaxY = index;
	    swap(indexOfMaxY,powerOf2(i)+k+j);
	}
	
	// Finally, sort all points past the current level
	inPlaceSort(powerOf2(i+1),n,s);
    }
/******************************************************************************
* Sorting                                                                     *
******************************************************************************/
    // Note: takes array indices of base 1
    private void inPlaceSort(int beginIndex, int endIndex, PSTPoint s) {
	insertionSort(baseZeroIndex(beginIndex),baseZeroIndex(endIndex));
    }
    // Note: takes array indices of base 0
    private void insertionSort(int beginIndex, int endIndex) {
	for(int i = beginIndex +1; i <= endIndex; i++) {
	    PSTPoint p = tree[i];
	    int j = i -1;
	    boolean done = false;
	    while(!done) {
		if(tree[j].xGreaterThan(p)) {
		    tree[j+1] = tree[j];
		    j--;
		    if(j < beginIndex)
			done = true;
		} else {
		    done = true;
		}
	    }
	    tree[j+1] = p;
	}
    }



/******************************************************************************
* Query                                                                       *
******************************************************************************/
/******************************************************************************
*                                                                             *
* FUNCTION NAME: leftMostNE                                                   *
*                                                                             *
* PURPOSE:       Determine the point with minimum x-coordinate among          *
*                all points {p ∈ P | xmin ≤ p.x ∧ ymin ≤ p.y}                 *
*                                                                             *
* PARAMETERS                                                                  *
*   Type/Name:   double/xmin                                                  *
*   Description: The minimum x coordinate to consider                         *
*                                                                             *
*   Type/Name:   double/ymin                                                  *
*   Description: The minimum y coordinate to consider                         *
*                                                                             *
* RETURN:        The PSTPoint with minimum x-coordinate within given          *
*                boundaries.                                                  *
*                                                                             *
******************************************************************************/
    public PSTPoint leftMostNE(double xmin, double ymin) {
	PSTPoint best = new PSTPoint(Double.POSITIVE_INFINITY,
				     Double.POSITIVE_INFINITY);
	int indexP = 1; // start at root
	int indexQ = 1;
	while(!isLeaf(indexP)) {
	    // UpdateLeftMost(p)
	    PSTPoint p = getPoint(indexP);
	    if(xmin <= p.getX() && p.getY() >= ymin && p.getX() < best.getX())
		best = p;
	    // UpdateLeftMost(q)
	    PSTPoint q = getPoint(indexQ);
	    if(xmin <= q.getX() && q.getY() >= ymin && q.getX() < best.getX())
		best = q; // assume q has the lower x coordinate
	    // 
	    if(indexP == indexQ) {
		if(numberOfChildren(indexP) == 1) {
		    indexQ = indexOfLeftChild(indexP);
		    indexP = indexOfLeftChild(indexP);
		} else {
		    indexQ = indexOfRightChild(indexP);
		    indexP = indexOfLeftChild(indexP);
		}
	    } else {
		if(isLeaf(indexQ))
		    indexQ = indexP;
		else if(numberOfChildren(indexQ) == 1) {
		    PSTPoint ql = getPoint(indexOfLeftChild(indexQ));
		    PSTPoint pr = getPoint(indexOfRightChild(indexP));
		    if(ql.getY() < ymin) {
			indexQ = indexOfRightChild(indexP);
			indexP = indexOfLeftChild(indexP);
		    } else if(pr.getY() < ymin) {
			indexP = indexOfLeftChild(indexP);
			indexQ = indexOfLeftChild(indexQ);
		    } else if(ql.getX() < xmin) {
			indexP = indexOfLeftChild(indexQ);
			indexQ = indexOfLeftChild(indexQ);
		    } else if(pr.getX() < xmin) {
			indexP = indexOfRightChild(indexP);
			indexQ = indexOfLeftChild(indexQ);
		    } else {
			indexQ = indexOfRightChild(indexP);
			indexP = indexOfLeftChild(indexP);
		    }
		} else { // q has 2 children
		    PSTPoint ql = getPoint(indexOfLeftChild(indexQ));
		    PSTPoint pr = getPoint(indexOfRightChild(indexP));
		    PSTPoint pl = getPoint(indexOfLeftChild(indexP));
		    if(pr.getX() >= xmin && pr.getY() >= ymin) {
			indexQ = indexOfRightChild(indexP);
			indexP = indexOfLeftChild(indexP);
		    } else if(pr.getX() < xmin) {
			if(ql.getX() < xmin) {
			    indexP = indexOfLeftChild(indexQ);
			    indexQ = indexOfRightChild(indexQ);
			} else if(ql.getY() < ymin) {
			    indexP = indexOfRightChild(indexP);
			    indexQ = indexOfRightChild(indexQ);
			} else {
			    indexP = indexOfRightChild(indexP);
			    indexQ = indexOfLeftChild(indexQ);
			}
		    } else { // pr.getX() >= xmin AND pr.getY() < ymin
			if(pl.getY() < ymin) {
			    indexP = indexOfLeftChild(indexQ);
			    indexQ = indexOfRightChild(indexQ);
			} else {
			    indexP = indexOfLeftChild(indexP);
			    if(ql.getY() >= ymin)
				indexQ = indexOfLeftChild(indexQ);
			    else
				indexQ = indexOfRightChild(indexQ);
			}
		    }
		}
	    }
	}
	// UpdateLeftMost(p)
	PSTPoint p = getPoint(indexP);
	if(xmin <= p.getX() && p.getY() >= ymin && p.getX() < best.getX())
	    best = p;
	// UpdateLeftMost(q)
	PSTPoint q = getPoint(indexQ);
	if(xmin <= q.getX() && q.getY() >= ymin && q.getX() < best.getX())
	    best = q; // assume q has the lower x coordinate
	return best;
    }

/******************************************************************************
* 
* FUNCTION NAME: highestNE 
* 
* PURPOSE:       Determine the point with maximum y-coordinate among 
*                all points {p ∈ P | xmin ≤ p.x ∧ ymin ≤ p.y} 
* 
* PARAMETERS 
*   Type/Name:   double/xmin 
*   Description: The minimum x coordinate to consider 
* 
*   Type/Name:   double/ymin 
*   Description: The minimum y coordinate to consider 
* 
* RETURN:        The PSTPoint with maximum y-coordinate within given 
*                boundaries. 
* 
* NOTES:         None. 
* 
******************************************************************************/
    public PSTPoint highestNE(double xmin, double ymin) {
	PSTPoint best = new PSTPoint(Double.POSITIVE_INFINITY,
				     Double.NEGATIVE_INFINITY);
	int indexP = 1; // root
	while(!isLeaf(indexP)) {
	    PSTPoint p = getPoint(indexP);
	    if(p.getX() >= xmin && p.getY() >= ymin) {
		// UpdateHighestMost(p)
		if(p.getY() > best.getY())
		    best = p;
		indexP = indexOfLeftChild(indexP);
	    } else if(p.getY() < ymin) {
		indexP = indexOfLeftChild(indexP);
	    } else if(numberOfChildren(indexP) == 1) {
		indexP = indexOfLeftChild(indexP);
	    } else { // must have 2 children
		PSTPoint pl = getPoint(indexOfLeftChild(indexP));
		PSTPoint pr = getPoint(indexOfRightChild(indexP));
		if(pr.getX() <= xmin) {
		    indexP = indexOfRightChild(indexP);
		} else if(pl.getX() >= xmin) {
		    if(pl.getY() > pr.getY()) indexP = indexOfLeftChild(indexP);
		    else indexP = indexOfRightChild(indexP);
		} else if(pr.getY() < ymin) {
		    indexP = indexOfLeftChild(indexP);
		} else {
		    // UpdateHighestMost(pr)
		    if(pr.getX() >= xmin &&
		       pr.getY() >= ymin &&
		       pr.getY() > best.getY())
			best = p;
		    indexP = indexOfLeftChild(indexP);
		}
	    }
	}
	PSTPoint p = getPoint(indexP);
	// UpdateHighestMost(p)
	if(p.getX() >= xmin && p.getY() >= ymin && p.getY() > best.getY())
	    best = p;
	return best;
    }

/******************************************************************************
*                                                                             *
* FUNCTION NAME: highest3Sided                                                *
*                                                                             *
* PURPOSE:       Determine the point with maximum y-coordinate among          *
*                all points {p ∈ P | xmin ≤ p.x ≤ xmax ∧ ymin ≤ p.y}          *
*                                                                             *
* PARAMETERS                                                                  *
*   Type/Name:   double/xmin                                                  *
*   Description: The minimum x coordinate to consider                         *
*                                                                             *
*   Type/Name:   double/xmax                                                  *
*   Description: The maximum x coordinate to consider                         *
*                                                                             *
*   Type/Name:   double/ymin                                                  *
*   Description: The minimum y coordinate to consider                         *
*                                                                             *
* RETURN:        The PSTPoint with maximum y-coordinate within given          *
*                boundaries.                                                  *
*                                                                             *
* NOTES:         None.                                                        *
*                                                                             *
******************************************************************************/
    public PSTPoint highest3Sided(double xmin, double xmax, double ymin) {
/******************************************************************************
* Initialization                                                              *
******************************************************************************/
	PSTPoint best = new PSTPoint(Double.POSITIVE_INFINITY,
				     Double.NEGATIVE_INFINITY);
	boolean L = false, R = false;
	PSTPoint root = getPoint(1);
	int indexP = 1, indexQ = 1; // start with the root
	if(xmin <= root.getX() && root.getX() <= xmax) {
	    if(root.getY() >= ymin)
		best = root;
	} else {
	    if(root.getX() < xmin) {
		L = true;
	    } else {
		R = true;
	    }
	}
/******************************************************************************
* Traversal                                                                   *
******************************************************************************/
	while(L || R) {
	    // Need to check from the left side of the boundary region
	    if(L && (!R || level(indexP) < level(indexQ))) {
		// CheckLeft(p)
		int indexPL = indexOfLeftChild(indexP);
		int indexPR = indexOfRightChild(indexP);
		if(isLeaf(indexP))
		    L = false;
		else if(numberOfChildren(indexP) == 1) {
		    PSTPoint pl = getPoint(indexPL);
		    if(xmin <= pl.getX() && pl.getX() <= xmax) {
			// within boundaries
			// UpdateHighestMost(Pl)
			if(pl.getY() >= ymin && pl.getY() > best.getY()) {
			    best = pl;
			}
			// end
			L = false;
		    } else if(pl.getX() < xmin) {
			// left of boundaries
			indexP = indexPL;
		    } else {
			// must be right of boundaries
			indexQ = indexPL;
			R = true;
			L = false;
		    }
		} else { // 2 children
		    PSTPoint pl = getPoint(indexPL);
		    PSTPoint pr = getPoint(indexPR);
		    if(pl.getX() < xmin) { // pl is left of boundaries
			if(pr.getX() < xmin) {
			    // Since both subtrees are to the left of
			    // the boundaries, all points within the boundaries
			    // must be in the right subtree
			    indexP = indexPR;
			} else if(pr.getX() <= xmax) {
			    // UpdateHighestMost(Pr)
			    if(pr.getY() >= ymin && pr.getY() > best.getY()) {
				best = pr;
			    }
			    // end
			    // since we've taken the best from the right subtree
			    // any remaining points within the boundaries must
			    // be in the left subtree
			    indexP = indexPL; 
			} else {
			    // pl is to the left of the boundaries and
			    // pr is to the right of the boundaries
			    // therefore, we must search left and right
			    indexQ = indexPR;
			    indexP = indexPL;
			    R = true;
			} 
		    } else if(pl.getX() <= xmax) { // pl is within the boundaries
			// UpdateHighestMost(Pl)
			if(pl.getY() >= ymin && pl.getY() > best.getY()) {
			    best = pl;
			}
			// end
			L = false;
			if(pr.getX() > xmax) { // pr is beyond the boundaries
			    indexQ = indexPR;
			    R = true;
			} else { // pr must also be within the boundaries
			    // UpdateHighestMost(Pr)
			    if(pr.getY() >= ymin && pr.getY() > best.getY()) {
				best = pr;
			    }
			    // end
			}
		    } else { // pl is right of boundaries
			// we begin searching from the right
			indexQ = indexPL;
			L = false;
			R = true;
		    }
		}
		// end CheckLeft(p)
	    }
	    // need to check from the right of the boundary region
	    else {
		// CheckRight(q)
		int indexQL = indexOfLeftChild(indexQ);
		int indexQR = indexOfRightChild(indexQ);
		// CASE 1: Q is a leaf
		if(isLeaf(indexQ)) {
		    R = false; // stop searching from the right
		}
		// CASE 2: Q has one child
		else if(numberOfChildren(indexQ) == 1) {
		    PSTPoint ql = getPoint(indexQL);
		    // CASE 2A: ql is within boundaries
		    if(xmin <= ql.getX() && ql.getX() <= xmax) {
			// UpdateHighestMost(Ql)
			if(ql.getY() >= ymin && ql.getY() > best.getY()) {
			    best = ql;
			}
			// end
			// no need to check any subtrees since they will
			// have lower y-values by design
			R = false;
		    }
		    // CASE 2B: ql is right of boundaries
		    else if(ql.getX() > xmax) {
			indexQ = indexQL; // keep checking from the right
		    }
		    // CASE 2C: ql must be left of boundaries
		    else {
			indexP = indexQL; // start checking from the left
			L = true;
			R = false;
		    }
		}
		// CASE 3: Q has 2 children
		else { 
		    PSTPoint ql = getPoint(indexQL);
		    PSTPoint qr = getPoint(indexQR);
		    // CASE 3A: qr is right of boundaries
		    if(qr.getX() > xmax) {
			// CASE 3A(I): both children are right of boundaries
			if(ql.getX() > xmax) {
			    // since the median x-coordinate that bisects the
			    // subtrees must be >= ql.getX(), only left subtree
			    // may contain points within the boundaries
			    indexQ = indexQL; 
			}
			// CASE 3A(II): ql is within boundaries
			else if(ql.getX() >= xmin) {
			    // UpdateHighestMost(Ql)
			    if(ql.getY() >= ymin && ql.getY() > best.getY()) {
				best = ql;
			    }
			    // end
			    // since we've taken the best from the left subtree
			    // any remaining points within the boundaries must
			    // be in the right subtree
			    indexQ = indexQR; 
			}
			// CASE 3A(III): ql is left of boundaries
			else {
			    // we must search left and right
			    indexP = indexQL;
			    indexQ = indexQR;
			    L = true; // check left
			} 
		    }
		    // CASE 3B: qr is within boundaries
		    else if(qr.getX() >= xmin) { 
			// UpdateHighestMost(Qr)
			if(qr.getY() >= ymin && qr.getY() > best.getY()) {
			    best = qr;
			}
			// end
			R = false;
			// CASE 3B(I): ql is left of boundaries
			if(ql.getX() < xmin) { 
			    indexP = indexQR;
			    L = true; // search from the left
			}
			// CASE 3B(II): ql must be within boundaries
			else { 
			    // UpdateHighestMost(Ql)
			    if(ql.getY() >= ymin && ql.getY() > best.getY()) {
				best = ql;
			    }
			    // end
			}
		    }
		    // CASE 3C: qr is left of boundaries
		    else { 
			// begin searching from the left
			indexP = indexQR;
			L = true;
			R = false;
		    }
		}
		// end CheckRight(q)
	    }
	}
	return best;
    }
    
/******************************************************************************
* Utility                                                                     *
******************************************************************************/
    private int powerOf2(int x) {
	return (int)Math.pow(2,x);
    }
    private PSTPoint getPoint(int index) { // base 1
	return tree[baseZeroIndex(index)];
    }
    private void setPoint(int index,PSTPoint p) { // base 1
	tree[baseZeroIndex(index)] = p;
    }
    private boolean isLeaf(int index) { // base 1
	return indexOfLeftChild(index) > tree.length;
    }
    private int numberOfChildren(int index) { // base 1
	if(isLeaf(index)) return 0;
	if(indexOfRightChild(index) > tree.length) return 1;
	return 2;
    }
    private int indexOfLeftChild(int index) { // base 1
	return (2*index);
    }
    private int indexOfRightChild(int index) { // base 1
	return (2*index)+1;
    }
    private void swap(int i, int j) { // base 1
	PSTPoint temp;
	temp = getPoint(i);
	setPoint(i,getPoint(j));
	setPoint(j,temp);
    }
    public void printArray() {
	printArray(tree);
    }
    public static void printArray(PSTPoint[] points) {
	for(int i = 0; i < points.length; i++) System.out.print(points[i] + " ");
	System.out.println();
    }
    // Gives the base-2 logarithm of a double
    private double log2(int x) {
	return Math.log(x) / Math.log(2);
    }
    private int level(int index) {
	return (int)log2(index);
    }

/******************************************************************************
*                                                                             *
* FUNCTION NAME: baseZeroIndex                                                *
*                                                                             *
* PURPOSE:       Converts the index of a base one array into the index        *
*                of a base zero array.                                        *
*                                                                             *
* PARAMETERS                                                                  *
*   Type/Name:   int/baseOneIndex                                             *
*   Description: The index of an array with base one.                         *
*                                                                             *
* RETURN:        The integer equivalent index in an array with base zero.     *
*                                                                             *
******************************************************************************/
    private static int baseZeroIndex(int baseOneIndex) {
	return baseOneIndex - 1;
    }

/******************************************************************************
* Testing                                                                     *
******************************************************************************/
    public static void main(String[] args) {
	System.out.println("Creating points...");
	int n = Integer.parseInt(args[0]);
	PSTPoint[] testPoints = new PSTPoint[2*n];
	int count = 0;
	for(int i = -n; i < n ; i++) {
	    if((i%2) == 0)
		testPoints[count++] = new PSTPoint(i,i+n);
	    else
		testPoints[count++] = new PSTPoint(-i,-(i+n));
	}
	System.out.println("Building PST with " + (2*n) + " nodes...");
	if(n < 10) {
	    System.out.print("Points: "); printArray(testPoints);
	}
	InPlacePST ippst = new InPlacePST(testPoints);
	if(n < 10) {
	    System.out.print("PST: "); ippst.printArray();
	}
	// Test queries
	System.out.println("leftMostNE(x=-10,y=-10):             "
			   + ippst.leftMostNE(-10,-10));
	System.out.println("highestNE(x=1,y=-10):                "
			   + ippst.highestNE(1,-10));
	System.out.println("highest3Sided(xmin=4,xmax=5,ymin=0): "
			   + ippst.highest3Sided(4,5,0));
    }

/******************************************************************************
* Stubs (for now)                                                             *
******************************************************************************/
    
    public java.util.ArrayList<PSTPoint> enumerate3Sided(double minX,
							     double maxX,
							     double maxY)
	throws EmptyTreeException {
	return null;
    }
    public PSTPoint minYinRange(double minX, double maxX, double maxY)
	throws NoPointsInRangeException {
	throw new NoPointsInRangeException();
    }
    public PSTPoint minXinRange(double minX, double maxX, double maxY)
	throws NoPointsInRangeException {
	throw new NoPointsInRangeException();
    }
    public PSTPoint maxXinRange(double minX, double maxX, double maxY)
	throws NoPointsInRangeException {
	throw new NoPointsInRangeException();
    }
}
