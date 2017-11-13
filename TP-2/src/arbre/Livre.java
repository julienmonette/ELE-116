package arbre;
import java.util.Vector;
import visitor.Visitor;

public class Livre implements Node {

	private Vector<Chapitre> chapitres = new Vector <Chapitre>();
	private String title;
	private String auteur;
	
	public Vector<Chapitre> getvChapitre() {
		return this.chapitres;
	}
	
	public Chapitre getLastChapitre() {
		return chapitres.lastElement();
	}
	
	
	public Chapitre getChapitre(int i) {
		return chapitres.get(i);
	}
	
	public int getNbChapitre() {
		return chapitres.size();
	}
	
	public void addChapitre() {
		chapitres.addElement(new Chapitre());
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuteur() {
		return this.auteur;
	}
	
	public void setAuteur(String s) {
		this.auteur = s;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
		for(int i=0; i<getNbChapitre(); i++) {
			getChapitre(i).accept(visitor);
		}
	}
	
	
	
	
	
}
