package memoire;
import java.awt.Color;
import java.util.EmptyStackException;
import java.util.Stack;

public class CareTaker {
	private Stack<Snapshot> snapshotStack = new Stack<Snapshot>();
	
	public void add(Snapshot etat) {	
		snapshotStack.add(etat);
	}
	
	public Snapshot getLastSnapshot() throws EmptyStackException {		
		Snapshot s = (snapshotStack.pop());
		return s; 
	}

}
