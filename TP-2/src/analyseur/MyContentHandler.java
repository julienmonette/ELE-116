package analyseur;

import javax.xml.soap.Node;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import arbre.Livre;
import arbre.Bibliotheque;

public class MyContentHandler implements ContentHandler {

	private Bibliotheque bibliotheque = new Bibliotheque();
	

	private String prevXMLTag;
	
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		
		String s = new String(arg0,arg1,arg2);	
		
		// Ici on accède contenu (texte) du fichier xml
		if(prevXMLTag.equals("titre_livre")) 
			bibliotheque.getLastLivre().setTitle(s);	
		
		else if(prevXMLTag.equals("auteur"))
			bibliotheque.getLastLivre().setAuteur(s);	
		
		else if(prevXMLTag.equals("titre_chapitre"))
			bibliotheque.getLastLivre().getLastChapitre().setTitre(s);
		
		else if(prevXMLTag.equals("paragraphe"))
			bibliotheque.getLastLivre().getLastChapitre().getLastParapgraphe().setText(s);
		
		prevXMLTag = "";
	}

	public void startElement(String arg0, String XMLTag, String arg2, Attributes arg3) throws SAXException {
		
		switch(XMLTag){
		case "livre":
			bibliotheque.addLivre();
			break;
			
		case "chapitre":
			bibliotheque.getLastLivre().addChapitre();
			break;	
			
		case "paragraphe":
			bibliotheque.getLastLivre().getLastChapitre().addParagraphe();
		}
		
		prevXMLTag = XMLTag;
	}
	

	public Bibliotheque getBibliotheque() {
		return bibliotheque;
	}
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void endElement(String arg0, String arg1, String arg2) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void endPrefixMapping(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void ignorableWhitespace(char[] arg0, int arg1, int arg2) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void processingInstruction(String arg0, String arg1) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void setDocumentLocator(Locator arg0) {
		// TODO Auto-generated method stub
		
	}

	public void skippedEntity(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void startDocument() throws SAXException {
		
	}

	public void startPrefixMapping(String arg0, String arg1) throws SAXException {

		
	}
	
}
