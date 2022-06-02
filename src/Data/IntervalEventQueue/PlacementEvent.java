package Data.IntervalEventQueue;

public class PlacementEvent extends IntervalEvent 
{
	PlacementEvent(int _coordinate)
	{
		super(_coordinate);
		eventType = IntervalEventType.PLACEMENT;
	}
}
