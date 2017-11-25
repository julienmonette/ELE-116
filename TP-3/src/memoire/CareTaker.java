package memoire;

import java.util.List;
import java.util.ArrayList;

public class CareTaker {
	private List<Snapshot> snapshotList = new ArrayList<Snapshot>();
	
	public void add(Snapshot etat) {
		snapshotList.add(etat);
	}
	
	public Snapshot get(int index) {
		return snapshotList.get(index);
	}

}
