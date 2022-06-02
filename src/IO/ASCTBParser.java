package IO;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Data.TwoLayerGraph.*;


public class ASCTBParser 
{
	HashMap<Integer,Vertex> idToVertex;
	HashSet<Edge> E;
	HashSet<Vertex> A;
	HashSet<Vertex> B;
	String path;
	int groupA;
	int groupB;
	double unitDistance;
	double aOffset;
	double bOffset;
	public ASCTBParser(String _path, int _groupA, int _groupB, double _unitDistance, double _aOffset, double _bOffset)
	{
		path = _path;
		groupA = _groupA;
		groupB = _groupB;
		unitDistance = _unitDistance;
		aOffset = _aOffset;
		bOffset = _bOffset;
	}
	
	private String readFile(String path)  throws IOException
	{
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return new String(encoded, StandardCharsets.US_ASCII);
	}
	
	public BipartiteGraph parse() throws IOException
	{
		A = new HashSet<Vertex>();
		B = new HashSet<Vertex>();
		E = new HashSet<Edge>();	
		idToVertex=new HashMap<Integer,Vertex>();
		String json = readFile(path);
		JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonArray jsonData = jsonObject.getAsJsonArray("data");
        JsonObject jsonNodes = null;
        for (int i = 0; i < jsonData.size(); i++)
        {
        	JsonObject nextObj = jsonData.get(i).getAsJsonObject();
        	String name = nextObj.get("name").getAsString();
        	if (name.equals("nodes"))
        	{
        		jsonNodes = nextObj;
        	}
        }
        JsonArray jsonNodeArray = jsonNodes.getAsJsonArray("values");
        for (int i = 0; i < jsonNodeArray.size(); i++)
        {
        	JsonObject nextNode = jsonNodeArray.get(i).getAsJsonObject();
        	String nodeLabel = nextNode.get("name").getAsString();
        	int nodeGroup = nextNode.get("group").getAsInt();
        	int nodeX = -1;
        	if (nodeGroup == groupA)
        	{
        		nodeX = (int)((nextNode.get("y").getAsDouble()-aOffset)/unitDistance);
        		Vertex newNode = new Vertex(nodeLabel,nodeX); 
        		JsonArray jsonTargets = nextNode.getAsJsonArray("targets");
        		if (jsonTargets.size() > 0)
        		{
        			A.add(newNode);
        			int nodeID = nextNode.get("id").getAsInt();
        			idToVertex.put(nodeID, newNode);
        		}
        	}
        }
        for (int i = 0; i < jsonNodeArray.size(); i++)
        {
        	JsonObject nextNode = jsonNodeArray.get(i).getAsJsonObject();
        	String nodeLabel = nextNode.get("name").getAsString();
        	int nodeGroup = nextNode.get("group").getAsInt();
        	int nodeX = -1;
        	if (nodeGroup == groupB)
        	{
        		nodeX = (int)((nextNode.get("y").getAsDouble()-bOffset)/unitDistance);
        		Vertex newNode = new Vertex(nodeLabel,nodeX); 
        		JsonArray jsonSources = nextNode.getAsJsonArray("sources");
        		if (jsonSources.size() > 0)
        		{
	        		B.add(newNode);
	        		//JsonArray jsonSources = nextNode.getAsJsonArray("targets");
	        		for (int j = 0; j < jsonSources.size(); j++)
	                {
	        			//JsonObject nextSource = jsonNodeArray.get(i).getAsJsonObject();
	        			int sourceID =  jsonSources.get(j).getAsInt();
	        			Edge e = new Edge(idToVertex.get(sourceID),newNode);
	        			E.add(e);
	                	//System.out.println(idToVertex.get(sourceID).getLabel() + "\t" + idToVertex.get(sourceID).getX());
	        		}
        		}
        	}
        	
        	//System.out.println(nodeLabel + "\t" + nodeX);
        }
		BipartiteGraph ret = new BipartiteGraph(A,B,E);
		return ret;
	}
}
