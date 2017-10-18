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


public class CommForme extends Comm {
	
	//public String forme;
	
	public void envoieGET() {
		this.sendString("GET");
		//forme = this.getString();
	}
		
	public void envoieEND() {
	    this.endConnection();
	}
	
}
