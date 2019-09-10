	import java.util.LinkedList;

public class Node {
	private Weapon weapon;
	private double time;
	
	private LinkedList<Node> next;
	private Node parent;
	
	private int generation;
	
	private int overdamage;
	
	public Node(Weapon weapon) {
		super();
		next = new LinkedList<Node>();
		this.weapon = weapon;
	}
	
	
	
	public int getOverdamage() {
		return overdamage;
	}



	public void setOverdamage(int overdamage) {
		this.overdamage = overdamage;
	}



	public int getGeneration() {
		return generation;
	}



	public void setGeneration(int generation) {
		this.generation = generation;
	}



	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;

	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}

	public LinkedList<Node> getNext() {
		return next;
	}

	public void setNext(LinkedList<Node> next) {
		this.next = next;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	
}
