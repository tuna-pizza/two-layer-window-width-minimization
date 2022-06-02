package Data.TwoLayerGraph;

public class Edge 
{
	private Vertex a;
	private Vertex b;
	public Vertex getA()
	{
		return a;
	}
	public Vertex getB()
	{
		return b;
	}
	public Edge(Vertex _a, Vertex _b)
	{
		a = _a;
		b = _b;
	}
}
