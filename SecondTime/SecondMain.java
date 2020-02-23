import java.util.*;

import SecondTimePackage.*;
import SecondTimePackage.Graph.GraphContainer;
import SecondTimePackage.Graph.GraphNode;
import SecondTimePackage.Hard.CircusPerson;
import SecondTimePackage.Hard.Name;
import SecondTimePackage.Moderate.Person;

public class SecondMain {
	
	public static void main(String[] args) {
		Recursion r = new Recursion();
		Moderate m = new Moderate();
		Solution s = new Solution();
		Hard h = new Hard();
		Graph g = new Graph();

		System.out.println("\n--------------------------------------------------");

		GraphContainer graph = g.new GraphContainer();
		GraphNode node1 = g.new GraphNode(1);	graph.addNode(node1);
		GraphNode node2 = g.new GraphNode(2);	graph.addNode(node2);
		GraphNode node3 = g.new GraphNode(3);	graph.addNode(node3);
		GraphNode node4 = g.new GraphNode(4);	graph.addNode(node4);
		GraphNode node5 = g.new GraphNode(5);	graph.addNode(node5);
		GraphNode node6 = g.new GraphNode(6);	graph.addNode(node6);

		node1.addNieghbor(node2);
		node1.addNieghbor(node3);
		node1.addNieghbor(node4);
		node2.addNieghbor(node5);
		node4.addNieghbor(node5);
		node5.addNieghbor(node6);

		// System.out.println(g.doesRouteExist(graph, node3, node6));
		ArrayList<LinkedList<GraphNode>> layers = g.layers(graph, node1);


		System.out.println("--------------------------------------------------");
	}

}
