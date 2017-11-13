package visitor;

import arbre.Chapitre;
import arbre.Livre;
import arbre.Paragraphe;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import arbre.Bibliotheque;

public class VisitorPrintAll implements Visitor {

	PrintStream pout;
	
	public VisitorPrintAll(PrintStream pout) {
		this.pout = pout;
	}
	
	public void visit(Bibliotheque root) {}

	@Override
	public void visit(Chapitre chapitre) {
		pout.println("<h2>"+chapitre.getTitre()+"</h2>");
	}

	@Override
	public void visit(Livre livre) {
		pout.println("<h1>"+livre.getTitle()+"</h1>");
		pout.println("Auteur : "+ livre.getAuteur());
	}

	@Override
	public void visit(Paragraphe paragraphe) {
		pout.println("<p>"+paragraphe.getText()+"</p>");
	}

}
