package demo.model;

public class Node {

	private String label;
	private boolean isVisited;
	private boolean isQueued;

	public Node(String label) {
		this.label = label;
		isVisited = false;
		isQueued = false;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public void visitNode() {
		System.out.println(label);
		this.isVisited = true;
	}
	
	public void markQueued(Node node) {
		isQueued = true;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public boolean isQueued() {
		return isQueued;
	}

	public void setQueued(boolean isQueued) {
		this.isQueued = isQueued;
	}
	
	
}
