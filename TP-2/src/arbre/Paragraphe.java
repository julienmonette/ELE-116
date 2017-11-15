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
Nom du fichier : Paragraphe.java
Date cr�ation : 2017-10-31
Date dern. modif. 2017-11-14

*******************************************************
Historique des modifications
*******************************************************
2017-11-14 Version initiale
*******************************************************/

package arbre;
import visitor.Visitor;


/**
 * Repr�sente un paragraphe dans l'arbre de donn�es
 * 
 * @author Colin Reid-Lapierre et Julien Monette
 */
public class Paragraphe implements Node {

	private String text="";
	
	/**
	 * D�finit s comme �tant le texte de paragraphe
	 * @param s
	 */
	public void setText(String s) {
		this.text = s;
	}
	
	/**
	 * Retourne le texte de paragraphe
	 * @return texte du paragrahe
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Recoit un visiteur qui visite le paragraphe.
	 * @param visitor
	 */
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
