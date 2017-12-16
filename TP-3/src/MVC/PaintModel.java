package MVC;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import memoire.CareTaker;
import memoire.Snapshot;

public class PaintModel implements Observable{

	private static final double ZOOMFACTOR = 0.1;
	
	private Observer observer;
	private CareTaker careTaker = new CareTaker();
	
    private double imageZoom = 1;
	private int imageXPos = 0;
	private int imageYPos = 0;
	private BufferedImage image = null;
	
	public void notifyObserver() {
		observer.getParameter();
	}	
	
	public void OpenImage(BufferedImage image) {
		this.image = image;
		notifyObserver();
	}
	
	public void applyTranslation(int xTranslation, int yTranslation) {
		careTaker.add(new Snapshot(imageZoom, imageXPos, imageYPos, image));
		this.imageXPos += xTranslation;
		this.imageYPos += yTranslation;
		notifyObserver();
	}
	
	public void zoomIN()
	{
		careTaker.add(new Snapshot(imageZoom, imageXPos, imageYPos,image));
		imageZoom += ZOOMFACTOR;
		notifyObserver();
	}
	public void zoomOUT()
	{
		careTaker.add(new Snapshot(imageZoom, imageXPos, imageYPos,image));
		imageZoom -= ZOOMFACTOR;
		notifyObserver();
	}
	
	public void undoAction() {
		Snapshot s = careTaker.getLastSnapshot();	
		this.imageXPos = s.getImageXPos();
		this.imageYPos = s.getImageYPos();
		this.imageZoom = s.getImageZoom();
		this.image = s.GetBufferedImage();
		notifyObserver();
	}
	
	public  void toGrayScale() {
		careTaker.add(new Snapshot(imageZoom, imageXPos, imageYPos,image));
		for( int i = 0; i < image.getWidth(); ++i ){
			for( int j = 0; j < image.getHeight(); ++j ) {
				Color color = new Color(image.getRGB(i,j));
				int gray = (color.getRed() + color.getGreen() + color.getBlue() )/3;
				Color grayPixel = new Color(gray, gray, gray);
				this.image.setRGB(i,j,grayPixel.getRGB());
			}
		}
		notifyObserver();
	}
	
	public void addObserver(Observer observer) {
		this.observer = observer;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public double getZoom() {
		return this.imageZoom;
	}
	
	public int getXPos() {
		return imageXPos;
	}
	
	public int getYPos() {
		return imageYPos;
	}
	

	
}
