package memoire;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Snapshot {
	
    private double imageZoom;
	private int imageXPos;
	private int imageYPos;
	private BufferedImage bufferedImage;
	
	public Snapshot(double imageZoom, int imageXPos, int imageYPos, BufferedImage buffImage) {
		Color c = new Color(buffImage.getRGB(165, 15));
		System.out.println("Imgae recu du caretaker :" + c.getRed());
		
		
		
		this.imageZoom = imageZoom;
		this.imageXPos = imageXPos;
		this.imageYPos = imageYPos;
		

		this.bufferedImage = buffImage;
	}
		
	public double getImageZoom() {
		return this.imageZoom;
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
