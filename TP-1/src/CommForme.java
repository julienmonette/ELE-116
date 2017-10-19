/******************************************************
Cours : ELE116
Session : AUT2017
Groupe : 01
Projet : Laboratoire #1
�tudiant(e)(s) : 	Colin Reid-Lapierre
					Julien Monette
Code(s) perm. : 	REIC11069309
					XXXXXXXXX					
Professeur : Rita Noumeir
Nom du fichier : CommForme.java
Date cr�ation : 2017-09-11
Date dern. modif. 2017-10-18

*******************************************************
Historique des modifications
*******************************************************
2017-09-11 Version initiale
*******************************************************/

/**
 * CommForme
 * 
 * Fourni deux m�thodes pour envoyer les commande GET et END � ServeurForme
 * @author Colin Reid-Lapierre, Julien Monette
 */
public class CommForme extends Comm {
	
	/**
	 * Demande une forme � ServeurForme
	 */
	public void envoieGET() {
		this.sendString("GET");
	}
	
	/**
	 * Envoie commande de d�connection � ServeurForme
	 */
	public void envoieEND() {
	    this.endConnection();
	}
	
}
