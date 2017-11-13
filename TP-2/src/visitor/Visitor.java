package visitor;

import arbre.Chapitre;
import arbre.Livre;
import arbre.Paragraphe;
import arbre.Bibliotheque;

public interface Visitor {

	public void visit(Bibliotheque root);
	public void visit(Chapitre chapitre);
	public void visit(Livre livre);
	public void visit(Paragraphe Paragraphe);
}
