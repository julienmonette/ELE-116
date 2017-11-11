package arbre;

import java.util.Vector;

public class Racine{

	private String data;

	private Vector<Livre> livres = new Vector<Livre>();
	private String cursor;
	private int iterator_livre = -1;
	private int iterator_chapitre = -1;
	
	
	public String getData() { return this.data;	}	
	public void setData(String data) { this.data = data;}
	
	
	public Livre getLastLivre() {
		return livres.lastElement();
	}
	
	public void setCursor(String s)
	{
		cursor = s;
	}
	
	public Livre getLivre(int i) {
		return livres.get(i);
	}
	
	public int getNbLivre() {
		return livres.size();
	}
	
	public void addLivre(){
		livres.add(new Livre());
	}
	
	public void append(String s)
	{
		switch(cursor)
		{
		case "livre":
			iterator_livre++;
			iterator_chapitre = 0;
			livres.addElement(new Livre());
			break;
			
		case "titre_livre":
			break;
			
		case "auteur":
		//	livres.elementAt(iterator_livre).setAuteur(s);
			break;
			
		case "chapitre":
			getLastLivre().addChapitre();
			iterator_chapitre ++;
			break;	
			
		case "titre_chapitre":
			//livres.elementAt(iterator_livre).getvChapitres.elementAt(iterator_chapitre).setTitre(s);
			break;
			
		case "paragraphe":
			getLastLivre().getLastChapitre().addParagraphe();
			
		}
	}
	
}
