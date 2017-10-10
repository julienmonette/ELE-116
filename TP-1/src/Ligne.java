import java.awt.Graphics;
import java.awt.Color;

public class Ligne extends Forme {
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	
	public Ligne(int c1,int c2, int c3,int c4) {
		x1 = c1;
		x2 = c2;
		y1 = c3;
		y2 = c4;
	}
	
	void dessine(Graphics g){
		g.setColor(Color.cyan);
		g.drawLine(x1, y1, x2, y2);
	}
	
}
