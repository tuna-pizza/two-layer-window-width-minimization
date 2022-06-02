package Data.IntervalEventQueue;

public class EndEvent extends IntervalEvent implements Comparable<EndEvent>
{
	EndEvent(int _coordinate, Interval i)
	{
		super(_coordinate);
		parentInterval = i;
		eventType = IntervalEventType.END;
	}

	@Override
	public int compareTo(EndEvent o) {
		int myEnd = this.getInterval().getEndpoint();
		int otherEnd = o.getInterval().getEndpoint();
		if (myEnd < otherEnd)
		{
			return -1;
		}
		else if (myEnd == otherEnd)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
}
