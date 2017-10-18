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
Nom du fichier : Forme.java
Date création : 2017-09-11
Date dern. modif. 2017-10-18

*******************************************************
Historique des modifications
*******************************************************
2017-09-11 Version initiale
*******************************************************/

import java.awt.Graphics;


/**
* Classe décrivant une forme
* 
* @author Colin Reid-Lapierre, Julien Monette
* 
*/
public abstract class Forme 
{
	/**
	 * Affiche la forme sur l'interface.
	 */
    abstract void dessine(Graphics g);
	
	public Forme() {};
	
}
