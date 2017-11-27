package memoire;

public class Snapshot {
	
	//Va falloir changer String pour le type d'etat qu'on veut sauvegarder
	private String etat;
	
	public Snapshot(String etat) {
		this.etat = etat;
	}
	
	public String getEtat() {
		return etat;
	}

}
