package arbre;

import java.util.Vector;

import visitor.Visitor;

public class Bibliotheque implements Node{

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
	
	public void accept(Visitor visitor) {	
		visitor.visit(this);
		for( int i=0; i < this.getNbLivre(); i++) {
			getLivre(i).accept(visitor);
		}
	}
	
}
