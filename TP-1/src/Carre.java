import java.awt.Graphics;
import java.awt.Color;

public class Carre extends Forme {

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int x;
	private int y;
	private int h;
	private int w;
	
	
	


	public Carre(int x1,int x2, int y1,int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		convertir();
	}
	
	void dessine(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, w, h);
	}

	public void convertir()
	{
		x=x1;
		y=y1;
		w = Math.abs(x2-x1) ;
		h = Math.abs(y2-y1);
	}

}
