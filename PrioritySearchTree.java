import java.util.*;
public interface PrioritySearchTree {
    public ArrayList<PSTPoint> findAllPointsWithin(double minX,
						   double maxX,
						   double maxY)
	throws EmptyTreeException;
    public double minYinRange(double minX, double maxX, double maxY)
	throws NoPointsInRangeException;
    public double minXinRange(double minX, double maxX, double maxY)
	throws NoPointsInRangeException;
    public double maxXinRange(double minX, double maxX, double maxY)
	throws NoPointsInRangeException;
    public double maxYinRange(double minX, double maxX, double maxY)
	throws NoPointsInRangeException;
}