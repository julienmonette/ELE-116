/******************************************************
Cours : ELE116
Session : AUT2017
Groupe : 01
Projet : Laboratoire #2
Étudiant(e)(s) : 	Colin Reid-Lapierre
					Julien Monette
Code(s) perm. : 	REIC11069309
					MONJ28079501					
Professeur : Rita Noumeir
Nom du fichier : VisitorPrintTitles.java
Date création : 2017-10-31
Date dern. modif. 2017-11-14

*******************************************************
Historique des modifications
*******************************************************
2017-11-14 Version initiale
*******************************************************/

package visitor;

import arbre.Bibliotheque;
import arbre.Chapitre;
import arbre.Livre;
import arbre.Paragraphe;
import java.io.PrintStream;

/**
 * Classe visiteur permettant d'écrire dans le fichier désigné par pout seulement les titres des livres et des 
 * chapitre contenu dans l'arbre de données donné au constructeur. 
 * 
 * @author Julien Monette
 *
 */
public class VisitorPrintTitles implements Visitor{

	PrintStream pout;
	
	/**
	 * Constructeur. Reçoit le Printstream vers lequel les données doivent être écrites.
	 * @param pout : PrintStream désignant le fichier html vers lequel écrire
	 */
	public VisitorPrintTitles(PrintStream pout) {
		this.pout = pout;
	}
	
	/**
	 * Visiteur de bibliotheque
	 */
	public void visit(Bibliotheque root) {}


	/**
	 * Visiteur de Chapitre
	 */
	public void visit(Chapitre chapitre) {
		pout.println("<h2>"+chapitre.getTitre()+"</h2>");
	}

	/**
	 * Visiteur de livre
	 */
	public void visit(Livre livre) {
		pout.println("<h1>"+livre.getTitle()+"</h1>");
	}

	/**
	 * Visiteur de paragraphe
	 */
	public void visit(Paragraphe paragraphe) {
		
	}
}
