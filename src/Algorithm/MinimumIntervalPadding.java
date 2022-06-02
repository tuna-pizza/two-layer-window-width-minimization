package Algorithm;

import java.util.LinkedList;
import java.util.PriorityQueue;

import Data.IntervalEventQueue.*;

public class MinimumIntervalPadding
{
	static public LinkedList<Interval> IntervalPadding(Iterable<Interval> intervals)
	{
		EventQueue eq = new EventQueue(intervals);
		PriorityQueue<Interval> pq = new PriorityQueue<Interval>(100,new IntervalComparator());
		LinkedList<Interval> ret = new LinkedList<Interval>();
		while (!eq.is_empty())
		{
			IntervalEvent e = eq.peek();
			switch(e.getEventType())
			{
				case START:
				{
					pq.add(e.getInterval());
					eq.pop(e);
					if (!eq.placementScheduled())
					{
						eq.pushPlacement(e);
					}
					break;
				}
				case PLACEMENT:
				{
					Interval next = (Interval) pq.poll();
					next.setParentPosition(e.getCoordinate());
					ret.add(next);
					eq.pop(e);
					if (!pq.isEmpty())
					{
						eq.pushPlacement(e);				
					}
					break;
				}
				case END:
				{
					Interval i = e.getInterval();
					if (!i.is_placed())
					{
						eq.pushPlacement(e);
						eq.increaseOffset();
						ret.getLast().setShifted();
					}
					else
					{
						eq.pop(e);
					}
					break;
				}
			}
		}
		return ret;
	}
}
