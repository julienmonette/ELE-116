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
Nom du fichier : Livre.java
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
 * Représente un livre dans l'arbre de données
 * 
 * @author Colin Reid-Lapierre et Julien Monette
 */
public class Livre implements Node {

	private Vector<Chapitre> chapitres = new Vector <Chapitre>();
	private String title;
	private String auteur;
	
	/**
	 * Retourne le vecteur de chapitres du livre.
	 * @return vecteur de chapitres du livre
	 */
	public Vector<Chapitre> getvChapitre() {
		return this.chapitres;
	}
	
	/**
	 * Retourne le dernier chapitre du livre
	 * @return Dernier chapitre
	 */
	public Chapitre getLastChapitre() {
		return chapitres.lastElement();
	}
	
	/**
	 * Retourne le chapitre se trouvant à l'index i du livre
	 * @param i : Index du chapitre
	 * @return Chapitre à l'index i
	 */
	public Chapitre getChapitre(int i) {
		return chapitres.get(i);
	}
	
	/**
	 * Retoure le nombre de chapitre(s) du livre
	 * @returnn Nombre de chapitre(s)
	 */
	public int getNbChapitre() {
		return chapitres.size();
	}
	
	/**.
	 * Ajoute un chapitre à la fin du livre actuel
	 */
	public void addChapitre() {
		chapitres.addElement(new Chapitre());
	}

	/**
	 * Retourne le titre du livre
	 * @return Titre du livre
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Modifie le titre du livre
	 * @param title : Titre désiré
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Retourne l'auteur du livre
	 * @return Auteur
	 */
	public String getAuteur() {
		return this.auteur;
	}
	
	/**
	 * Modifie l'auteur du livre
	 * @param s : Auteur
	 */
	public void setAuteur(String s) {
		this.auteur = s;
	}

	/**
	 * Recoit un visiteur. Envoie le visiteurà tous les chapitres du livre
	 */
	public void accept(Visitor visitor) {
		visitor.visit(this);
		for(int i=0; i<getNbChapitre(); i++) {
			getChapitre(i).accept(visitor);
		}
	}
	
}
