import java.awt.Graphics;
import java.awt.Color;

public class Cercle extends Forme{
	
	private int x1;
	private int y1;
	private int r;
	
	private int x;
	private int y;
	private int h;
	private int w;
	
	public Cercle(int x,int y, int r) {
		x1 = x;    // TODO TODO TODO 
		y1 = y;
		this.r = r;
		convertir();
	}
	
	void dessine(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(x, y, h, w);
	}

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
