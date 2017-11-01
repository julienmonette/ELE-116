package arbre;

import java.util.Vector;

public class Noeud{

	private String data;

	private Vector<Livre> livres = new Vector<Livre>();
	private String cursor;
	private int iterator_livre = -1;
	private int iterator_chapitre = -1;
	
	
	public String getData() { return this.data;	}	
	public void setData(String data) { this.data = data;}
	
	
	public void setCursor(String s)
	{
		cursor = s;
	}
	
	public void append(String s)
	{
		switch(cursor)
		{
		case "livre":
			iterator_livre = iterator_livre + 1;
			iterator_chapitre = 0;
			break;
		case "titre_livre":
			livres.elementAt(iterator_livre).setTitle(s);
			break;
		case "auteur":
			livres.elementAt(iterator_livre).setAuteur(s);
			break;
		case "chapitre":
			iterator_chapitre ++;
			break;
		case "titre_chapitre":
			livres.elementAt(iterator_livre).addChapitre(s);
		case "paragraphe":
			livres.elementAt(iterator_livre).EditChapitre(iterator_chapitre).addText(s);
		}
	}
	
}
