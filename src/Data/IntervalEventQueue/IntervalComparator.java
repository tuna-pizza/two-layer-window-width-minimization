package Data.IntervalEventQueue;

import java.util.Comparator;

public class IntervalComparator implements Comparator<Interval>
{

	@Override
	public int compare(Interval o1, Interval o2) 
	{
		int end1 = o1.getEndpoint();
		int end2 = o2.getEndpoint();
		if (end1 < end2)
		{
			return -1;
		}
		else if (end1 == end2)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}

}
