package MVC;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;





public class PaintModel implements Observable{

	private static final double ZOOMFACTOR = 0.1;
	
	private AffineTransform modif = null;
	private Observer observer;
	private double imageZoom = 1;
	private BufferedImage image = null;
	
	public void notifyObserver() {
		observer.getParameter();
	}	
	
	public void OpenImage(BufferedImage image) {
		this.image = image;
		notifyObserver();
	}
	
	public void applyTranslation() {
		// do something
        //float currentImgWidth = img.getWidth()*zoom, currentImgHeight = img.getHeight()*zoom;
       // t.translate(width/2-currentImgWidth/2, height/2-currentImgHeight/2);
		notifyObserver();
	}
	
	public void zoomIN()
	{
		imageZoom += ZOOMFACTOR;
		notifyObserver();
	}
	public void zoomOUT()
	{
		imageZoom -= ZOOMFACTOR;
		notifyObserver();
	}
	
	public  void toGrayScale() {
		for( int i = 0; i < image.getWidth(); ++i ){
			for( int j = 0; j < image.getHeight(); ++j ) {
				Color color = new Color(image.getRGB(i,j));
				int gray = (color.getRed() + color.getGreen() + color.getBlue() )/3;
				Color grayPixel = new Color(gray, gray, gray);
				image.setRGB(i,j,grayPixel.getRGB());
			}
		}
		
		notifyObserver();
	}
	
	public void addObserver(Observer observer) {
		this.observer = observer;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	public double getZoom() {
		return this.imageZoom;
	}
	
	
	
	

}
