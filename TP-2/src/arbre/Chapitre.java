package arbre;

import java.util.Vector;
import visitor.Visitor;

public class Chapitre implements Node {
	
	private String titre;
	private Vector<Paragraphe> paragraphes = new Vector<Paragraphe>();
	
	public Paragraphe getLastParapgraphe() {
		return paragraphes.lastElement();
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public void setTitre(String s) {
		this.titre = s;
	}
	
	public void addParagraphe() {
		paragraphes.addElement(new Paragraphe());
	}
	
	public Paragraphe getParagraphe(int i) {
		return paragraphes.get(i);
	}
	
	public int getNbParagraphes() {
		return paragraphes.size();
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		for(int i=0; i<getNbParagraphes(); i++) {
			getParagraphe(i).accept(visitor);
		}
	}
	
}
