package demo.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {

	int[][] edges;
	int vertices;
	Node[] nodes;
	int index;
	
	public Graph(int vertices) {
		this.vertices = vertices;
		edges = new int[vertices][vertices];
		nodes = new Node[vertices];
	}
	
	public void addNode(String label) {
		if( index == vertices) {
			System.out.println("Graph is full");
			return;
		}
		nodes[index++] = new Node(label);
	}
	
	public void addEdge(String from, String to) {
		edgeOperation(from, to, true);
	}
	
	public void removeEdge(String from, String to) {
		edgeOperation(from, to, false);
	}
	
	public void bfs(String sourceLabel) {
		Queue<Node> queue = new LinkedList<>();
		System.out.println("BFS of graph : ");
		queue.add(getNode(getNodeIndex(sourceLabel)));
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			node.visitNode();
			List<Node> adajacentVertices = getAdjacentVertices(node.getLabel());
			adajacentVertices.forEach(n -> {
				if(!n.isVisited() && !n.isQueued()) {
					queue.add(n);
					n.setQueued(true);
				}
			});
		}
	}
	
	public void dfs(String sourceLabel) {
		Stack<Node> stack = new Stack<>();
		System.out.println("DFS of graph : ");
		Node node = getNode(getNodeIndex(sourceLabel));
		node.visitNode();
		stack.push(node);
		
		while(!stack.isEmpty()) {
			Node unvisitedNode = getUnVisitedAdjacentNode(node.getLabel());
			if(unvisitedNode != null) {
				unvisitedNode.visitNode();
				stack.push(unvisitedNode);
				node = unvisitedNode;
			} else {
				node = stack.pop();
			}
		}
	}

	public boolean isGraphConnected() {
		Node node = getNode();
		dfs(node.getLabel());
		for(Node n : nodes) {
			if(!n.isVisited()) {
				return false;
			}
		}
		return true;
	}
	
	private Node getNode() {
		return nodes[0];
	}
	private List<Node> getAdjacentVertices(String label) {
		int nodeIndex = getNodeIndex(label);
		List<Node> adjacentNodes = new ArrayList<>();
		for(int i=0; i<vertices; i++) {
			if(edges[nodeIndex][i] == 1) {
				adjacentNodes.add(getNode(i));
			}
		}
		return adjacentNodes;
	}
	
	private Node getUnVisitedAdjacentNode(String label) {
		List<Node> adjacentVertices = getAdjacentVertices(label);
		return adjacentVertices.stream().filter(node->!node.isVisited()).findFirst().orElse(null);
	}
	
	private Node getNode(int index) {
		return nodes[index];
	}
	
	private int getNodeIndex(String label) {
		for(int i=0; i<vertices; i++) {
			if(nodes[i].getLabel().equals(label)){
				return i;
			}
		}
		return -1;
	}
	
	private void edgeOperation(String from, String to, boolean operation) {
		int fromIndex = getNodeIndex(from);
		int toIndex = getNodeIndex(to);
		if(fromIndex == -1 || toIndex == -1) {
			System.out.println("Node not found");
			return;
		}
		if(operation) {
			edges[fromIndex][toIndex] = 1;
			edges[toIndex][fromIndex] = 1;
		} else {
			edges[fromIndex][toIndex] = 0;
			edges[toIndex][fromIndex] = 0;
		}
	}
}
