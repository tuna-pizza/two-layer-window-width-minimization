package Algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import Data.IntervalEventQueue.Interval;
import Data.TwoLayerGraph.*;

public class WindowAndBandWidth 
{
	static private HashMap<Vertex,Vertex> getLeftmostNeighbors(BipartiteGraph graph)
	{
		HashMap<Vertex,Vertex> l= new HashMap<Vertex,Vertex>();
        //preprocessing
        //calculate l(v) and r(v) for each v in A
        for(Edge e : graph.getEdges())
        {
            if(!l.containsKey(e.getA()) || e.getB().getX()<l.get(e.getA()).getX())
            {
                l.put(e.getA(),e.getB());
            }
        }
        return l;
	}
	static private HashMap<Vertex,Vertex> getRightmostNeighbors(BipartiteGraph graph)
	{
		HashMap<Vertex,Vertex> r= new HashMap<Vertex,Vertex>();
        //preprocessing
        //calculate l(v) and r(v) for each v in A
        for(Edge e : graph.getEdges())
        {
        	if(!r.containsKey(e.getA()) || e.getB().getX()>r.get(e.getA()).getX())
            {
                r.put(e.getA(),e.getB());
            }
        }
        return r;
	}
	static private int getMaxDistanceOfNeighbors(BipartiteGraph graph, HashMap<Vertex,Vertex> leftmostNeighbors, HashMap<Vertex,Vertex> rightmostNeighbors)
	{
		int k = 0;
		for(Vertex v : graph.getA())
        {
            if (rightmostNeighbors.get(v).getX()-leftmostNeighbors.get(v).getX()>k)
            {
                k = rightmostNeighbors.get(v).getX()-leftmostNeighbors.get(v).getX(); //initially k is the maximum neighbourhood span
            }
        }
		return k;
	}
	static private HashSet<Interval> createIntervals(BipartiteGraph graph, HashMap<Vertex,Vertex> leftmostNeighbors, HashMap<Vertex,Vertex> rightmostNeighbors, int k)
	{
		HashSet<Interval> intervals = new HashSet<Interval>();
		for(Vertex v : graph.getA())
        {
            Interval interval = new Interval(rightmostNeighbors.get(v).getX()-k, leftmostNeighbors.get(v).getX()+k, v);
            intervals.add(interval);
        }
		return intervals;
	}
	static private void applyShift(LinkedList<Interval> sortedIntervals)
	{
		 int shift = 0;
	        while(!sortedIntervals.isEmpty())
	        {
	            Interval interval = sortedIntervals.getLast();
	            if(interval.getShifted())
	            {
	            	shift++;
	            }
	            interval.getParent().setX(interval.getParentPosition()-shift);
	            sortedIntervals.remove(interval);
	        }
	}
	
	static public BipartiteGraph MinimumWindowWidthDrawing(BipartiteGraph graph)
	{
		graph = (BipartiteGraph) graph.clone();
        HashMap<Vertex,Vertex> l= getLeftmostNeighbors(graph);
        HashMap<Vertex,Vertex> r= getRightmostNeighbors(graph);
        int k = getMaxDistanceOfNeighbors(graph,l,r); //at the end of the algorithm this will be the window width
        HashSet<Interval> intervals = createIntervals(graph,l,r,k);
        LinkedList<Interval> sortedIntervals = MinimumIntervalPadding.IntervalPadding(intervals);
        applyShift(sortedIntervals);
		return graph;    
    }
	
	static public BipartiteGraph MinimumBandwidthDrawing(BipartiteGraph graph)
	{
		graph = (BipartiteGraph) graph.clone();
        HashMap<Vertex,Vertex> l= getLeftmostNeighbors(graph);
        HashMap<Vertex,Vertex> r= getRightmostNeighbors(graph);
        int k = (int)Math.ceil(getMaxDistanceOfNeighbors(graph,l,r)/2.0); //at the end of the algorithm this will be the window width
        HashSet<Interval> intervals = createIntervals(graph,l,r,k);
        LinkedList<Interval> sortedIntervals = MinimumIntervalPadding.IntervalPadding(intervals);
        applyShift(sortedIntervals);
		return graph;    
    }
	
	static public int getWindowWidth(BipartiteGraph graph)
	{
		int k = 0;
		HashMap<Vertex,Vertex> l= getLeftmostNeighbors(graph);
        HashMap<Vertex,Vertex> r= getRightmostNeighbors(graph);
        for (Vertex a : graph.getA())
        {
        	int ownX = a.getX();
        	int leftX = Math.min(l.get(a).getX(),ownX);
        	int rightX = Math.max(r.get(a).getX(),ownX);
        	int window = rightX - leftX + 1;
        	if (k < window)
        	{
        		k = window;
        	}
        }
        return k;
	}
	
	static public int getBandwidth(BipartiteGraph graph)
	{
		int k = 0;
        for (Edge e : graph.getEdges())
        {
        	int x1 = e.getA().getX();
        	int x2 = e.getB().getX();
        	int bandwidth = Math.abs(x1-x2);
        	if (k < bandwidth)
        	{
        		k = bandwidth;
        	}
        }
        return k;
	}
	
	static public String getWindowWidthLabel(BipartiteGraph graph)
	{
		int k = 0;
		String worstVertex = "";
		HashMap<Vertex,Vertex> l= getLeftmostNeighbors(graph);
        HashMap<Vertex,Vertex> r= getRightmostNeighbors(graph);
        for (Vertex a : graph.getA())
        {
        	int ownX = a.getX();
        	int leftX = Math.min(l.get(a).getX(),ownX);
        	int rightX = Math.max(r.get(a).getX(),ownX);
        	int window = rightX - leftX + 1;
        	if (k < window)
        	{
        		k = window;
        		worstVertex = a.getLabel();
        	}
        }
        return worstVertex;
	}
}
