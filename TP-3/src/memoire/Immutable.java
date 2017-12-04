package memoire;

final public class Immutable {
	
	private final Snapshot sauvegarde;
	
	public Immutable(Snapshot snap){
		sauvegarde = snap;
	}
	
	public Snapshot getSnap() {
		return sauvegarde;
	}

}
