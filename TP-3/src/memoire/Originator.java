package memoire;

public class Originator {
	private String etat;
	
	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getEtat() {
		return etat;
	}
	
	public Snapshot saveToSnapshot() {
		return new Snapshot(etat);
	}
	
	public void getEtatDeSnapshot(Snapshot snapshot) {
		etat = snapshot.getEtat();
	}
}
