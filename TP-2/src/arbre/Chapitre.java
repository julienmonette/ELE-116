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
Nom du fichier : Chapitre.java
Date création : 2017-10-31
Date dern. modif. 2017-11-14

*******************************************************
Historique des modifications
*******************************************************
2017-11-14 Version initiale
*******************************************************/

package arbre;

import java.util.Vector;
import visitor.Visitor;

/**
 * Classe Chapitre, un élément de l'arbre de données.
 * 
 * @author Colin Reid-Lapierre et Julien Monette
 */
public class Chapitre implements Node {
	
	private String titre;
	private Vector<Paragraphe> paragraphes = new Vector<Paragraphe>();
	
	/**
	 * Retourne le dernier paragraphe du chapitre
	 * @return Dernier paragraphe
	 */
	public Paragraphe getLastParapgraphe() {
		return paragraphes.lastElement();
	}
	
	/**
	 * Retourne le titre du chapitre
	 * @return Titre du chapitre
	 */
	public String getTitre() {
		return this.titre;
	}
	
	/**
	 * Modifie le titre du chapitre
	 * @param s : Nouveau titre du chapitre
	 */
	public void setTitre(String s) {
		this.titre = s;
	}
	
	/**
	 * Ajoute un paragrahe dans le chapitre actuel.
	 */
	public void addParagraphe() {
		paragraphes.addElement(new Paragraphe());
	}
	
	/**
	 * Retourne le paragraphe à l'indice i du chapitre 
	 * @param i : Indice du chapitre
	 * @return Chapitre à l'indice i
	 */
	public Paragraphe getParagraphe(int i) {
		return paragraphes.get(i);
	}
	
	/**
	 * Retourne le nombre de paragraphe(s) du chapitre
	 * @return Nombre de paragraph(s) du chapitre
	 */
	public int getNbParagraphes() {
		return paragraphes.size();
	}

	/**
	 * Recoit un visiteur, envoie ce dernier à tout les paragraphes chapitre.
	 * @param visitor : Visiteur recu
	 */
	public void accept(Visitor visitor) {
		visitor.visit(this);
		for(int i=0; i<getNbParagraphes(); i++) {
			getParagraphe(i).accept(visitor);
		}
	}
	
}
