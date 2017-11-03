package arbre;

import java.util.Vector;

public class Chapitre {
	
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

//	public void addText(String text)
//	{
//		paragraphes.addElement(text);
//	}
//	public void addText(String text, int i)
//	{
//		paragraphes.add(i, text);
//	}
	
	
	public Paragraphe getParagraphe(int i) {
		return paragraphes.get(i);
	}
	
	public int getNbParagraphes() {
		return paragraphes.size();
	}
	
	// TODO implémenter d'autres méthodes ex. enlever texte, enlever paragraphe, etc.

}
