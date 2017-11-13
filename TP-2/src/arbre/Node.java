package arbre;

import visitor.Visitor;

public interface Node {
	public void accept(Visitor visitor);
}
