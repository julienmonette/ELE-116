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
Nom du fichier : Visitor.java
Date création : 2017-10-31
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
import arbre.Bibliotheque;

/**
 * Interface visiteur. Contient les méthodes visit pour chacun des éléments de l'arbre de données.
 * 
 * @author Colin Reid-Lapierre et Julien Monette
 */
public interface Visitor {

	public void visit(Bibliotheque root);
	public void visit(Chapitre chapitre);
	public void visit(Livre livre);
	public void visit(Paragraphe Paragraphe);
}
