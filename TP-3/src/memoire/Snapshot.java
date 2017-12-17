package memoire;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.image.BufferedImage;


public class Snapshot {
	
	private BufferedImage etat;
    private double imageZoom;
	private int imageXPos;
	private int imageYPos;
	private BufferedImage bufferedImage;
	
	public Snapshot(BufferedImage etat) {
		this.etat = etat;
	}
	public Snapshot(double imageZoom, int imageXPos, int imageYPos, BufferedImage image) {	
		this.imageZoom = imageZoom;
		this.imageXPos = imageXPos;
		this.imageYPos = imageYPos;

			this.bufferedImage = image;

	}
	
	public double getImageZoom() {
		return this.imageZoom;
	}
	
	public BufferedImage getEtat() {
		return etat;
	}
	public int getImageXPos() {
		return this.imageXPos;
	}
	
	public int getImageYPos() {
		return this.imageYPos;
	}

	public BufferedImage GetBufferedImage() {
		return this.bufferedImage;
	}
	
	
	
	
}
