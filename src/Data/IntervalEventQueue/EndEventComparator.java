package Data.IntervalEventQueue;

import java.util.Comparator;

public class EndEventComparator implements Comparator<EndEvent>
{
	@Override
	public int compare(EndEvent o1, EndEvent o2) 
	{
		return o1.compareTo(o2);
		/*int end1 = o1.getInterval().getEndpoint();
		int end2 = o2.getInterval().getEndpoint();
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
		}*/
	}
}
