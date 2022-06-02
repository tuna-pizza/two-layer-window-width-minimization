package Data.IntervalEventQueue;

abstract public class IntervalEvent 
{
    protected IntervalEventType eventType;
    private int coordinate;
    protected Interval parentInterval;
    public IntervalEvent(int _coordinate) 
    {
    	coordinate = _coordinate;
    }

    public Interval getInterval()
    {
        return parentInterval;
    }

    public int getCoordinate() 
    {
        return coordinate;
    }

    public IntervalEventType getEventType()
    {
        return eventType;
    }
}