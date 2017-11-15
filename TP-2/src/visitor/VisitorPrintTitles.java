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
Nom du fichier : VisitorPrintTitles.java
Date cr�ation : 2017-10-31
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
 * Classe visiteur permettant d'�crire dans le fichier d�sign� par pout seulement les titres des livres et des 
 * chapitre contenu dans l'arbre de donn�es donn� au constructeur. 
 * 
 * @author Julien Monette
 *
 */
public class VisitorPrintTitles implements Visitor{

	PrintStream pout;
	
	/**
	 * Constructeur. Re�oit le Printstream vers lequel les donn�es doivent �tre �crites.
	 * @param pout : PrintStream d�signant le fichier html vers lequel �crire
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
