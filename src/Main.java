import java.io.IOException;

import Data.TwoLayerGraph.BipartiteGraph;
import IO.*;
import Algorithm.WindowAndBandWidth;

public class Main 
{
	public static void main (String[] args) throws IOException
	{
		ASCTBParser p = new ASCTBParser("/home/foersth/eclipse-workspace/Windows/all_organs.json",2,3,60,50,60);
		//ASCTBParser p = new ASCTBParser("/home/foersth/eclipse-workspace/Windows/blood.json",3,2,60,60,50);
		BipartiteGraph originalG = p.parse();
		//originalG = originalG.createReverseGraph();
		System.out.println("Original Window:\t" + WindowAndBandWidth.getWindowWidthLabel(originalG) + "\t" + WindowAndBandWidth.getWindowWidth(originalG));
		//g = g.createReverseGraph();
		BipartiteGraph optimalG = WindowAndBandWidth.MinimumWindowWidthDrawing(originalG);
		System.out.println("Optimal Window:\t\t" + WindowAndBandWidth.getWindowWidthLabel(optimalG) + "\t" + WindowAndBandWidth.getWindowWidth(optimalG));
		BipartiteGraph reverseG = originalG.createReverseGraph();
		System.out.println("Reverse Window:\t\t" + WindowAndBandWidth.getWindowWidthLabel(reverseG)  + "\t" + WindowAndBandWidth.getWindowWidth(reverseG));	
		BipartiteGraph approximateG = WindowAndBandWidth.MinimumBandwidthDrawing(reverseG);
		approximateG = approximateG.createReverseGraph();
		System.out.println("Approximate Window:\t" + WindowAndBandWidth.getWindowWidthLabel(approximateG) + "\t" + WindowAndBandWidth.getWindowWidth(approximateG));	
		BipartiteGraph cheatedG = WindowAndBandWidth.MinimumWindowWidthDrawing(reverseG);
		cheatedG = cheatedG.createReverseGraph();
		System.out.println("Cheated Window:\t\t" + WindowAndBandWidth.getWindowWidthLabel(cheatedG) + "\t" + WindowAndBandWidth.getWindowWidth(cheatedG));	
		//BipartiteGraphLayouter.bipartiteGraphToIpe(originalG, 32, 0, 4, "/home/foersth/eclipse-workspace/Windows/originalG.ipe");
		//BipartiteGraphLayouter.bipartiteGraphToIpe(optimalG, 32, 0, 4, "/home/foersth/eclipse-workspace/Windows/optimalG.ipe");
		//BipartiteGraphLayouter.bipartiteGraphToIpe(approximateG, 32, 0, 4, "/home/foersth/eclipse-workspace/Windows/approximateG.ipe");
	}
}
