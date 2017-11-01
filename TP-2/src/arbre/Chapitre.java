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
	
	// TODO impl�menter d'autres m�thodes ex. enlever texte, enlever paragraphe, etc.

}
