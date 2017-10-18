/******************************************************
Cours : ELE116
Session : AUT2017
Groupe : 01
Projet : Laboratoire #1
Étudiant(e)(s) : 	Colin Reid-Lapierre
					Julien Monette
Code(s) perm. : 	REIC11069309
					XXXXXXXXX					
Professeur : Rita Noumeir
Nom du fichier : Rectangle.java
Date création : 2017-09-11
Date dern. modif. 2017-10-18

*******************************************************
Historique des modifications
*******************************************************
2017-09-11 Version initiale
*******************************************************/


import java.awt.Graphics;
import java.awt.Color;

public class Rectangle extends Forme {
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int x;
	private int y;
	private int h;
	private int w;
	
	
	public Rectangle(int x1,int x2, int y1,int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		convertir();
	}
	
	void dessine(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x1, x2, y1, y2);
	}
	
	public void convertir()
	{
		x = x1;
		y = y1;
		w = Math.abs(x2-x1) ;
		h = Math.abs(y2-y1);
	}
	
}
