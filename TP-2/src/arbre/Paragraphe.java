package arbre;
import visitor.Visitor;

public class Paragraphe implements Node {

	private String text="";
	
	public void setText(String s) {
		this.text = s;
	}
	
	public String getText() {
		return this.text;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
