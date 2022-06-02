package Data.IntervalEventQueue;

import java.util.Comparator;

public class StartEventComparator implements Comparator<StartEvent>
{
	@Override
	public int compare(StartEvent o1, StartEvent o2) 
	{
		return o1.compareTo(o2);
		/*int start1 = o1.getInterval().getStartpoint();
		int start2 = o2.getInterval().getStartpoint();
		if (start1 < start2)
		{
			return -1;
		}
		else if (start1 == start2)
		{
			return 0;
		}
		else
		{
			return 1;
		}*/
	}
}
