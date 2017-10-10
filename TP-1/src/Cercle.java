import java.awt.Graphics;
import java.awt.Color;

public class Cercle extends Forme{
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	
	public Cercle(int x,int y, int r) {
		x1 = x;    // TODO TODO TODO 
		x2 = y;
		y1 = r;
		y2 = r;
	}
	
	void dessine(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(x1, x2, y1, y2);
	}
	
}
