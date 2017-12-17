package MVC;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import memoire.CannotUndoException;
import memoire.CareTaker;
import memoire.Snapshot;

public class PaintModel implements Observable{

	private static final double ZOOMFACTOR = 0.1;
	
	private Observer observer;
	private CareTaker careTaker = new CareTaker();
	private Singleton listeCommandes = Singleton.getInstance();
	
    private double imageZoom = 1;
	private int imageXPos = 0;
	private int imageYPos = 0;
	private BufferedImage image = null;
	
	public void notifyObserver() {
		observer.getParameter();
	}	
	
	public void OpenImage(BufferedImage image) {
		this.image = image;
		listeCommandes.ajouterCommande("Ouverture Image");
		notifyObserver();
	}
	
	public void applyTranslation(int xTranslation, int yTranslation) {
		careTaker.add(new Snapshot(imageZoom, imageXPos, imageYPos, image));
		this.imageXPos += xTranslation;
		this.imageYPos += yTranslation;
		listeCommandes.ajouterCommande("Translation de " + xTranslation + " en x et de " + yTranslation + " en y");
		notifyObserver();
	}
	
	public void zoomIN()
	{
		careTaker.add(new Snapshot(imageZoom, imageXPos, imageYPos,image));
		imageZoom += ZOOMFACTOR;
		listeCommandes.ajouterCommande("Zoom In");
		notifyObserver();
	}
	public void zoomOUT()
	{
		careTaker.add(new Snapshot(imageZoom, imageXPos, imageYPos,image));
		imageZoom -= ZOOMFACTOR;
		listeCommandes.ajouterCommande("Zoom Out");
		notifyObserver();
	}
	
	public void undoAction() {
		
		Snapshot s;
		try {
			s = careTaker.getLastSnapshot();
			this.imageXPos = s.getImageXPos();
			this.imageYPos = s.getImageYPos();
			this.imageZoom = s.getImageZoom();
			this.image = s.GetBufferedImage();
		} catch (CannotUndoException e) {
		}
		

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
	
	public void printCommandes() {
		System.out.println(listeCommandes.listeDesCommandes());
	}

	
}
