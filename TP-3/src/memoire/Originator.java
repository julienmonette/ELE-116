package memoire;

import java.awt.image.BufferedImage;

public class Originator {
	private BufferedImage etat;
	
	public void setEtat(BufferedImage etat) {
		this.etat = etat;
	}

	public BufferedImage getEtat() {
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
