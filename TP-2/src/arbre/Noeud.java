package arbre;

import visitor.Visitor;

public interface Noeud {
	public void accept(Visitor visitor);
}
