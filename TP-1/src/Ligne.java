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
Nom du fichier : Ligne.java
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
* Classe décrivant la forme "ligne"
* 
* @author Colin Reid-Lapierre, Julien Monette
* 
*/
public class Ligne extends Forme {
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	/**
	 * Constructeur
	 * 
	 * @param x1 Coordonnée (x1,y1) (x2,y2) décrivant les extémités de la ligne
	 * @param x2
	 * @param y1
	 * @param y2
	 */
	public Ligne(int x1,int x2, int y1,int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	/**
	 * Affiche la forme sur l'interface.
	 */
	void dessine(Graphics g){
		g.setColor(Color.cyan);
		g.drawLine(x1, y1, x2, y2);
	}
	
}
