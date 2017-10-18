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
Nom du fichier : Carre.java
Date création : 2017-09-11
Date dern. modif. 2017-10-18

*******************************************************
Historique des modifications
*******************************************************
2017-09-11 Version initiale
*******************************************************/


import java.awt.Graphics;
import java.awt.Color;

/**
 * Classe décrivant la forme "carré"
 * 
 * @author Colin Reid-Lapierre, Julien Monette
 * 
 */
public class Carre extends Forme {

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int x;
	private int y;
	private int h;
	private int w;
	
	/**
	 * Constructeur
	 * 
	 * @param x1 Coordonnée (x1,y1) (x2,y2) décrivant la diagonnale du carré
	 * @param x2
	 * @param y1
	 * @param y2
	 */
	public Carre(int x1,int x2, int y1,int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		convertir();
	}
	
	/**
	 * Affiche la forme sur l'interface.
	 */
	void dessine(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, w, h);
	}

	/**
	 * Convertit le format reçu du serveur pour respecter le format de 
	 * la méthode fillRect.
	 */
	private void convertir()
	{
		x=x1;
		y=y1;
		w = Math.abs(x2-x1) ;
		h = Math.abs(y2-y1);
	}

}
