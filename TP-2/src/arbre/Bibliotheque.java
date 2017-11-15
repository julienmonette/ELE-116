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
Nom du fichier : Bibliotheque.java
Date cr�ation : 2017-10-31
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
 * Classe Bibliotheque. Racine de l'arbre de donn�s contenant tout les livres.
 * 
 * @author Colin Reid-Lapierre et Julien Monette
 */
public class Bibliotheque implements Node{

	private String data;
	private Vector<Livre> livres = new Vector<Livre>();
	public String getData() { return this.data;	}	
	
	
	/**
	 * Retourn le dernier livre de la bibliotheque
	 * @return Dernier livre
	 */
	public Livre getLastLivre() {
		return livres.lastElement();
	}
	
	/**
	 * Retourne le livre � l'indice i de la bibliotheque
	 * @param i : Indice du livre � retourner
	 * @return Livre � l'indice i de la bibliotheque
	 */
	public Livre getLivre(int i) {
		return livres.get(i);
	}
	
	/**
	 * Retourne le nombre de livre(s) contenu(s) dans la bibliotheque
	 * @return Nombre de livre(s) contenu(s) dans la bibliotheque
	 */
	public int getNbLivre() {
		return livres.size();
	}
	
	/**
	 * Cr�e un livre, ajoute ce dernier dans la bibliotheque actuelle
	 */
	public void addLivre(){
		livres.add(new Livre());
	}
	
	/**
	 * Accepte un visiteur. Envoie ce dernier � tous les livres de la biblioth�que
	 */
	public void accept(Visitor visitor) {	
		visitor.visit(this);
		for( int i=0; i < this.getNbLivre(); i++) {
			getLivre(i).accept(visitor);
		}
	}
}
