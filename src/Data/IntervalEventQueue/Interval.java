package Data.IntervalEventQueue;

import Data.TwoLayerGraph.Vertex;

public class Interval 
{
    private int startpoint;
    private int endpoint;
    private Vertex parent;
    private int parentPosition;
    private boolean shifted;
    private boolean isPlaced;

    public Interval(int startpoint, int endpoint, Vertex parent) 
    {
        this.startpoint = startpoint;
        this.endpoint = endpoint;
        this.parent = parent;
        parentPosition = Integer.MIN_VALUE;
        shifted = false;
        isPlaced = false;
    }

    public int getStartpoint() 
    {
        return startpoint;
    }

    public int getEndpoint() 
    {
        return endpoint;
    }

    public Vertex getParent() 
    {
        return parent;
    }
    
    public void setParentPosition(int _p) 
    {
        parentPosition = _p;
        isPlaced = true;
    }
    
    public int getParentPosition() 
    {
        return parentPosition;
    }
    
    public void setShifted() 
    {
    	shifted = true;
    }
    
    public boolean getShifted() 
    {
        return shifted;
    }
    
    public boolean is_placed()
    {
    	return isPlaced;
    }
}