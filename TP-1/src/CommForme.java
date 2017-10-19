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
Nom du fichier : CommForme.java
Date création : 2017-09-11
Date dern. modif. 2017-10-18

*******************************************************
Historique des modifications
*******************************************************
2017-09-11 Version initiale
*******************************************************/

/**
 * CommForme
 * 
 * Fourni deux méthodes pour envoyer les commande GET et END à ServeurForme
 * @author Colin Reid-Lapierre, Julien Monette
 */
public class CommForme extends Comm {
	
	/**
	 * Demande une forme à ServeurForme
	 */
	public void envoieGET() {
		this.sendString("GET");
	}
	
	/**
	 * Envoie commande de déconnection à ServeurForme
	 */
	public void envoieEND() {
	    this.endConnection();
	}
	
}
