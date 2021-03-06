/******************************************************
Cours : ELE116
Session : AUT2017
Groupe : 01
Projet : Laboratoire #2
�tudiant(e)(s) : 	Colin Reid-Lapierre
					Julien Monette
Code(s) perm. : 	REIC11069309
					MONJ28079501					
Professeur : Rita Noumeir
Nom du fichier : VisitorPrintAll.java
Date cr�ation : 2017-10-31
Date dern. modif. 2017-11-14

*******************************************************
Historique des modifications
*******************************************************
2017-11-14 Version initiale
*******************************************************/

package visitor;

import arbre.Chapitre;
import arbre.Livre;
import arbre.Paragraphe;
import java.io.PrintStream;
import arbre.Bibliotheque;

/**
 * Visite chacun des �l�ment du de la bibliotheque et les �crit dans un fichier html avec les
 * bonnes balises.
 * 
 * @author Colin Reid-Lapierre
 */
public class VisitorPrintAll implements Visitor {

	PrintStream pout;
	
	/**
	 * Consctructeur. Initialise le Printstream pour le fichier html qui sera g�n�r�
	 * 
	 * @param pout
	 */
	public VisitorPrintAll(PrintStream pout) {
		this.pout = pout;
	}
	
	/**
	 * Visiteur de bibliotheque
	 */
	public void visit(Bibliotheque root){};

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
		pout.println("Auteur : "+ livre.getAuteur());
	}

	/**
	 * Visiteur de paragraphe
	 */
	public void visit(Paragraphe paragraphe) {
		pout.println("<p>"+paragraphe.getText()+"</p>");
	}

}
