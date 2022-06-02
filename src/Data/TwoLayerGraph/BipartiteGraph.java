package Data.TwoLayerGraph;

import java.util.HashMap;
import java.util.HashSet;

public class BipartiteGraph 
{
	private HashSet<Vertex> A;
	private HashSet<Vertex> B;
	private HashSet<Edge> E;
	
	@SuppressWarnings("unchecked")
	public Iterable<Vertex> getA()
	{
		return (HashSet<Vertex>)A.clone();
	}
	@SuppressWarnings("unchecked")
	public Iterable<Vertex> getB()
	{
		return (HashSet<Vertex>)B.clone();
	}
	@SuppressWarnings("unchecked")
	public Iterable<Edge> getEdges()
	{
		return (HashSet<Edge>)E.clone();
	}
	
	public BipartiteGraph(Iterable<Vertex> _A, Iterable<Vertex> _B, Iterable<Edge> _E)
	{
		A = new HashSet<Vertex>();
		B = new HashSet<Vertex>();
		E = new HashSet<Edge>();
		for (Vertex a : _A)
		{
			A.add(a);
		}
		for (Vertex b : _B)
		{
			B.add(b);
		}
		for (Edge e : _E)
		{
			E.add(e);
		}
	}
	
	@Override
	public Object clone()
	{
		HashSet<Vertex> newA = new HashSet<Vertex>();
		HashSet<Vertex> newB = new HashSet<Vertex>();
		HashSet<Edge> newE = new HashSet<Edge>();
		HashMap<Vertex,Vertex> aCopies = new HashMap<Vertex,Vertex>();
		HashMap<Vertex,Vertex> bCopies = new HashMap<Vertex,Vertex>();
		for (Vertex a : A)
		{
			Vertex copyA = new Vertex(a.getLabel(),a.getX());
			aCopies.put(a,copyA);
			newA.add(copyA);
		}
		for (Vertex b : B)
		{
			Vertex copyB = new Vertex(b.getLabel(),b.getX());
			bCopies.put(b,copyB);
			newB.add(copyB);
		}
		for (Edge e : E)
		{
			newE.add(new Edge(aCopies.get(e.getA()),bCopies.get(e.getB())));
		}
		return new BipartiteGraph(newA, newB, newE);
	}

	public BipartiteGraph createReverseGraph()
	{
		HashSet<Vertex> newA = new HashSet<Vertex>();
		HashSet<Vertex> newB = new HashSet<Vertex>();
		HashSet<Edge> newE = new HashSet<Edge>();
		HashMap<Vertex,Vertex> aCopies = new HashMap<Vertex,Vertex>();
		HashMap<Vertex,Vertex> bCopies = new HashMap<Vertex,Vertex>();
		for (Vertex a : A)
		{
			Vertex copyA = new Vertex(a.getLabel(),a.getX());
			aCopies.put(a,copyA);
			newA.add(copyA);
		}
		for (Vertex b : B)
		{
			Vertex copyB = new Vertex(b.getLabel(),b.getX());
			bCopies.put(b,copyB);
			newB.add(copyB);
		}
		for (Edge e : E)
		{
			newE.add(new Edge(bCopies.get(e.getB()),aCopies.get(e.getA())));
		}
		return new BipartiteGraph(newB, newA, newE);
	}
}
