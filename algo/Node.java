package graou.algo;

public class Node {

	private int name;
	private int key;
	private int parent;
	
	public Node(int name, int key, int parent) {
		this.name = name;
		this.key = key;
		this.parent = parent;
	}
	
	public int getName() {
		return this.name;
	}
	
	public int getKey() {
		return this.key;
	}

	public int getParent() {
		return this.parent;
	}

	public void setName(int name) {
		this.name = name;
	}
	
	public void setKey(int key) {
		this.key = key;
	}
	
	public void setParent(int parent) {
		this.parent = parent;
	}
}
