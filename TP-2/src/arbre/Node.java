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
Nom du fichier : Node.java
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
 * Interface pour les �l�ments de l'arbre de donn�.
 * Contient la fonction abstraite "accept" pour le fonctionnement du visiteur
 * 
 * @author Colin RL et Julien Monette
 */
public interface Node {
	 public void accept(Visitor visitor);
}