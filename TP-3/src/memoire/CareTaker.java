package memoire;


import java.awt.Color;
import java.util.EmptyStackException;
import java.util.Stack;

public class CareTaker {
	private Stack<Snapshot> snapshotStack = new Stack<Snapshot>();
	
	public void add(Snapshot etat) {	
		snapshotStack.add(etat);
		Color c = new Color(etat.GetBufferedImage().getRGB(165, 15));
		System.out.println("Generation du snap "+ snapshotStack.size() + " avec : "+ c.getRed());	
	}
	
	public Snapshot getLastSnapshot() throws EmptyStackException {		
		Snapshot s = (snapshotStack.pop());
		Color c = new Color(s.GetBufferedImage().getRGB(165, 15));
		System.out.println("Get de snap du stack "+ (snapshotStack.size()+1) + " avec : "+ c.getRed());

		return s; 
	}
	
	/*
	public Snapshot get(int index) {
		return snapshotList.get(index);
	}*/

}
