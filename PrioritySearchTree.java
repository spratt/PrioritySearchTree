public interface PrioritySearchTree {
    public java.util.ArrayList<PSTPoint> enumerate3Sided(double minX,
						   double maxX,
						   double maxY)
	throws EmptyTreeException;
    public PSTPoint minYinRange(double minX, double maxX, double maxY)
	throws NoPointsInRangeException;
    public PSTPoint minXinRange(double minX, double maxX, double maxY)
	throws NoPointsInRangeException;
    public PSTPoint maxXinRange(double minX, double maxX, double maxY)
	throws NoPointsInRangeException;
    public PSTPoint highest3Sided(double minX, double maxX, double maxY)
	throws NoPointsInRangeException;
}