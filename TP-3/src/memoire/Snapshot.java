package memoire;

import java.awt.image.BufferedImage;

public class Snapshot {
	
	private BufferedImage etat;
	
	public Snapshot(BufferedImage etat) {
		this.etat = etat;
	}
	
	public BufferedImage getEtat() {
		return etat;
	}

}
