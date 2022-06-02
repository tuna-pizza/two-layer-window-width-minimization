package Data.TwoLayerGraph;

public class Vertex 
{
	private String label;
	private int x;
	
	public String getLabel()
	{
		return label;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setX(int _x)
	{
		x=_x;
	}
	
	public Vertex(String _label, int _x)
	{
		label = _label;
		x = _x;
	}
}
