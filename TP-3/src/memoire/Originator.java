package memoire;

public class Originator {
	//Va falloir changer String pour le type d'etat qu'on veut sauvegarder
	private String etat;
	
	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getEtat() {
		return etat;
	}
	/*
	public Snapshot saveToSnapshot() {
		return new Snapshot(etat);
	}
	
	public void getEtatDeSnapshot(Snapshot snapshot) {
	//	etat = snapshot.getEtat();
	}*/
}
