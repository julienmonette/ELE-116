import java.awt.Graphics;
import java.awt.Color;

/**
* Classe décrivant la forme "cercle"
* 
* @author Colin Reid-Lapierre, Julien Monette
* 
*/
public class Cercle extends Forme{
	
	private int x1;
	private int y1;
	private int r;
	
	private int x;
	private int y;
	private int h;
	private int w;
	
	/**
	 * Constructeur de cercle
	 * @param x (x,y), coordonnée du centre 
	 * @param y
	 * @param r rayon
	 */
	public Cercle(int x,int y, int r) {
		x1 = x;    // TODO TODO TODO 
		y1 = y;
		this.r = r;
		convertir();
	}
	
	/**
	 * Affiche la forme sur l'interface.
	 */
	void dessine(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(x, y, h, w);
	}
	
	/**
	 * Convertit le format reçu du serveur pour respecter le format de 
	 * la méthode fillOval.
	 */
	public void convertir()
	{
		if(x1 > r)
			x = x1-r;
		else
			x = x1;
		
		if(y1 > r)
			y = y1-r;
		else
			y = y1;
		
		
		w = 2*r;
		h = 2*r;
	}	
		
}
