package Data.IntervalEventQueue;

public class StartEvent extends IntervalEvent implements Comparable<StartEvent>
{
	StartEvent(int _coordinate, Interval i)
	{
		super(_coordinate);
		parentInterval = i;
		eventType = IntervalEventType.START;
	}

	@Override
	public int compareTo(StartEvent o) {
		int myStart = this.getInterval().getStartpoint();
		int otherStart = o.getInterval().getStartpoint();
		if (myStart < otherStart)
		{
			return -1;
		}
		else if (myStart == otherStart)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
}
