package arbre;

import java.util.Vector;

public class Chapitre {
	
	private String titreChapitre = "";
	private Vector<String> paragraphes = new Vector<String>();
	
	public Chapitre() {};
	public Chapitre(String titre)
	{
		titreChapitre=titre;
	}
	
	public void addText(String text)
	{
		paragraphes.addElement(text);
	}
	public void addText(String text, int i)
	{
		paragraphes.add(i, text);
	}
	
	// TODO implémenter d'autres méthodes ex. enlever texte, enlever paragraphe, etc.

}
