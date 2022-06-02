package Data.IntervalEventQueue;

import java.util.ArrayList;

public class EventQueue 
{
    private int offset = 0;
    private int nextStart = 0;
    private int nextEnd = 0;
    private ArrayList<StartEvent> startEvents;
    private ArrayList<EndEvent> endEvents;
    private PlacementEvent placementEvent;
    public EventQueue(Iterable<Interval> intervals)
    {
    	startEvents = new ArrayList<StartEvent>();
    	endEvents = new ArrayList<EndEvent>();
    	placementEvent = null;
    	for (Interval i : intervals)
    	{
    		StartEvent s = new StartEvent(i.getStartpoint(),i);
    		EndEvent e = new EndEvent(i.getEndpoint(),i);
    		startEvents.add(s);
    		endEvents.add(e);
    	}
    	startEvents.sort(new StartEventComparator());
    	endEvents.sort(new EndEventComparator());
    }
    
    public IntervalEvent peek()
    {
    	int startx = Integer.MAX_VALUE;
    	if (nextStart < startEvents.size())
    	{
    		startx = startEvents.get(nextStart).getCoordinate() - offset;
    	}
    	int endx = Integer.MAX_VALUE;
    	if (nextEnd < endEvents.size())
		{
			endx = endEvents.get(nextEnd).getCoordinate() + offset;
		}
    	int placementx = Integer.MAX_VALUE;
    	if (placementEvent != null)
    	{
    		placementx = placementEvent.getCoordinate();
    	}
    	if (startx <= Math.min(endx,placementx))
    	{
    		return startEvents.get(nextStart);
    	}
    	else if (endx < placementx)
    	{
    		return endEvents.get(nextEnd);
    	}
    	else
    	{
    		return placementEvent;
    	}
    }
    
    public void pop(IntervalEvent e)
    {
    	if (nextStart < startEvents.size())
    	{
	    	if (e == startEvents.get(nextStart))
	    	{
	    		nextStart++;
	    	}
    	}
    	if (nextEnd < endEvents.size())
    	{
	    	if (e == endEvents.get(nextEnd))
	    	{
	    		nextEnd++;
	    	}
    	}
    	if (e == placementEvent)
    	{
    		placementEvent = null;
    	}
    }
    
    public void increaseOffset()
    {
    	offset++;
    }
    
    public void pushPlacement(IntervalEvent e)
    {
    	switch (e.eventType)
    	{
    		case START:
    		{
    			placementEvent = new PlacementEvent(e.getCoordinate()-offset);	
    			break;
    		}
    		case PLACEMENT:
    		{
    			placementEvent = new PlacementEvent(e.getCoordinate()+1);	
    			break;
    		}
    		case END:
    		{
    			placementEvent = new PlacementEvent(e.getCoordinate()+offset);	
    			break;
    		}
    	}
    }
    
    public boolean placementScheduled()
    {
    	return (placementEvent != null);
    }
    
    public boolean is_empty()
    {
    	if (nextStart < startEvents.size())
    	{
    		return false;
    	}
    	if (nextEnd < endEvents.size())
    	{
    		return false;
    	}
    	if (placementEvent != null)
    	{
    		return false;
    	}
    	return true;
    }
}